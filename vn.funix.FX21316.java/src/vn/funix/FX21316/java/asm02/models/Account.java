package vn.funix.FX21316.java.asm02.models;

import vn.funix.FX21316.java.asm03.models.Transaction;
import vn.funix.FX21316.java.asm04.TransactionDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Serializable {
    // định danh phiên bản của lớp để đảm bảo tính nhất quán
    // trong quá trình tuần tự hóa và giải tuần tự hóa.
    private static final long serialVersionUID = 1L;
    private String accountNumer;
    private double balance;
    private String type;
    private String customerId;

// constructor
//    public Account(String accountNumer, double balance, String type) {
//        this.accountNumer = accountNumer;
//        this.balance = balance;
//        this.type = type;
//    }
//    public Account(String accountNumer, double balance) {
//        this.accountNumer = accountNumer;
//        this.balance = balance;
//        if (balance >= 10000000) {
//            this.type = "Premium";
//        } else {
//            this.type = "Normal";
//        }
//    }

    public Account(String accountNumer, String customerId, double balance) {
        this.accountNumer = accountNumer;
        this.balance = balance;
        if (balance >= 10000000) {
            this.type = "Premium";
        } else {
            this.type = "Normal";
        }
        this.customerId = customerId;
    }
// setter & getter
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountNumer() {
        return accountNumer;
    }

    public String isPremiumAccount() {
        if (balance >= 10000000) {
            return "Premium";
        }
        return "Normal";
    }

    public double getBalance() {
        return balance;
    }


    public String getType() {
        return type;
    }

    public String getCustomerId() {
        return customerId;
    }
    // lấy ra các giao dịch thuộc account hiện tại từ file
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        List<Transaction> readFile = TransactionDao.list();
        for (Transaction transaction : readFile)
                // Lọc các giao dịch theo accountNumber
                if (transaction.getAccountNumber().equals(accountNumer)) {
                    transactions.add(transaction);
                }


        return transactions;
    }
    //hiện thị danh sách ra màn hình
    public void displayTransactionsList() {
        for (Transaction transaction : getTransactions()) {
            transaction.displayTransaction();
        }
    }
    // tạo ra thêm một giao dịch cho account và cập nhật số dư tài khoản
    public void createTransaction(double amount, String time, boolean status, TransactionType type) {
        // Tạo giao dịch mới
        Transaction transaction = new Transaction(accountNumer, amount, time, status, type);

        // Thực hiện cập nhật số dư tài khoản
//        setBalance(getBalance() + amount);
    }


//    // thêm tài khoản mới vào danh sách
//    public void  input(Scanner scanner) {
//        System.out.print("Nhập mã tài khoản gồm 6 chữ số: ");
//        int accNumber;
//        //kiểm tra số tài khoản
//        do {
//            // try catch khi nhập số tài khoản
//            try {
//                accNumber = Integer.parseInt(scanner.nextLine());
//            } catch (Exception e) {
//                accNumber = 0;
//            }
//            if (!customer.checkAccNum(accNumber)) {
//                System.out.println("Số tài khoản đã trùng hoặc không phải 6 số");
//            }
//        } while (!customer.checkAccNum(accNumber));
//        int balance;
//        //vòng lặp do kiểm tra số dư ban đầu hợp lệ
//        do {
//            System.out.print("Nhâp số dư tài khoản ban đầu, yêu cầu lớn hơn 50 000 đồng: ");
//            //catch nhập kí tự khong hợp lệ
//            try {
//                balance = Integer.parseInt(scanner.nextLine());
//            } catch (Exception e) {
//                System.out.println("Không hợp lệ:");
//                balance = 0;
//            }
//
//        } while (balance < 50000);
//
//        Account account = new SavingsAccount(accNumber, balance);
//        customer.addAccount(account);
//    }
//    //kiem tra số tài khoản và số tiền ban đầu
//    public boolean checkAccNum(int accNumber) {
//        if (accounts.size() < 1) {
//            if (accNumber >= 100000 && accNumber < 1000000) {
//                return true;
//            } else return false;
//        } else if (accNumber < 100000 || accNumber >= 1000000) {
//            return false;
//        } else  {
//            for (int i = 0; i < accounts.size(); i++) {
//                if ((accNumber == accounts.get(i).getAccountNumer())) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    public abstract String getTypeOfAcc();

    @Override
    public String toString() {
        return "Account{" +
                "accountNumer='" + accountNumer + '\'' +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}

