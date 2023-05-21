package vn.funix.FX21316.java.asm03.test;

import org.junit.Test;
import vn.funix.FX21316.java.asm02.models.Customer;
import vn.funix.FX21316.java.asm03.models.DigitalBank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DigitalBankTest {

    @Test
    public void getCustomerById() {
        DigitalBank bank = new DigitalBank();
        Customer customer1 = new Customer("Thiep", "001123123123");
        bank.addCuctomer(customer1);
        Customer customer2 = new Customer("Duy", "002123123123");
        bank.addCuctomer(customer2);
        assertEquals(bank.getCustomerById("002123123123"), customer2);
    }
    @Test
    public void isCustomerExistedTest() {
        DigitalBank bank = new DigitalBank();
        Customer customer1 = new Customer("Thiep", "001123123123");
        bank.addCuctomer(customer1);
        Customer customer2 = new Customer("Duy", "002123123123");
        bank.addCuctomer(customer2);
        assertTrue(bank.isCustomerExisted(customer1));
    }
}