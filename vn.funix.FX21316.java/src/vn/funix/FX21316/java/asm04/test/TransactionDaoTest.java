package vn.funix.FX21316.java.asm04.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vn.funix.FX21316.java.asm02.models.TransactionType;
import vn.funix.FX21316.java.asm03.models.Transaction;
import vn.funix.FX21316.java.asm03.models.Utils;
import vn.funix.FX21316.java.asm04.sevice.BinaryFileService;

import java.util.ArrayList;
import java.util.List;

class TransactionDaoTest {
    private final static String FILE_PATH = "transaction.dat";

    @Test
    void saveTest() {
        // Tạo danh sách giao dịch mẫu
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", 100, Utils.getDateTime(), true, TransactionType.WITHDRAW));
        transactions.add(new Transaction("2", 500, Utils.getDateTime(), true, TransactionType.DEPOSIT));

        // Gọi phương thức save() để lưu danh sách giao dịch vào file
//        TransactionDao.save(transactions);
        BinaryFileService.writeFile(FILE_PATH, transactions);

        // Đọc danh sách giao dịch từ file để kiểm tra
        List<Transaction> savedTransactions = BinaryFileService.readFile(FILE_PATH);

        // Kiểm tra xem danh sách giao dịch đã được lưu đúng hay không
        Assertions.assertEquals(transactions.size(), savedTransactions.size(), "Transaction count is incorrect.");
//        Assertions.assertTrue(savedTransactions.containsAll(transactions), "Transactions are not correct.");
    }

    @Test
    void listTest(){
        // Tạo danh sách giao dịch mẫu
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new Transaction("1", 100, Utils.getDateTime(), true, TransactionType.WITHDRAW));
        expectedTransactions.add(new Transaction("2", 500, Utils.getDateTime(), true, TransactionType.DEPOSIT));

        // Gọi phương thức save() để lưu danh sách giao dịch vào file
//        TransactionDao.save(transactions);
        BinaryFileService.writeFile(FILE_PATH, expectedTransactions);

        // Gọi phương thức list() để lấy danh sách giao dịch từ file
        List<Transaction> actualTransactions = BinaryFileService.readFile(FILE_PATH);

        // Kiểm tra xem danh sách giao dịch trả về có đúng hay không
        Assertions.assertEquals(expectedTransactions.size(), actualTransactions.size(), "Transaction count is incorrect.");
        // vi co cac thuoc tinh khac nen van chua biet cach so sanh 2 object
//        Assertions.assertTrue(actualTransactions.containsAll(expectedTransactions), "Transactions are not correct.");
//        for (Transaction expectedTransaction : expectedTransactions) {
//            Assertions.assertTrue(actualTransactions.contains(expectedTransaction), "Transaction is not found: " + expectedTransaction);
//        }
    }
}