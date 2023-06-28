package vn.funix.FX21316.java.asm04.sevice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService {
    //định nghĩa lớp có chức năng cung cấp các dịch vụ đọc/ghi file nhị phân
    public static <T> List<T> readFile(String fileName) {
        List<T> objects = new ArrayList<>();
        try(ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    T object = (T) file.readObject();
                    objects.add(object);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException io) {
            System.out.println("IO Exception " + io.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :" + e.getMessage());
        }
        return objects;
    }
    //nhận đầu vào là đường dẫn đến thư mục và danh sách đối tượng cần lưu
    public static <T> void writeFile(String fileName, List<T> objects) {
        try(ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (T object : objects) {
                file.writeObject(object);
            }
//            System.out.println("Ghi file thành công");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
