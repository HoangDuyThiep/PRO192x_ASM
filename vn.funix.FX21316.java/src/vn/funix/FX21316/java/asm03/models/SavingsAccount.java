package vn.funix.FX21316.java.asm03.models;

import vn.funix.FX21316.java.asm02.models.Account;
import vn.funix.FX21316.java.asm02.models.TransactionType;
import vn.funix.FX21316.java.asm04.IReport;

import java.util.ArrayList;
import java.util.Date;

public class SavingsAccount extends Account implements Withdraw, ReportService, IReport {

    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    private ArrayList<Transaction> transactions;

//contractor
    public SavingsAccount(String accountNumer, double balance, String type) {
        super(accountNumer, balance, type);
    }

    public SavingsAccount(String accountNumer, String customerId, double balance) {
        super(accountNumer, customerId, balance);
        transactions = new ArrayList<Transaction>();
        Transaction transaction = new Transaction(String.valueOf(accountNumer), balance);
        transactions.add(transaction);
    }

    //    public SavingsAccount(String accountNumer, double balance, String customerId ) {
//        super(accountNumer, balance, customerId);
//        transactions = new ArrayList<Transaction>();
//        Transaction transaction = new Transaction(String.valueOf(accountNumer), balance);
//        transactions.add(transaction);
//    }
//hàm khác
    @Override
    public String getTypeOfAcc() {
        return "SAVINGS";
    }
    private static String getTitle() {
        return "BIEN LAI GIAO DICH SAVINGS";
    }

    private static double getTransactionFee() {
        return 0;
    }

    @Override
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", getTitle());
        System.out.printf("Ngày G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumer());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(getTransactionFee() * amount));
        System.out.println(Utils.getDivider());
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            setBalance(getBalance() - amount);
            String getCurrentTime = new Date().toString();
            createTransaction(-amount, getCurrentTime, true, TransactionType.WITHDRAW);
//            transactions.add(transaction);
            return true;
        }
        return false;
    }
    public void transfers(Account receiveAccount, double amount) {
        // Kiểm tra điều kiện chuyển tiền qua lớp isAccepted
        if (isAccepted(amount)) {
            String getCurrentTime = new Date().toString();
            // Tạo giao dịch trừ tiền cho người gửi
            createTransaction(-amount, getCurrentTime, true, TransactionType.TRANSFERS);

            // Tạo giao dịch cộng tiền cho người nhận
            receiveAccount.createTransaction(amount, getCurrentTime, true, TransactionType.TRANSFERS);

//
//            // Cập nhật số dư tài khoản gửi
//            this.setBalance(this.getBalance() - amount);
//
//            // Cập nhật số dư tài khoản nhận
//            receiveAccount.setBalance(receiveAccount.getBalance() + amount);
        } else {
            System.out.println("Không thể thực hiện giao dịch chuyển tiền.");
        }
    }

    @Override
    public boolean isAccepted(double amount) {
        double maxWithdrawal = (getType().equals("Premium")) ? Double.MAX_VALUE : SAVINGS_ACCOUNT_MAX_WITHDRAW;

        if (amount < 50000 || amount > maxWithdrawal) {
            System.out.println("Số tiền rút khong hợp lệ.");
            return false;
        }

        if (amount % 10000 != 0) {
            System.out.println("Số tiền phải là bội số của 10,000.");
            return false;
        }

        if ((amount + 50000) > getBalance()) {
            System.out.println("số dư tài khoản không đủ hoặc sau khi rút ít hơn 50.000đ.");
            System.out.println("Bạn chỉ có thể rút dưới : " + Utils.formatBalance(getBalance() - 50000));
            return false;
        }
        return true;
    }
    public void displayTransactions() {
             for (Transaction transaction : transactions) {
                 transaction.displayTransaction();
             }
    }

    @Override
    public void log(double amount, TransactionType type, String receiveAccount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", getTitle());
        System.out.printf("Ngày G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumer());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(getTransactionFee() * amount));
        System.out.println(Utils.getDivider());
    }

}
