package vn.funix.FX21316.java.asm03.models;

import vn.funix.FX21316.java.asm02.models.Account;
import vn.funix.FX21316.java.asm02.models.TransactionType;
import vn.funix.FX21316.java.asm04.AccountDao;

import java.io.Serializable;
import java.util.Date;
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

    public Transaction(String accountNumber, double amount) {
        // Kiểm tra xem tài khoản khách hàng có tồn tại không
        if (checkAccountExistence(accountNumber)) {
            this.id = generateRandomId();
            this.accountNumber = accountNumber;
            this.amount = amount;
            // Lấy thời điểm hiện tại
            this.time = new Date().toString();
            this.status = true;
            this.type = null;
        } else {
            this.id = null;
            this.accountNumber = null;
            this.amount = 0;
            this.time = null;
            this.status = null;
            this.type = null;
        }
    }

    public Transaction(String accountNumber, double amount, String time, boolean status, TransactionType type) {
        // Kiểm tra xem tài khoản khách hàng có tồn tại không
        if (checkAccountExistence(accountNumber)) {
            this.id = generateRandomId();
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.time = time;
            this.status = status;
            this.type = type;
        } else {
            this.id = null;
            this.accountNumber = null;
            this.amount = 0;
            this.time = null;
            this.status = null;
            this.type = null;
        }
    }

    // Hàm kiểm tra tài khoản khách hàng có tồn tại không trong hệ thống ngân hàng
    private Boolean checkAccountExistence(String accountNumber) {
        // Viết thêm code kiểm tra tài khoản khách hàng có tồn tại trong hệ thống ngân hàng
        // Ở đây sẽ trả về true/ false cho mô phỏng thử nghiệm
        for (Account account : AccountDao.list()) {
            if (account.getAccountNumer().equals(accountNumber)) {
                return true; // Tài khoản tồn tại trong danh sách tài khoản
            }
        }
        return false; // Tài khoản không tồn tại trong danh sách tài khoản
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

