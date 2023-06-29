package vn.funix.FX21316.java.asm04.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import vn.funix.FX21316.java.asm02.models.Account;
import vn.funix.FX21316.java.asm02.models.Customer;
import vn.funix.FX21316.java.asm03.models.SavingsAccount;
import vn.funix.FX21316.java.asm04.dao.AccountDao;
import vn.funix.FX21316.java.asm04.dao.CustomerDao;
import vn.funix.FX21316.java.asm04.dao.TransactionDao;
import vn.funix.FX21316.java.asm04.models.DigitalBank;
import vn.funix.FX21316.java.asm04.models.ITransfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
@PrepareForTest(Customer.class) // Mô phỏng lớp Customer
public class DigitalBankTest {

    @Mock
    private CustomerDao customerDao;
    @Mock
    private AccountDao accountDao;
    @Mock
    private TransactionDao transactionDao;
    @Mock
    private Customer customer = new Customer("Thiep", "123");

    @Mock
    private SavingsAccount senderAccount = new SavingsAccount("123", "123", 100000);

    @Mock
    private SavingsAccount receiverAccount = new SavingsAccount("456", "123", 100000);;


    private DigitalBank digitalBank;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        digitalBank = new DigitalBank("123", "Thiep");
    }

    @Test
    public void testTransfers() throws IOException {
        String customerId = "123";

        // Thiết lập hành vi của CustomerDao
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerDao.list()).thenReturn(customers);

        // Thiết lập hành vi của Scanner
//        when(scanner.nextLine())
//                .thenReturn("senderAccountNumber")
//                .thenReturn("receiverAccountNumber")
//                .thenReturn("100000")
//                .thenReturn("Y");

        // Thiết lập hành vi của AccountDao
        List<Account> accounts = new ArrayList<>();
        accounts.add(senderAccount);
        accounts.add(receiverAccount);
        when(customer.getAccounts()).thenReturn(accounts);

        // Thiết lập hành vi của ITransfer
        when(senderAccount instanceof ITransfer).thenReturn(true);

        // Gọi phương thức đang được kiểm tra

        // Kiểm tra hành vi đã được gọi
        verify(customer).displayInformation();
        verify(customer).displayTransactionInformation();
        verify(senderAccount).transfer(receiverAccount, 100.0);
        verify(senderAccount).setBalance(anyDouble());
        verify(receiverAccount).setBalance(anyDouble());
        // Kiểm tra hành vi không được gọi
        verify(System.out, never()).println("Khách hàng không tồn tại!");
        verify(System.out, never()).println("Chuyển tiền đã bị hủy bỏ.");
        verify(System.out, never()).println("Không thể thực hiện giao dịch chuyển tiền.");
        // Kiểm tra hành vi không được gọi trên các mock khác
        verifyNoMoreInteractions(customerDao, customer, senderAccount, receiverAccount);
    }
}

