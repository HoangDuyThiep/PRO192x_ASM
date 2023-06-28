package vn.funix.FX21316.java.asm03.models;

import vn.funix.FX21316.java.asm02.models.Account;
import vn.funix.FX21316.java.asm02.models.TransactionType;
import vn.funix.FX21316.java.asm04.models.IReport;
import vn.funix.FX21316.java.asm04.models.ITransfer;
import vn.funix.FX21316.java.asm04.dao.TransactionDao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SavingsAccount extends Account implements Withdraw, IReport, ITransfer, Serializable {
    private static final long serialVersionUID = 1L;

    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
//    private ArrayList<Transaction> transactions;

//contractor
//    public SavingsAccount(String accountNumer, double balance, String type) {
//        super(accountNumer, balance, type);
//    }

    public SavingsAccount(String accountNumer, String customerId, double balance) {
        super(accountNumer, customerId, balance);
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
    public List<Transaction> getTransactions() {
        return TransactionDao.list();
    }



    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            setBalance(getBalance() - amount);
//            String getCurrentTime = new Date().toString();
            createTransaction(amount, Utils.getDateTime(), true, TransactionType.WITHDRAW);
            log(amount,TransactionType.WITHDRAW, null);
//            transactions.add(transaction);
            return true;
        }
        return false;
    }
    @Override
    public void transfer(Account receiveAccount, double amount) {
        // Kiểm tra điều kiện chuyển tiền qua lớp isAccepted
        if (isAccepted(amount)) {
            String getCurrentTime = new Date().toString();
            // Tạo giao dịch trừ tiền cho người gửi
            this.setBalance(this.getBalance() - amount);
            createTransaction(-amount, Utils.getDateTime(), true, TransactionType.TRANSFERS);

            // Tạo giao dịch cộng tiền cho người nhận
            receiveAccount.setBalance(receiveAccount.getBalance() + amount);
            receiveAccount.createTransaction(amount, Utils.getDateTime(), true, TransactionType.TRANSFERS);
            log(amount,TransactionType.TRANSFERS, receiveAccount.getAccountNumer());
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
        // stream loc transactions
        List<Transaction> transactions = TransactionDao.list().stream()
                .filter(transaction -> transaction.getAccountNumber().equals(getAccountNumer()))
                .collect(Collectors.toList());
             for (Transaction transaction : transactions) {
                 transaction.displayTransaction();
             }
    }
// log
    @Override
    public void log(double amount, TransactionType type, String receiveAccount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", getTitle());
        System.out.printf("Ngày G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        if (receiveAccount == null) {
            System.out.printf("SO TK: %31s%n", getAccountNumer());
            System.out.printf("SO TIEN RUT: %25s%n", Utils.formatBalance(amount));
        } else {
            System.out.printf("SO TK GUI: %27s%n", getAccountNumer());
            System.out.printf("SO TK NHAN: %26s%n", receiveAccount);
            System.out.printf("SO TIEN GUI: %25s%n", Utils.formatBalance(amount));
        }
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(getTransactionFee() * amount));
        System.out.println(Utils.getDivider());
    }

}
