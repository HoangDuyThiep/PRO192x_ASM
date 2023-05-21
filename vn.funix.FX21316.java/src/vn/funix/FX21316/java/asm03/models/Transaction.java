package vn.funix.FX21316.java.asm03.models;

import java.util.Date;
import java.util.Random;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private Boolean status;

    public Transaction(String accountNumber, double amount) {
        // Kiểm tra xem tài khoản khách hàng có tồn tại không
        if (checkAccountExistence(accountNumber)) {
            this.id = generateRandomId();
            this.accountNumber = accountNumber;
            this.amount = amount;
            // Lấy thời điểm hiện tại
            this.time = new Date().toString();
            this.status = true;
        } else {
            this.id = null;
            this.accountNumber = null;
            this.amount = 0;
            this.time = null;
            this.status = null;
        }
    }

    // Hàm kiểm tra tài khoản khách hàng có tồn tại không trong hệ thống ngân hàng
    private Boolean checkAccountExistence(String accountNumber) {
        // Viết thêm code kiểm tra tài khoản khách hàng có tồn tại trong hệ thống ngân hàng
        // Ở đây sẽ trả về true/ false cho mô phỏng thử nghiệm
        return true; // Trả về true nếu tài khoản tồn tại
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public void displayTransaction() {
        System.out.printf("[GD]   " + getAccountNumber() + " | %20s | %30s%n", Utils.formatBalance(amount), getTime());
    }
}
