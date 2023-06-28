package vn.funix.FX21316.java.asm03.test;

import org.junit.Test;
import vn.funix.FX21316.java.asm02.models.Bank;

import static org.junit.Assert.assertFalse;

public class BankTest {

    @Test
    public void validateCustomerIdTest() {
        vn.funix.FX21316.java.asm02.models.Bank bank = new Bank();
        assertFalse(bank.validateCustomerId("00112312312a"));
    }
}