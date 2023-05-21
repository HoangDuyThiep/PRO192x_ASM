package vn.funix.FX21316.java.asm02.models;

import vn.funix.FX21316.java.asm03.models.LoansAccount;
import vn.funix.FX21316.java.asm03.models.SavingsAccount;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Customer extends User {
    private ArrayList<Account> accounts;
// constructor
    public Customer(String name, String customerId) {
        super(name, customerId);
        this.accounts = new ArrayList<Account>();
    }

// các hàm chức năng
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public boolean checkAccNum(int accNumber) {
        if (accounts.size() < 1) {
            if (accNumber >= 100000 && accNumber < 1000000) {
                return true;
            }
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (accNumber != accounts.get(i).getAccountNumer() && accNumber >= 100000 && accNumber < 1000000) {
                return true;
            }
        }
        return false;
    }
    private String getType() {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).isPremiumAccount().equalsIgnoreCase("Premium")) {
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

    public Account searchByAccNum(int keyWord) {
        for (int i = 0; i < accounts.size(); i++) {
            int accNum = accounts.get(i).getAccountNumer();
            if (keyWord == accNum) {
                return accounts.get(i);
            }
        }
        return null;
    }

    public void displayInformation() {
        int counter = 1;
        //định dạng tiền việt
        double number = getBalance();
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String currency = currencyFormatter.format(number);

        System.out.println(getCustomerId() + "   |        " + getName() + "  |      "+ getType() +"         |     " + currency);
        for (int i = 0; i < accounts.size(); i++) {
            double numberOfAcc = accounts.get(i).getBalance();
            String currencyOfAcc = currencyFormatter.format(numberOfAcc);
            System.out.printf(counter + accounts.get(i).getAccountNumer() + " %25s %39s%n", accounts.get(i).getTypeOfAcc() + "  |", currencyOfAcc);
            counter++;
        }
    }
    public  void displaytransactions() {
        for (Account account: accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).displayTransactions();
            }
            if (account instanceof LoansAccount) {
                ((LoansAccount) account).displayTransactions();
            }
        }
    }

}

