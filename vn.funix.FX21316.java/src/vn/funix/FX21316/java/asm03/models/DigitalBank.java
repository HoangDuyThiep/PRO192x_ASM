package vn.funix.FX21316.java.asm03.models;

import vn.funix.FX21316.java.asm02.models.Bank;
import vn.funix.FX21316.java.asm02.models.Customer;

public class DigitalBank extends Bank {
    public Customer getCustomerById(String customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equals(customerId)) {
                return customers.get(i);
            }
        }
        return null;
    }

    public void addCustomer(String customerId, String name) {
        Customer newCuctomer = new Customer(name, customerId);
        customers.add(newCuctomer);
    }
}
