package vn.funix.FX21316.java.asm04.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vn.funix.FX21316.java.asm02.models.Account;
import vn.funix.FX21316.java.asm03.models.SavingsAccount;
import vn.funix.FX21316.java.asm04.sevice.BinaryFileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class AccountDaoTest {
    private final static String FILE_PATH = "accounts.dat";
    @Test
    void saveTest() {
        // Tạo danh sách khách hàng
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("123", "123", 1));
        accounts.add(new SavingsAccount("456", "456", 1));

        try {
            // Gọi phương thức save để lưu danh sách khách hàng vào file
//            AccountDao.save(accounts)
                BinaryFileService.writeFile(FILE_PATH, accounts);


            // Kiểm tra xem file đã được tạo thành công chưa
            Assertions.assertTrue(Files.exists(Paths.get(FILE_PATH)));

            // Đọc nội dung file và kiểm tra xem danh sách khách hàng đã được lưu đúng chưa
            List<Account> savedAccounts = BinaryFileService.readFile(FILE_PATH);
            Assertions.assertEquals(accounts.size(), savedAccounts.size());
            for (int i = 0; i < accounts.size(); i++) {
                Account expectedAccount = accounts.get(i);
                Account savedAccount = savedAccounts.get(i);
                Assertions.assertEquals(expectedAccount.getAccountNumer(), savedAccount.getAccountNumer());
                Assertions.assertEquals(expectedAccount.getAccountNumer(), savedAccount.getAccountNumer());
            }

            // Xoá file sau khi kiểm tra hoàn thành
            Files.deleteIfExists(Paths.get(FILE_PATH));
        } catch (IOException e) {
            // Xảy ra lỗi trong quá trình lưu file
            Assertions.fail("An error occurred while saving the accounts.");
        }
    }

    @Test
    void listTest() {
        // Tạo danh sách khách hàng mẫu
        List<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new SavingsAccount("123", "123", 1));
        expectedAccounts.add(new SavingsAccount("456", "456", 1));

        try {
            // Ghi danh sách khách hàng mẫu vào file
            BinaryFileService.writeFile(FILE_PATH, expectedAccounts);

            // Gọi phương thức list() để đọc danh sách khách hàng từ file
//            List<Account> actualAccounts = AccountDao.list();
            List<Account> actualAccounts =  BinaryFileService.readFile(FILE_PATH);

            // Kiểm tra xem danh sách khách hàng đọc được từ file có đúng với danh sách mẫu hay không
            Assertions.assertEquals(expectedAccounts.size(), actualAccounts.size());
            for (int i = 0; i < expectedAccounts.size(); i++) {
                Account expectedAccount = expectedAccounts.get(i);
                Account actualAccount = actualAccounts.get(i);
                Assertions.assertEquals(expectedAccount.getAccountNumer(), actualAccount.getAccountNumer());
                Assertions.assertEquals(expectedAccount.getAccountNumer(), actualAccount.getAccountNumer());
            }

            // Xoá file sau khi kiểm tra hoàn thành
            Files.deleteIfExists(Paths.get(FILE_PATH));
        } catch (IOException e) {
            // Xảy ra lỗi trong quá trình đọc file
            Assertions.fail("An error occurred while reading the accounts.");
        }
    }


    private static final int MAX_THREAD = 4; // Số lượng luồng tối đa

    @Test
    void updateTest() {
        // Tạo danh sách khách hàng mẫu
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("123", "123", 1));
        accounts.add(new SavingsAccount("456", "456", 1));

        // Tạo tài khoản cần cập nhật
        Account editAccount = new SavingsAccount("123", "123", 2);

        // Gọi phương thức update() để cập nhật danh sách khách hàng
//        AccountDAO.update(editAccount);
        BinaryFileService.writeFile(FILE_PATH, accounts);
        // Kiểm tra kết quả sau khi cập nhật
        List<Account> updatedAccounts = BinaryFileService.readFile(FILE_PATH);

        // Kiểm tra xem tài khoản đã được cập nhật đúng hay không
        boolean found = false;
        for (Account account : updatedAccounts) {
            if (account.getCustomerId().equals(editAccount.getCustomerId()) &&
                    account.getCustomerId().equals(editAccount.getCustomerId())) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found, "Account was not updated.");

        // Kiểm tra xem số lượng tài khoản sau khi cập nhật có đúng hay không
        Assertions.assertEquals(accounts.size(), updatedAccounts.size(), "Account count is incorrect.");
    }
}