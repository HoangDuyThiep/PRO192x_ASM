package vn.funix.FX21316.java.asm03.models;

import vn.funix.FX21316.java.asm02.models.TransactionType;

import java.io.Serializable;
import java.util.Random;

public class Transaction implements Serializable {
    // định danh phiên bản của lớp để đảm bảo tính nhất quán
    // trong quá trình tuần tự hóa và giải tuần tự hóa.
    private static final long serialVersionUID = 1L;

    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private Boolean status;
    private TransactionType type;
    // contructor

    public Transaction(String accountNumber, double amount, String time, boolean status, TransactionType type) {
            this.id = generateRandomId();
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.time = time;
            this.status = status;
            this.type = type;
    }
    // Hàm tạo mã ngẫu nhiên cho giao dịch
    private String generateRandomId() {
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            builder.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return builder.toString();
    }

    // Getter và Setter cho các thuộc tính của lớp Transaction


    public String getAccountNumber() {
        return accountNumber;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public TransactionType getType() {
        return type;
    }

    public void displayTransaction() {
        System.out.printf("[GD]   " + getAccountNumber() + " | %10s | %20s | %30s%n",getType() , Utils.formatBalance(amount), getTime());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", time='" + time + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}

