package vn.funix.FX21316.java.asm02.models;

public class Account {
    private int accountNumer;
    private double balance;
    private String type;

    public Account(int accountNumer, double balance, String type) {
        this.accountNumer = accountNumer;
        this.balance = balance;
        this.type = type;
    }

    public int getAccountNumer() {
        return accountNumer;
    }


    public double getBalance() {
        return balance;
    }


    public String getType() {
        return type;
    }



}
