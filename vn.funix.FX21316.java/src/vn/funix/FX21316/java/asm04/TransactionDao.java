package vn.funix.FX21316.java.asm04;

import vn.funix.FX21316.java.asm03.models.Transaction;

import java.io.IOException;
import java.util.List;

public class TransactionDao {
    private final static String FILE_PATH = "store//transactions.dat";
    // lưu danh sách khách hàng vào file
    public static void save(List<Transaction> transactions) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, transactions);
    }
    // lấy ra danh sách khách hàng từ file
    public static List<Transaction> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

}
