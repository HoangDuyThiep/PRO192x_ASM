package vn.funix.FX21316.java.asm03.models;

import vn.funix.FX21316.java.asm02.models.Account;

import java.util.ArrayList;

public class LoansAccount extends Account implements ReportService, Withdraw{

    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;
    private ArrayList<Transaction> transactions;

    //contractor
    public LoansAccount(int accountNumer, double balance, String type) {
        super(accountNumer, balance, type);
    }

    public LoansAccount(int accountNumer, double balance) {
        super(accountNumer, balance);
        transactions = new ArrayList<Transaction>();
        Transaction transaction = new Transaction(String.valueOf(accountNumer), balance);
        transactions.add(transaction);
    }

    //hàm khác
    private static String getTitle() {
        return "BIEN LAI GIAO DICH LOAN";
    }

    @Override
    public String getTypeOfAcc() {
        return "LOAN";
    }
    public double getTransactionFee() {
        return (getType().equals("Premium")) ? LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : LOAN_ACCOUNT_WITHDRAW_FEE;
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
            setBalance(getBalance() - amount - (amount * getTransactionFee()));
            Transaction transaction = new Transaction(String.valueOf(getAccountNumer()), -amount - (amount * getTransactionFee()));
            transactions.add(transaction);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        double maxWithdrawal = (getType().equals("Premium")) ? Double.MAX_VALUE : LOAN_ACCOUNT_MAX_BALANCE;

        if (amount < 50000 || amount > maxWithdrawal) {
            System.out.println("Số tiền rút khong hợp lệ.");
            return false;
        }

        if (amount % 10000 != 0) {
            System.out.println("Số tiền phải là bội số của 10,000.");
            return false;
        }

        if ((amount + (amount * getTransactionFee()) + 50000) > getBalance()) {
            System.out.println("số dư tài khoản không đủ hoặc sau khi rút ít hơn 50.000đ.");
            System.out.println("Bạn chỉ có thể rút dưới : " + Utils.formatBalance(getBalance() - 50000 - (getBalance() - 50000) * getTransactionFee()));
            return false;
        }
        return true;
    }
    public void displayTransactions() {
        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }
}
