package vn.funix.FX21316.java.asm02.models;

import vn.funix.FX21316.java.asm03.models.LoansAccount;
import vn.funix.FX21316.java.asm03.models.SavingsAccount;
import vn.funix.FX21316.java.asm03.models.Utils;
import vn.funix.FX21316.java.asm03.models.Withdraw;
import vn.funix.FX21316.java.asm04.dao.AccountDao;
import vn.funix.FX21316.java.asm04.models.Color;
import vn.funix.FX21316.java.asm04.models.ITransfer;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer extends User implements Serializable {
    // định danh phiên bản của lớp để đảm bảo tính nhất quán
    // trong quá trình tuần tự hóa và giải tuần tự hóa.
    private static final long serialVersionUID = 1L;;
//    private List<Account> accounts = getAccounts();
// constructor

    public Customer() {
        super();
    }

    public Customer(String name, String customerId) {
        super(name, customerId);
    }

    // đọc dữ liệu từ file text
    public Customer(List<String> values) {
        this(values.get(1), values.get(0));
    }

// các hàm chức năng
//    public void addAccount(Account account) {
//        List<Account> accounts = getAccounts();
//        accounts.add(account);
//    }

// getter & setter
    //  lấy ra danh sách các tài khoản (accounts) có customerId bằng customerId hiện tại
    public List<Account> getAccounts() {
        return AccountDao.list().stream()
                .filter(account -> account.getCustomerId().equals(getCustomerId()))
                .collect(Collectors.toList());
    }
    // lấy ra một tài khoản từ trong danh sách accounts dựa trên số tài khoản (accountNumber)
    public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumer().equals(accountNumber)) {
                return account;
            }
        }
        return null; // Trả về null nếu không tìm thấy tài khoản
    }

    public String getType() {
        List<Account> accounts = getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).isPremiumAccount().equalsIgnoreCase("Premium")) {
                return "Premium";
            }
        }
        return "Normal";
    }
    //nhận tổng số tiền của khách hàng
    public int getBalance() {
        List<Account> accounts = getAccounts();
        int balance = 0;
        for (int i = 0; i < accounts.size(); i++) {
            balance += accounts.get(i).getBalance();
        }
        return balance;
    }
// hàm chức năng
    public Account searchByAccNum(int keyWord) {
        List<Account> accounts = getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            int accNum = Integer.parseInt(accounts.get(i).getAccountNumer());
            if (keyWord == accNum) {
                return accounts.get(i);
            }
        }
        return null;
    }
    //rut tien
    public void withdraw(Scanner scanner) {
        List<Account> accounts = getAccounts();
        if (!accounts.isEmpty()) {
            Account account;
            double amount;

            do {
                System.out.println("Nhập số tài khoàn: ");
                account = getAccountByAccountNumber(accounts, scanner.nextLine());
            } while (account == null);
            // nhap va kiem tra so tien
            do {
                System.out.println("Nhập số tiền rút: ");
                amount = Double.parseDouble(scanner.nextLine());
            } while (!checkAmount(account, amount));

            if (account instanceof Withdraw) {
                ((Withdraw) account).withdraw(amount);
                // cap nhap tai khoan

//                ((SavingsAccount) account).withdraw(amount);
            }
            Account updataAccount = new SavingsAccount(account.getAccountNumer(), getCustomerId(), account.getBalance());
            AccountDao.update(updataAccount);
//            else if (account instanceof LoansAccount) {
//                ((LoansAccount) account).withdraw(amount);
//            }
        } else {
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công");
        }
    }
    //chuyen tien
    public void transfers(Scanner scanner) {
        List<Account> accounts = getAccounts();
        // Nhập tài khoản gửi
        Account senderAccount;
        do {
            System.out.println("Nhập số tài khoản gửi: ");
            senderAccount = getAccountByAccountNumber(accounts, scanner.nextLine());
        } while (senderAccount == null);

        // Nhập tài khoản nhận
        Account receiverAccount = null;
        String receiverAccNum;
        while (true){
            System.out.println("Nhập số tài khoản nhận (exit để thoát): ");
            receiverAccNum = scanner.nextLine();
            if (receiverAccNum.equalsIgnoreCase("exit")) {
                break;
            }
            if(receiverAccNum.equals(senderAccount.getAccountNumer())) {
                System.out.println("Số tài khoản nhận đã trùng số tài khoản nhận!");
                receiverAccNum = "";
            }
            receiverAccount = getAccountByAccountNumber(accounts, receiverAccNum);
            if (receiverAccount != null) {
                break;
            }
        }
        //nhan exit de thoat
        if (!receiverAccNum.equalsIgnoreCase("exit")) {
            // Nhập số tiền cần chuyển & kiem tra so tien chuyen
            double amount;
            do {
                System.out.println("Nhập số tiền chuyển: ");
                try {
                    amount = Double.parseDouble(scanner.nextLine());
                } catch (Exception e) {
                    amount = 0;
                }
            } while (!checkAmount(senderAccount, amount));
            // Xác nhận việc chuyển tiền
            System.out.println("Xác nhận chuyển tiền? (Y/N)");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("Y")) {
                // Thực hiện chuyển tiền
                if (senderAccount instanceof ITransfer) {
                    ((ITransfer) senderAccount).transfer(receiverAccount, amount);
                }
//            else if (senderAccount instanceof LoansAccount) {
//                ((LoansAccount) senderAccount).transfers(amount);
//            }
                // updata tai khoan gui
                Account updataSenderAccount = new SavingsAccount(senderAccount.getAccountNumer(), getCustomerId(), senderAccount.getBalance());
                AccountDao.update(updataSenderAccount);
                // updata tai khoan nhan
                Account updataReceiverAccount = new SavingsAccount(receiverAccount.getAccountNumer(), getCustomerId(), receiverAccount.getBalance());
                AccountDao.update(updataReceiverAccount);
                System.out.println("Chuyển tiền thành công.");
            } else {
                System.out.println("Chuyển tiền đã bị hủy bỏ.");
            }
        }
    }
    // thêm tài khoản mới vào danh sách
    public void  input(Scanner scanner) {
        System.out.print("Nhập mã tài khoản gồm 6 chữ số: ");
        int accNumber;
        //kiểm tra số tài khoản
        do {
            // try catch khi nhập số tài khoản
            try {
                accNumber = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                accNumber = 0;
            }
            if (!checkAccNum(accNumber)) {
                System.out.print("Số tài khoản đã trùng hoặc không phải 6 số: ");
            }
        } while (!checkAccNum(accNumber));
        double balance;
        //vòng lặp do kiểm tra số dư ban đầu hợp lệ
        do {
            System.out.print("Nhâp số dư tài khoản ban đầu, yêu cầu lớn hơn 50 000 đồng: ");
            //catch nhập kí tự khong hợp lệ
            try {
                balance = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Không hợp lệ:");
                balance = 0;
            }

        } while (balance < 50000);
        String accoutNumer = String.valueOf(accNumber);
        Account account = new SavingsAccount(accoutNumer, getCustomerId(), balance);
        account.createTransaction(balance, Utils.getDateTime(), true, TransactionType.DEPOSIT);
//        addAccount(account);
        AccountDao.update(account);
        System.out.println("Tài khoản đã được thêm thành công!");
//        displayTransactionInformation();
    }
    //kiem tra số tài khoản và số tiền ban đầu
    public boolean checkAccNum(int accNumber) {
        // check toan bo accNum trong ngan hang
        List<Account> accounts = AccountDao.list();
        if (accounts.size() < 1) {
            if (accNumber >= 100000 && accNumber < 1000000) {
                return true;
            } else return false;
        } else if (accNumber < 100000 || accNumber >= 1000000) {
            return false;
        } else  {
            // kiểm tra acc đã được tạo hay chưa
            if (isAccountExits(accounts, String.valueOf(accNumber))) {
                return false;
            }
        }
        return true;
    }
    //kiem tra so tien rut
    private boolean checkAmount(Account account, double amount) {
        double maxWithdrawal = (account.getType().equals("Premium")) ? Double.MAX_VALUE : 5000000;

        if (amount < 50000 || amount > maxWithdrawal) {
            System.out.println("Số tiền rút khong hợp lệ.");
            return false;
        }

        if (amount % 10000 != 0) {
            System.out.println("Số tiền phải là bội số của 10,000.");
            return false;
        }

        if ((amount + 50000) > account.getBalance()) {
            System.out.println("số dư tài khoản không đủ hoặc sau khi rút ít hơn 50.000đ.");
            System.out.println("Bạn chỉ có thể rút dưới : " + Utils.formatBalance(account.getBalance() - 50000));
            return false;
        }
        return true;
    }
    // kiểm tra acc đã được tạo hay chưa
    private boolean isAccountExits(List<Account> accounts, String accNumber) {
        return accounts.stream()
                .anyMatch(account -> accNumber.equals(account.getAccountNumer()));
    }
