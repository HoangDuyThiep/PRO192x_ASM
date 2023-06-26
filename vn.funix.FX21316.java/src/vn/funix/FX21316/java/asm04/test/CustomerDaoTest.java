package vn.funix.FX21316.java.asm04.test;

import org.junit.jupiter.api.Test;
import vn.funix.FX21316.java.asm02.models.Customer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class CustomerDaoTest {

    @Test
    void save() {
//        List<Customer> customers = new ArrayList<>();
//        Customer cus1 = new Customer("asdads", "123");
//        Customer cus2 = new Customer("asdadsas", "1234");
//        customers.add(cus1);
//        customers.add(cus2);
//        try {
//            CustomerDao.save(customers);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        for (Customer customer : customers) System.out.println(customer);
        List<Customer> customers = new ArrayList<>();
        Customer cus1 = new Customer("asdads", "123");
        Customer cus2 = new Customer("asdadsas", "1234");
        customers.add(cus1);
        customers.add(cus2);

        try (FileOutputStream fos = new FileOutputStream("customers.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(customers);
            System.out.println("Ghi file thành công");
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi ghi file: " + e.getMessage(), e);
        }
    }

    @Test
    void list() {
        List<Customer> customers;
        try (FileInputStream fis = new FileInputStream("customers.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            customers = (List<Customer>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Lỗi khi đọc file: " + e.getMessage(), e);
        }

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
    @Test
    void testIO() {
        Customer customer = new Customer("John Doe", "12345");

        // Ghi đối tượng vào file
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("customer.dat")))) {
            oos.writeObject(customer);
            System.out.println("Ghi đối tượng thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Đọc đối tượng từ file
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("customer.dat")))) {
            Customer readCustomer = (Customer) ois.readObject();
            System.out.println("Đọc đối tượng thành công:");
            System.out.println(readCustomer);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}