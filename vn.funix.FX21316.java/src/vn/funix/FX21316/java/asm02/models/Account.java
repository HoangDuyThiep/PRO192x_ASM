package vn.funix.FX21316.java.asm02.models;

public abstract class Account {
    private int accountNumer;
    private double balance;
    private String type;
// constructor ------------------------------
    public Account(int accountNumer, double balance, String type) {
        this.accountNumer = accountNumer;
        this.balance = balance;
        this.type = type;
    }
// ---------------------------------------------
    public Account(int accountNumer, double balance) {
        this.accountNumer = accountNumer;
        this.balance = balance;
        if (balance >= 10000000) {
            this.type = "Premium";
        } else {
            this.type = "Normal";
        }
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAccountNumer() {
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

    public abstract String getTypeOfAcc();



}
