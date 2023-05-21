package vn.funix.FX21316.java.asm03.models;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utils {
    public static String getDivider() {
        return "------------+--------------------+-----------------";
    }

    // Phương thức để trả về thời gian hiện tại dưới dạng chuỗi theo định dạng yyyy-MM-dd HH:mm:ss
    public static String getDateTime() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentDate);
    }

    // Phương thức để định dạng số tiền trong tệ VNĐ
    public static String formatBalance(double balance) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        return format.format(balance);
    }
}
