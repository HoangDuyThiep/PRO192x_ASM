package vn.funix.FX21316.java.asm02.models;

import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Account> accounts;

    public Customer(String name, String customerId) {
        super(name, customerId);
        this.accounts = new ArrayList<Account>();
    }


    public void addAccount(int newAccount, double newBalance, String type) {
        Account account = new Account(newAccount, newBalance, type);
        accounts.add(account);
    }


    private String getType() {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getType().equalsIgnoreCase("premium")) {
                return "Premium";
            }
        }
        return "Normal";
    }

    private int getBalance() {
        int balance = 0;
        for (int i = 0; i < accounts.size(); i++) {
            balance += accounts.get(i).getBalance();
        }
        return balance;
    }

    public void displayInformation() {
        System.out.println(getCustomerId() + "   |        " + getName() + "            "+ getType() +"         |     " + getBalance());
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).getAccountNumer() + "   |                             |     " +accounts.get(i).getBalance());
        }
    }

}

