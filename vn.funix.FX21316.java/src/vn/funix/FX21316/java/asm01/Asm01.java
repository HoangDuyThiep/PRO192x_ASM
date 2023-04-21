package vn.funix.FX21316.java.asm01;

import java.util.Random;
import java.util.Scanner;

public class Asm01 {
    public static void main(String[] args) {


        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  THÔNG TIN SỐ  | Hoàng Duy Thiệp FX21316@V1.0.0 |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  1. Nhập CCCD                                   |");
        System.out.println("|  0. Thoát                                       |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("Chức năng: ");




        Scanner sc = new Scanner(System.in);

        int chucNang = sc.nextInt();
        while (true) {

            if (chucNang == 0 || chucNang == 1)
                break;
            System.out.println("+----------+---------------------------+----------+");
            System.out.println("Mời nhập lại phím chức năng: ");
            System.out.println("+----------+---------------------------+----------+");
            chucNang = sc.nextInt();
        }
        if (chucNang == 1) {
            Random rd = new Random();
            int newRd = 100 + rd.nextInt(899);
            System.out.println("+----------+---------------------------+----------+");
            System.out.println("Mã số xác thực là: " +  newRd);
            System.out.println("|  0. Thoát                                       |");
            System.out.println("+----------+---------------------------+----------+");
            System.out.println("Mời bạn nhập mã số xác thực: ");

            //kiểm tra số xác thực và thực hiện lựa chọn
            int xacThuc = -1;
            while (xacThuc != 0) {
                xacThuc = sc.nextInt();
                sc.nextLine();

                //khi mã xác thực chính xác
                if (xacThuc == newRd) {
                    System.out.println("+----------+---------------------------+----------+");
                    System.out.println("+----------+----Mã xác thực chính xác -+----------+");
                    System.out.println("|  0. Thoát                                       |");
                    System.out.println("+----------+---------------------------+----------+");
                    System.out.println("Mời nhập căn cước công dân: ");
                    String soCanCuoc = sc.nextLine();
                    Cccd cccd1 = new Cccd(soCanCuoc);


                    //vòng lặp nhập cccd
                    while (!soCanCuoc.equals("0")) {


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
                                System.out.println("+----------+---------------------------+----------+");
                                System.out.println("+----------+----Số căn cước hợp lệ-----+----------+");
                                System.out.println("| 1. Kiểm tra nơi sinh");
                                System.out.println("| 2. Kiểm tra tuôi, giới tính");
                                System.out.println("| 3. Kiểm tra số ngẫu nhiên");
                                System.out.println("| 0. thoát");
                                System.out.println("+----------+---------------------------+----------+");
                                System.out.println("Chức năng: ");
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

                                        break;
                                    case 3:
                                        //kiểm tra số ngẫu nhiên

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