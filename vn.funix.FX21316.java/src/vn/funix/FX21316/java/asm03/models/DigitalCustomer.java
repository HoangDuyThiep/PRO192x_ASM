package vn.funix.FX21316.java.asm03.models;

import vn.funix.FX21316.java.asm02.models.Account;
import vn.funix.FX21316.java.asm02.models.Customer;

public class DigitalCustomer extends Customer {
    public DigitalCustomer(String name, String customerId) {
        super(name, customerId);
    }

    public void withdraw(int accNumber, double amount) {
        Account accountNumber = searchByAccNum(accNumber);
        if (accountNumber instanceof SavingsAccount) {
            if (((SavingsAccount) accountNumber).isAccepted(amount)) {
                ((SavingsAccount) accountNumber).withdraw(amount);
                ((SavingsAccount) accountNumber).log(amount);
            }
        }
        if (accountNumber instanceof LoansAccount) {
            if (((LoansAccount) accountNumber).isAccepted(amount)) {
                ((LoansAccount) accountNumber).withdraw(amount);
                ((LoansAccount) accountNumber).log(amount);
            }
        }
    }
}
