package vn.funix.FX21316.java.asm03.models;

import vn.funix.FX21316.java.asm02.models.Bank;

public class DigitalBank extends Bank {
    public DigitalCustomer getCustomerById(String customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equals(customerId)) {
                return (DigitalCustomer) customers.get(i);
            }
        }
        return null;
    }

    public void addCustomer(String customerId, String name) {
        DigitalCustomer newCuctomer = new DigitalCustomer(name, customerId);
        customers.add(newCuctomer);
    }
}
