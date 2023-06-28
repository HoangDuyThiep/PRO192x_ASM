package vn.funix.FX21316.java.asm04.dao;

import vn.funix.FX21316.java.asm02.models.Customer;
import vn.funix.FX21316.java.asm04.sevice.BinaryFileService;

import java.io.IOException;
import java.util.List;

// định nghĩa lớp trung gian thao tác với file để lấy dữ liệu và thêm mới, cập nhật dữ liệu
public class CustomerDao {
    private final static String FILE_PATH = "store//customers.dat";
    // lưu danh sách khách hàng vào file
    public static void save(List<Customer> customers) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, customers);
    }
    // lấy ra danh sách khách hàng từ file
    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
