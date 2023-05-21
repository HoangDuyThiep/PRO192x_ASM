package vn.funix.FX21316.java.asm03.test;

import vn.funix.FX21316.java.asm03.models.SavingsAccount;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SavingsAccountTest {
    @org.junit.Test
    public void withdraw() {
        SavingsAccount savingsAccount = new SavingsAccount(111111, 5000000);
        boolean expected = savingsAccount.withdraw(500000);
        assertTrue(expected);
    }

    @org.junit.Test
    public void isAccepted() {
        SavingsAccount savingsAccount = new SavingsAccount(111111, 5000000);
        boolean expected = savingsAccount.isAccepted(45000);
        assertFalse(expected);
    }
}