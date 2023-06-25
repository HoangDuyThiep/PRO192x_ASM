package vn.funix.FX21316.java.asm04;

import vn.funix.FX21316.java.asm03.models.DigitalBank;

import java.util.Scanner;

public class Asm04 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final vn.funix.FX21316.java.asm03.models.DigitalBank activeBank = new DigitalBank();


    //giới thiệu chương trình
    static void gioiThieu() {
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  Chào bạn đến với ứng dụng Ngân hàng số.        |");
        System.out.println("|  Ứng dụng hỗ trợ lưu trữ và tìm kiếm thông tin  |");
        System.out.println("|  khách hàng.                                    |");
    }

    //hiển thị đầu
    static  void menu() {
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  NGÂN HÀNG SỐ  | Hoàng Duy Thiệp FX21316@V4.0.0 |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  1. Xem danh sách khách hàng                    |");
        System.out.println("|  2. Nhập danh sách khách hàng                   |");
        System.out.println("|  3. Thêm tài khoản ATM                          |");
        System.out.println("|  4. Chuyển tiền                                 |");
        System.out.println("|  5. Rút tiền                                    |");
        System.out.println("|  6. Tra cứu lịch sử giao dịch                   |");
        System.out.println("|  0. Thoát                                       |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.print("Chức năng: ");
    }
    //kiểm tra nhập phím chức năng, sử dụng try catch
    static int ktChucNang(int choice) {
        Scanner sc = new Scanner(System.in);
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            choice = -1;
        }

        if (choice < 0 || choice > 5) {
            System.out.println("Phím chức năng là số từ 0 đến 5, vui lòng nhập lại phím chức năng");
        }
        return choice;
    }


    // main
    public static void main(String[] args) {
        gioiThieu();
        int choice = -1;
        //vòng lặp lựa chon chức năng
        while (choice != 0) {
            menu();

            choice = ktChucNang(choice);


            switch (choice) {
                case 1:
//                    showCustomer();
                    break;
                case 2:
//                    addAccountATM(scanner);
                    break;
                case 3:
//                    addAccountLoan(scanner);
                    break;
                case 4:
//                    withdraw(scanner);
                    break;
                case 5:
//                    displayTransactions();
                    break;
                case 0:
                    System.out.println("Thoát chương trình, cảm ơn đã sử dụng!");
                    break;
                default:
                    if (choice > 5 && choice < -1) {
                        System.out.println("Phím chức năng là số từ 0 đến 5, vui lòng nhập lại phím chức năng");
                    }
                    break;
            }
        }

    }





}
