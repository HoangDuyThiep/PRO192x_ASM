package vn.funix.FX21316.java.asm01;

import java.util.Random;
import java.util.Scanner;

public class Asm01 {

    //hiện thị mở đầu
    static void hienThiDau() {
        System.out.println("|  Chương trình kiểm tra thông tin cá nhân thông  |");
        System.out.println("|  qua số căn cước công dân                       |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  THÔNG TIN SỐ  | Hoàng Duy Thiệp FX21316@V1.0.0 |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  1. Nhập CCCD                                   |");
        System.out.println("|  0. Thoát                                       |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("Chức năng: ");
    }


    //tạo mã số xác thực
    static int maXacThuc() {
        Random rd = new Random();
        int newRd = 100 + rd.nextInt(899);
        return newRd;
    }
    //hiện thị mã xác thục cho người dùng
    static void hienThiMXT(int newRd) {
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("Mã số xác thực là: " +  newRd);
        System.out.println("|  0. Thoát                                       |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("Mời bạn nhập mã số xác thực: ");
    }
    //hiển thị khi mã xác thực chính xác
    static void chinhXacMXT() {
        System.out.println("+----------+----Mã xác thực chính xác -+----------+");
        System.out.println("|  0. Thoát                                       |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("Mời nhập căn cước công dân: ");
    }
    static void loiChucNang() {
        System.out.println("| Lỗi nhập lựa chọn chức năng Mentor ạ");
        System.out.println("| Mentor nhập 0 hoặc 1 thôi ạ, em cô đơn quá! T_T");

    }
    static void hienThiChucNang() {
        System.out.println("+----------+----Số căn cước hợp lệ-----+----------+");
        System.out.println("| 1. Kiểm tra nơi sinh");
        System.out.println("| 2. Kiểm tra tuôi, giới tính");
        System.out.println("| 3. Kiểm tra số ngẫu nhiên");
        System.out.println("| 0. thoát");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("Chức năng: ");
    }

    //-------------------------------------------main----------------------------
    public static void main(String[] args) {

        hienThiDau();

        Scanner sc = new Scanner(System.in);
        int chucNang;
        //xử lý khi nhập sai phím chức năng
        try {
            chucNang = sc.nextInt();
        }
        catch (Exception ex) {
            loiChucNang();
            chucNang = 2;
        }
//        sc.nextLine();

        //kiểm tra phím chức năng của lựa chọn đầu
        while (true) {
            if (chucNang == 0 || chucNang == 1) {
                break;
            }
            System.out.println("+----------+---------------------------+----------+");
            System.out.println("Mời nhập lại phím chức năng: ");
            System.out.println("+----------+---------------------------+----------+");
            try {
                sc.nextLine();
                chucNang = sc.nextInt();
            }
            catch (Exception ex) {
                loiChucNang();
                chucNang = 2;

            }

        }

        if (chucNang == 1) {
            //tạo mã xác thực
            int newRd = maXacThuc();
            hienThiMXT(newRd);

            //kiểm tra số xác thực và thực hiện lựa chọn
            int xacThuc = -1;
            while (xacThuc != 0) {
                //xử lý khi gặp lỗi mã xác thực
                try {
                    xacThuc = sc.nextInt();
                }
                catch (Exception ex) {
                    System.out.println("| Em biết ngay Mentor cố tình nhập sai mà!");
                    System.out.println("| Mentor xem lại "+ newRd + " giúp em ạ! T_T");
                    System.out.println("| Em chưa có bạn gái mà cày code thế này chắc ế tới chớt! T_T");
                    xacThuc = 2;
                }

                sc.nextLine();


                //khi mã xác thực chính xác
                if (xacThuc == newRd) {
                    chinhXacMXT();
                    String soCanCuoc = sc.nextLine();
                    Cccd cccd1 = new Cccd(soCanCuoc);


                    //vòng lặp nhập cccd
                    while (!soCanCuoc.equals("0")) {
                        //kiểm tra căn cước công dân hợp lệ
                        while (!cccd1.checkcccd() || !cccd1.checkMaTinh() || !cccd1.checkGioiTinh()){
                            System.out.println("Số căn cước công dân không hợp lệ, vui lòng nhập lại");
                            System.out.println("| 0. thoát");
                            soCanCuoc = sc.nextLine();
                            cccd1 = new Cccd(soCanCuoc);

                            //thoát nếu nhập 0
                            if (soCanCuoc.equals("0")) {
                                break;
                            }
                        }

                        //nếu số căn cước hợp lệ
                        if (cccd1.checkcccd() && cccd1.checkMaTinh() && cccd1.checkGioiTinh()) {
                            int luaChon = -1;
                            while (luaChon != 0) {
                                hienThiChucNang();
                                luaChon = sc.nextInt();

                                //các lựa chon chức năng
                                switch (luaChon){
                                    case 0:
                                        System.out.println("+----------+---------------------------+----------+");
                                        System.out.println("Chương trình kết thúc, cảm ơn đã sử dụng!");
                                        System.out.println("+----------+---------------------------+----------+");
                                        break;
                                    case 1:
                                        //kiểm tra nơi sinh
                                        System.out.println("+----------+---------------------------+----------+");
                                        System.out.println("Nơi sinh: " + cccd1.tinh());
                                        System.out.println("+----------+---------------------------+----------+");
                                        break;
                                    case 2:
                                        // kiểm tra giới tính, tuổi
                                        System.out.println("+----------+---------------------------+----------+");
                                        cccd1.GTinhNSinh();
                                        System.out.println("+----------+---------------------------+----------+");
                                        break;
                                    case 3:
                                        //kiểm tra số ngẫu nhiên
                                        System.out.println("+----------+---------------------------+----------+");
                                        cccd1.soNgauNhien();
                                        System.out.println("+----------+---------------------------+----------+");
                                        break;

                                    default:
                                        System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
                                        break;
                                }

                            }




                            //cccd = 0 sẽ thoát, sai sẽ nhập lại
                        }

                        //thoát vòng lặp nhập số căn cước
                        soCanCuoc = "0";
                    }

                    //cắt vòng lặp và thoát hết ra ngoài khi nhập cccd = 0;
                    xacThuc = 0;




                //khi mã xác thực không chính xác
                } else if (xacThuc != 0){
                    System.out.println("Mã số xác thực sai, vui lòng nhập lại:");
                }



                //mã xác thực bằng 0 sẽ thoát
            }
        }










    }
}