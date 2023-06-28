package vn.funix.FX21316.java.asm04.test;

import vn.funix.FX21316.java.asm02.models.Customer;
import vn.funix.FX21316.java.asm04.dao.CustomerDao;

import java.util.List;

class DigitalBankTest {

    @org.junit.jupiter.api.Test
    void showCustomers() {
    }

    @org.junit.jupiter.api.Test
    void addCustomers() {
        List<Customer> customers = CustomerDao.list();
    }

    @org.junit.jupiter.api.Test
    void addSavingAccount() {
    }

    @org.junit.jupiter.api.Test
    void withdraw() {
    }

    @org.junit.jupiter.api.Test
    void transfers() {
    }
}