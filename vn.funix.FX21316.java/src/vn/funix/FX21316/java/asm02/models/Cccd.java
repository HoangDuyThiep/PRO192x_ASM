package vn.funix.FX21316.java.asm02.models;

import java.util.HashMap;
import java.util.ArrayList;

public class Cccd {
    private ArrayList<String> gioiTinh;
    private HashMap<String, String> maTinh;
    private String cccd;




    public Cccd(String cccd) {
        gioiTinh = new ArrayList<String>();
        gioiTinh.add("0");
        gioiTinh.add("1");
        gioiTinh.add("2");
        gioiTinh.add("3");
        maTinh = new HashMap<>();
        maTinh.put("001", "Hà Lội");
        maTinh.put("002", "Hà Giang");
        maTinh.put("004", "Cao Bằng");
        maTinh.put("006", "Bắc Kạn");
        maTinh.put("008", "Tuyên Quang");
        maTinh.put("010", "Lào Cai");
        maTinh.put("011", "Điện Biên");
        maTinh.put("012", "Lai Châu");
        maTinh.put("014", "Sơn La");
        maTinh.put("015", "Yên Bái");
        maTinh.put("017", "Hòa Bình");
        maTinh.put("019", "Thái Nguyên");
        maTinh.put("020", "Lạng Sơn");
        maTinh.put("022", "Quảng Ninh");
        maTinh.put("024", "Bắc Giang");
        maTinh.put("025", "Phú Thọ");
        maTinh.put("026", "Vĩnh Phúc");
        maTinh.put("027", "Bắc Ninh");
        maTinh.put("030", "Hải Dương");
        maTinh.put("031", "Hải Phòng");
        maTinh.put("033", "Hưng Yên");
        maTinh.put("034", "Thái Bình");
        maTinh.put("035", "Hà Nam");
        maTinh.put("036", "Nam Định");
        maTinh.put("037", "Ninh Bình");
        maTinh.put("038", "Thanh Hóa");
        maTinh.put("040", "Nghệ An");
        maTinh.put("042", "Hà Tĩnh");
        maTinh.put("044", "Quảng Bình");
        maTinh.put("045", "Quảng Trị");
        maTinh.put("046", "Thừa Thiên Huế");
        maTinh.put("048", "Đà Nẵng");
        maTinh.put("049", "Quảng Nam");
        maTinh.put("051", "Quảng Ngãi");
        maTinh.put("052", "Bình Định");
        maTinh.put("054", "Phú Yên");
        maTinh.put("056", "Khánh Hòa");
        maTinh.put("058", "Ninh Thuận");
        maTinh.put("060", "Bình Thuận");
        maTinh.put("062", "Kon Tum");
        maTinh.put("064", "Gia Lai");
        maTinh.put("066", "Đắk Lắk");
        maTinh.put("067", "Đắk Nông");
        maTinh.put("068", "Lâm Đồng");
        maTinh.put("070", "Bình Phước");
        maTinh.put("072", "Tây Ninh");
        maTinh.put("074", "Bình Dương");
        maTinh.put("075", "Đồng Nai");
        maTinh.put("077", "Bà Rịa - Vũng Tàu");
        maTinh.put("079", "Hồ Chí Minh");
        maTinh.put("080", "Long An");
        maTinh.put("082", "Tiền Giang");
        maTinh.put("083", "Bến Tre");
        maTinh.put("084", "Trà Vinh");
        maTinh.put("086", "Vĩnh Long");
        maTinh.put("087", "Đồng Tháp");
        maTinh.put("089", "An Giang");
        maTinh.put("091", "Kiên Giang");
        maTinh.put("092", "Cần Thơ");
        maTinh.put("093", "Hậu Giang");
        maTinh.put("094", "Sóc Trăng");
        maTinh.put("095", "Bạc Liêu");
        maTinh.put("096", "Cà Mau");
        this.cccd = cccd;
    }

    //các hamg để check cccd -------------------------------------
    public boolean checkcccd() {
        if (cccd.length() == 12) {
            return true;
        } else return false;
    }
    public boolean checkMaTinh() {
        String tacMaTinh = cccd.substring(0, 3);
        if (maTinh.containsKey(tacMaTinh)) {
            return true;
        } else return false;
    }


    public boolean checkGioiTinh() {
        String tacGioiTinh = cccd.substring(3, 4);
        if (gioiTinh.contains(tacGioiTinh)) {
            return true;
        } else return false;
    }

    //hàm xuất thông tin từ cccd ------------------------------
    public String tinh() {
        String tacMaTinh = cccd.substring(0, 3);
        return  maTinh.get(tacMaTinh);
    }
    public void GTinhNSinh () {
        String tacGioiTinh = cccd.substring(3, 4);
        switch (tacGioiTinh) {
            case "0":
                System.out.println("giới tính: Nam | 19" + cccd.substring(4, 6));
                break;
            case "1":
                System.out.println("giới tính: Nữ | 19" + cccd.substring(4, 6));
                break;
            case "2":
                System.out.println("giới tính: Nam | 20" + cccd.substring(4, 6));
                break;
            case "3":
                System.out.println("giới tính: Nữ | 20" + cccd.substring(4, 6));
                break;
            default:
                System.out.println("Năm sinh không hợp lý rồi! có thể cụ đã mất hoặc đứa trẻ chưa được sinh!");
                break;
        };
    }

    public void soNgauNhien() {
        System.out.println("Số ngẫu nhiên: " + cccd.substring(6));
    }


}
