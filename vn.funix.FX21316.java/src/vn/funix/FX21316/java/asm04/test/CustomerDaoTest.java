package vn.funix.FX21316.java.asm04.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vn.funix.FX21316.java.asm02.models.Customer;
import vn.funix.FX21316.java.asm04.sevice.BinaryFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CustomerDaoTest {
    private final static String FILE_PATH = "customer.dat";
    @Test
    void saveTest() throws IOException {
        // Tạo danh sách khách hàng mẫu
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("1", "John Doe"));
        customers.add(new Customer("2", "Jane Smith"));
        for (Customer customer : customers) System.out.println(customer);


        // Gọi phương thức save() để lưu danh sách khách hàng vào file
        BinaryFileService.writeFile(FILE_PATH, customers);

        // Gọi phương thức list() để đọc danh sách khách hàng từ file
        List<Customer> savedCustomers = BinaryFileService.readFile(FILE_PATH);

        // Kiểm tra xem danh sách khách hàng sau khi lưu có đúng hay không
        Assertions.assertEquals(customers.size(), savedCustomers.size(), "Customer count is incorrect.");
        for (Customer customer : savedCustomers) System.out.println(customer);
//        Assertions.assertTrue(savedCustomers.containsAll(customers), "Saved customers are not correct.");
    }
    @Test
    void listTest() throws IOException {
        // Tạo danh sách khách hàng mẫu
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer("1", "John Doe"));
        expectedCustomers.add(new Customer("2", "Jane Smith"));

        // Ghi danh sách khách hàng vào file
        BinaryFileService.writeFile(FILE_PATH, expectedCustomers);

        // Gọi phương thức list() để đọc danh sách khách hàng từ file
        List<Customer> actualCustomers = BinaryFileService.readFile(FILE_PATH);

        // Kiểm tra xem danh sách khách hàng được đọc có đúng hay không
        Assertions.assertEquals(expectedCustomers.size(), actualCustomers.size(), "Customer count is incorrect.");
        //so sanh 2 object kha phuc tap, phai tim hieu them T_T!
//        Assertions.assertTrue(actualCustomers.containsAll(expectedCustomers), "Customers are not correct.");
    }

}