package vn.funix.FX21316.java.asm04.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.when;

public class CustomerDaoTest {

    @Mock
    private Scanner mockScanner;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTransfers_CustomerExisted_TransferSuccessful() {
        // Tạo dữ liệu giả cho kiểm thử
        String customerId = "123";

        // Tạo danh sách khách hàng giả
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer("123", "John Doe");
        customers.add(customer);

        // Thiết lập hành vi cho Scanner giả
        String accountNumber = "123456";
        String receiverAccountNumber = "789012";
        String transferAmount = "100";
        String confirmation = "Y";
        String userInput = accountNumber + "\n" + receiverAccountNumber + "\n" + transferAmount + "\n" + confirmation;
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        when(mockScanner.nextLine()).thenReturn(accountNumber, receiverAccountNumber, transferAmount, confirmation);
        System.setIn(inputStream);

        // Gọi phương thức transfers
        CustomerDao.transfers(mockScanner, customerId);

        // Kiểm tra kết quả
        // Assert rằng khách hàng đã được hiển thị thông tin
        Assertions.assertTrue(customer.isInformationDisplayed());

        // Assert rằng giao dịch chuyển tiền đã được thực hiện thành công
        Assertions.assertTrue(customer.isTransactionSuccessful());

        // Assert rằng thông tin giao dịch đã được hiển thị
        Assertions.assertTrue(customer.isTransactionInformationDisplayed());
    }

    @Test
    public void testTransfers_CustomerNotExisted_DisplayErrorMessage() {
        // Tạo dữ liệu giả cho kiểm thử
        String customerId = "456";

        // Tạo danh sách khách hàng giả
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer("123", "John Doe");
        customers.add(customer);

        // Gọi phương thức transfers
        CustomerDao.transfers(mockScanner, customerId);

        // Kiểm tra kết quả
        // Assert rằng thông báo lỗi được hiển thị khi khách hàng không tồn tại
        Assertions.assertEquals("Khách hàng không tồn tại!", systemOut());
    }

    // Hàm hỗ trợ để chụp thông điệp xuất ra từ System.out.println()
    private String systemOut() {
        return outputStreamCaptor.toString().trim();
    }
}
