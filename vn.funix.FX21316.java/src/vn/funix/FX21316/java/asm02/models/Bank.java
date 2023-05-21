package vn.funix.FX21316.java.asm02.models;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    private String id;
    protected ArrayList<Customer> customers;

    public Bank() {
        this.customers = new ArrayList<Customer>();
        this.id = String.valueOf(UUID.randomUUID());
    }

    public void addCuctomer(Customer newCuctomer) {
            customers.add(newCuctomer);
    }
    public boolean isCustomerExisted(Customer newCustomer) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(newCustomer.getCustomerId())) {
                return true;
            }
        }
        return false;
    }
    public boolean validateCustomerId(String canCuocCongDan) {
        Cccd cccd = new Cccd(canCuocCongDan);
        if (cccd.checkcccd() && cccd.checkMaTinh() && cccd.checkGioiTinh() && cccd.checkSoNgayNhien()) {
            return true;
        }
        return false;
    }
    //hàm tìm theo ten khác hàng
    public Customer searchByName(String keyword) {
        for (int i = 0; i < customers.size(); i++) {
            String name = customers.get(i).getName();
            if (name.toLowerCase().contains(keyword.toLowerCase())) {
                return customers.get(i);
            }
        }
        return null;
    }

    public Customer searchById(String customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equals(customerId)) {
                return customers.get(i);
            }
        }
        return null;
    }

    public void getCustomers() {
        for (int i = 0; i < customers.size(); i++) {
            customers.get(i).displayInformation();
        }
    }

}
