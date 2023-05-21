package vn.funix.FX21316.java.asm03.test;

import org.junit.Test;
import vn.funix.FX21316.java.asm02.models.Account;
import vn.funix.FX21316.java.asm02.models.Customer;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void validateAccount() {
        Customer customer = new Customer("Thiep", "001123123123");
        boolean vali = customer.checkAccNum(1234567);
        assertFalse(vali);
    }
    @Test
    public void isAccountExisted() {
        Customer customer = new Customer("Thiep", "001123123123");
        Account account = new Account(123456, 500000) {
            @Override
            public String getTypeOfAcc() {
                return null;
            }
        };
        customer.addAccount(account);
        boolean vali = customer.checkAccNum(123456);
        assertFalse(vali);
    }
    @Test
    public void getTotalAccountBalance() {
        Customer customer = new Customer("Thiep", "001123123123");
        Account account1 = new Account(123456, 500000) {
            @Override
            public String getTypeOfAcc() {
                return null;
            }
        };
        Account account2 = new Account(111111, 500000) {
            @Override
            public String getTypeOfAcc() {
                return null;
            }
        };
        customer.addAccount(account1);
        customer.addAccount(account2);
        double balance = customer.getBalance();
        assertEquals(balance, 1000000, 0);
    }
    @Test
    public void isCustomerPremium() {
        Customer customer = new Customer("Thiep", "001123123123");
        Account account1 = new Account(123456, 5000000) {
            @Override
            public String getTypeOfAcc() {
                return null;
            }
        };
        Account account2 = new Account(111111, 10000000) {
            @Override
            public String getTypeOfAcc() {
                return null;
            }
        };
        customer.addAccount(account1);
        customer.addAccount(account2);
        assertTrue(customer.getType().equals("Premium"));
    }
    @Test
    public void getAccountByAccountNumber() {
        Customer customer = new Customer("Thiep", "001123123123");
        Account account1 = new Account(123456, 5000000) {
            @Override
            public String getTypeOfAcc() {
                return null;
            }
        };
        Account account2 = new Account(111111, 10000000) {
            @Override
            public String getTypeOfAcc() {
                return null;
            }
        };
        customer.addAccount(account1);
        customer.addAccount(account2);
        assertEquals(customer.searchByAccNum(111111), account2);
    }
}