// display
    public void displayInformation() {
        List<Account> accounts = getAccounts();
        int counter = 1;
        //định dạng tiền việt
        double number = getBalance();
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String currency = currencyFormatter.format(number);

        System.out.println(Color.yellowColor + getCustomerId() + "   |        " + getName() + "  |      "+ getType() +"         |     " + currency + Color.resetColor);
        for (int i = 0; i < accounts.size(); i++) {
            double numberOfAcc = accounts.get(i).getBalance();
            String currencyOfAcc = currencyFormatter.format(numberOfAcc);
            System.out.printf(Color.greenColor + String.valueOf(counter) + "   " + accounts.get(i).getAccountNumer() + " %25s %39s%n", accounts.get(i).getTypeOfAcc() + "  |", currencyOfAcc + Color.resetColor);
            counter++;
        }
    }
    public  void displaytransactions() {
        List<Account> accounts = getAccounts();
        for (Account account: accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).displayTransactions();
            }
            if (account instanceof LoansAccount) {
                ((LoansAccount) account).displayTransactions();
            }
        }
    }
    //hiển thị thông tin customer, thông tin các tài khoản và thông tin các giao dịch của khách hàng hiện tại
    public  void displayTransactionInformation() {
        List<Account> accounts = getAccounts();
        int counter = 1;
        //định dạng tiền việt
        double number = getBalance();
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String currency = currencyFormatter.format(number);

        System.out.println(Color.yellowColor + getCustomerId() + "   |        " + getName() + "  |      "+ getType() +"         |     " + currency + Color.resetColor);
        for (int i = 0; i < accounts.size(); i++) {
            double numberOfAcc = accounts.get(i).getBalance();
            String currencyOfAcc = currencyFormatter.format(numberOfAcc);
            System.out.printf(Color.greenColor + String.valueOf(counter) + "   " + accounts.get(i).getAccountNumer() + " %25s %39s%n", accounts.get(i).getTypeOfAcc() + "  |", currencyOfAcc + Color.resetColor);
            counter++;
        }
        for (Account account: accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).displayTransactions();
            }
            if (account instanceof LoansAccount) {
                ((LoansAccount) account).displayTransactions();
            }
        }
    }
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                ", customerId='" + getCustomerId() + '\'' +
                '}';
    }

}

