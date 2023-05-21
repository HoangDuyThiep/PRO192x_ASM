package vn.funix.FX21316.java.asm03.test;

import org.junit.Test;
import vn.funix.FX21316.java.asm02.models.Account;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void isPremiumAccount() {
        Account account = new Account(123456, 10000000) {
            @Override
            public String getTypeOfAcc() {
                return null;
            }
        };
        String isPremium = account.isPremiumAccount();
        assertEquals(isPremium, "Premium");
    }
}