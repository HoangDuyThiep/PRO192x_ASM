package vn.funix.FX21316.java.asm02.models;

import vn.funix.FX21316.java.asm03.models.Transaction;
import vn.funix.FX21316.java.asm04.dao.TransactionDao;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Account implements Serializable {
    // định danh phiên bản của lớp để đảm bảo tính nhất quán
    // trong quá trình tuần tự hóa và giải tuần tự hóa.
    private static final long serialVersionUID = 1L;
    private String accountNumer;
    private double balance;
    private String type;
    private String customerId;


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
        List<Transaction> transactions = TransactionDao.list();

        List<Transaction> readFile = TransactionDao.list();
        return readFile.stream()
                .filter(transaction
                        -> transaction
                        .getAccountNumber()
                        .equals(accountNumer))
                .collect(Collectors.toList());
//        for (Transaction transaction : readFile)
//                // Lọc các giao dịch theo accountNumber
//                if (transaction.getAccountNumber().equals(accountNumer)) {
//                    transactions.add(transaction);
//                }
//        return transactions;
    }
    //hiện thị danh sách ra màn hình
    public void displayTransactionsList() {
        for (Transaction transaction : getTransactions()) {
            transaction.displayTransaction();
        }
    }
    // tạo ra thêm một giao dịch cho account và cập nhật số dư tài khoản
    public void createTransaction(double amount, String time, boolean status, TransactionType type) {
        List<Transaction> transactions = TransactionDao.list();
        // Tạo giao dịch mới
        Transaction transaction = new Transaction(getAccountNumer(), amount, time, status, type);
        transactions.add(transaction);
        try {
            TransactionDao.save(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Thực hiện cập nhật số dư tài khoản
//        setBalance(getBalance() + amount);
    }

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

