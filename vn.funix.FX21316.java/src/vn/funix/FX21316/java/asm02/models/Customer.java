package vn.funix.FX21316.java.asm02.models;

import vn.funix.FX21316.java.asm03.models.LoansAccount;
import vn.funix.FX21316.java.asm03.models.SavingsAccount;
import vn.funix.FX21316.java.asm03.models.Withdraw;
import vn.funix.FX21316.java.asm04.AccountDao;

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

            do {
                System.out.println("Nhập số tiền rút: ");
                amount = Double.parseDouble(scanner.nextLine());
            } while (amount <= 0);

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
        Account receiverAccount;
        do {
            System.out.println("Nhập số tài khoản nhận: ");
            receiverAccount = getAccountByAccountNumber(accounts, scanner.nextLine());
        } while (receiverAccount == null);

        // Nhập số tiền cần chuyển
        System.out.println("Nhập số tiền chuyển: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // Xác nhận việc chuyển tiền
        System.out.println("Xác nhận chuyển tiền? (Y/N)");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("Y")) {
            // Thực hiện chuyển tiền
            if (senderAccount instanceof  SavingsAccount) {
                ((SavingsAccount) senderAccount).transfers(receiverAccount, amount);
            }
//            else if (senderAccount instanceof LoansAccount) {
//                ((LoansAccount) senderAccount).transfers(amount);
//            }
            System.out.println("Chuyển tiền thành công.");
        } else {
            System.out.println("Chuyển tiền đã bị hủy bỏ.");
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
                System.out.println("Số tài khoản đã trùng hoặc không phải 6 số");
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
//        addAccount(account);
        AccountDao.update(account);
        System.out.println("Tài khoản đã được thêm thành công!");
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
            for (Account account : accounts) {
                if ((accNumber == Integer.parseInt(account.getAccountNumer()))) {
                    return false;
                }
            }
        }
        return true;
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

        System.out.println(getCustomerId() + "   |        " + getName() + "  |      "+ getType() +"         |     " + currency);
        for (int i = 0; i < accounts.size(); i++) {
            double numberOfAcc = accounts.get(i).getBalance();
            String currencyOfAcc = currencyFormatter.format(numberOfAcc);
            System.out.printf(String.valueOf(counter) + "   " + accounts.get(i).getAccountNumer() + " %25s %39s%n", accounts.get(i).getTypeOfAcc() + "  |", currencyOfAcc);
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

        System.out.println(getCustomerId() + "   |        " + getName() + "  |      "+ getType() +"         |     " + currency);
        for (int i = 0; i < accounts.size(); i++) {
            double numberOfAcc = accounts.get(i).getBalance();
            String currencyOfAcc = currencyFormatter.format(numberOfAcc);
            System.out.printf(String.valueOf(counter) + "   " + accounts.get(i).getAccountNumer() + " %25s %39s%n", accounts.get(i).getTypeOfAcc() + "  |", currencyOfAcc);
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

