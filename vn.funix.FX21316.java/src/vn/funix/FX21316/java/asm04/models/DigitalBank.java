package vn.funix.FX21316.java.asm04.models;

import vn.funix.FX21316.java.asm02.models.Cccd;
import vn.funix.FX21316.java.asm02.models.Customer;
import vn.funix.FX21316.java.asm04.dao.CustomerDao;
import vn.funix.FX21316.java.asm04.exception.CustomerIdNotValidException;
import vn.funix.FX21316.java.asm04.sevice.TextFileService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DigitalBank extends Bank{
    public DigitalBank(String bankID, String bankName) {
        super(bankID, bankName);
    }
    // đọc dữ liệu từ file sử dụng hàm CustomerDao.list()
    public void showCustomers() {
        List<Customer> customers = CustomerDao.list();

        if (customers.isEmpty()) {
            System.out.println("Chưa có khách hàng nào trong danh sách!");
        } else {
            for (Customer customer : customers) {
                customer.displayInformation();
            }
        }
    }
    // đọc dữ liệu từ file, dữ liệu từ file bao gồm nhiều khách hàng, kiểm tra dữ liệu từng khách hàng
    public void addCustomers(String fileName) {
        List<List<String>> fileDatas = TextFileService.readFile(fileName);
        List<Customer> customers = CustomerDao.list();

        for (List data : fileDatas) {
            Customer customer = new  Customer(data);
            if (customer != null) {
                // nhận lỗi trong quá trình kiểm tra customerId
                try {
                    if (checkIdCustomer(customer.getCustomerId())) {
                        if (!isCustomerExisted(customers, customer.getCustomerId())) {
                            customers.add(customer);
                            System.out.println("Thêm khách hàng thành công: " + customer.getName());
                        } else {
                            System.out.println("Số ID đã tồn tại cho khách hàng: " + customer.getName());
                        }
                    }
//                    else {
//                        System.out.println("Số ID không hợp lệ cho khách hàng: " + customer.getName());
//                    }
                } catch (CustomerIdNotValidException e) {
                    System.out.println(e.getMessage() + customer.getName());
                }
            }
        }


        try {
            CustomerDao.save(customers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // tạo mới một tài khoản ATM
    public void addSavingAccount(Scanner scanner, String customerId) {
        List<Customer> customers = CustomerDao.list();
        if (isCustomerExisted(customers, customerId)) {
            Customer customer = getCustomerById(customers, customerId);
            if (customer != null) {
                customer.input(scanner);
                customer.displayInformation();
            }
        } else {
            System.out.println("Khách hàng không tồn tại!");
        }

    }
    // rút tiền
    public void withdraw(Scanner scanner, String customerId) {
        List<Customer> customers = CustomerDao.list();
        if (isCustomerExisted(customers, customerId)) {
            Customer customer = getCustomerById(customers, customerId);
            if (customer != null) {
                customer.withdraw(scanner);
                customer.displayInformation();

            }
        } else {
            System.out.println("Khách hàng không tồn tại!");
        }
    }
    // lấy ra một customer có id bằng id cho trước
    private Customer getCustomerById(List<Customer> customers, String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
    // chuyển tiền
    public void transfers(Scanner scanner, String customerId) {
        List<Customer> customers = CustomerDao.list();
        if (isCustomerExisted(customers, customerId)) {
            Customer customer = getCustomerById(customers, customerId);
            if (customer != null) {
                customer.transfers(scanner);
                customer.displayTransactionInformation();
            }
        } else {
            System.out.println("Khách hàng không tồn tại!");
        }
    }
    // kiểm customerID khi tạo mới, dùng CustomerIdNotValidException
    private boolean checkIdCustomer(String customerId) throws CustomerIdNotValidException {
        // Logic để kiểm tra tính hợp lệ của số ID khách hàng
        Cccd cccd = new Cccd(customerId);
        if (cccd.checkcccd() && cccd.checkMaTinh() && cccd.checkGioiTinh() && cccd.checkSoNgayNhien()) {
            return true;
        } else {
            throw new CustomerIdNotValidException("Số ID không hợp lệ cho khách hàng: ");
        }
    }
    public void showAccOfCustomer(Scanner scanner, String customerId) {
        List<Customer> customers = CustomerDao.list();
        if (isCustomerExisted(customers, customerId)) {
            Customer customer = getCustomerById(customers, customerId);
            if (customer != null) {
                customer.displayTransactionInformation();
            }
        } else {
            System.out.println("Khách hàng không tồn tại!");
        }
    }
    // kiểm tra khách hàng đã có trong ngân hàng hay chưa
    private boolean isCustomerExisted(List<Customer> customers, String customerId) {
        return customers.stream()
                .anyMatch(customer -> customer.getCustomerId().equals(customerId));
//        for (Customer customer : customers) {
//            if (customer.getCustomerId().equals(customerId)) {
//                return true;
//            }
//        }
//        return false;
    }


}
