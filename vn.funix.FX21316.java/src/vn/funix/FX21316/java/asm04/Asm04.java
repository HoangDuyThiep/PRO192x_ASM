package vn.funix.FX21316.java.asm04;

import vn.funix.FX21316.java.asm04.models.Color;
import vn.funix.FX21316.java.asm04.models.DigitalBank;

import java.util.Scanner;

public class Asm04 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank("FX123456", "NGAN HANG SO");


    //giới thiệu chương trình
    static void gioiThieu() {
        System.out.println(Color.blueColor + "+----------+---------------------------+----------+");
        System.out.println("|  Chào bạn đến với ứng dụng Ngân hàng số.        |");
        System.out.println("|  Ứng dụng hỗ trợ lưu trữ và tìm kiếm thông tin  |");
        System.out.println("|  khách hàng.                                    |" + Color.resetColor);
    }

    //hiển thị đầu
    static  void menu() {
        System.out.println(Color.blueColor + "+----------+---------------------------+----------+");
        System.out.println("|  NGÂN HÀNG SỐ  | Hoàng Duy Thiệp FX21316@V4.0.0 |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  1. Xem danh sách khách hàng                    |");
        System.out.println("|  2. Nhập danh sách khách hàng                   |");
        System.out.println("|  3. Thêm tài khoản ATM                          |");
        System.out.println("|  4. Chuyển tiền                                 |");
        System.out.println("|  5. Rút tiền                                    |");
        System.out.println("|  6. Tra cứu lịch sử giao dịch                   |");
        System.out.println("|  0. Thoát                                       |");
        System.out.println("+----------+---------------------------+----------+" + Color.resetColor);
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

        if (choice < 0 || choice > 6) {
            System.out.println("Phím chức năng là số từ 0 đến 6, vui lòng nhập lại phím chức năng");
        }
        return choice;
    }
    // chuc nang 2: them khach hang
    static void addCustomers(Scanner scanner) {
        System.out.println("nhap duong dan den tep: store/customers.txt");
        String path = scanner.nextLine();
        activeBank.addCustomers(path);
    }
    // chuc nang 1: xem danh sach khach hang
    static void showCustomers() {
        activeBank.showCustomers();
    }
    // chuc nang 3: them tai khoan saving
    static void addAccountSaving(Scanner scanner) {
        System.out.println("nhap ma so khach hang: ");
        String customerId = scanner.nextLine();
        activeBank.addSavingAccount(scanner, customerId);
    }
    // chuc nang 4: chuyen tien
    static void transfer(Scanner scanner) {
        System.out.println("nhap ma so khach hang: ");
        String customerId = scanner.nextLine();
        activeBank.transfers(scanner, customerId);
    }
    //chuc nang 5: rut tien
    static void withdraw(Scanner scanner) {
        System.out.println("nhap ma so khach hang: ");
        String customerId = scanner.nextLine();
        activeBank.withdraw(scanner, customerId);
    }
    //chuc nang 6: Tra cứu lịch sử giao dịch
    static void displayTransactions(Scanner scanner) {
        System.out.println("nhap ma so khach hang: ");
        String customerId = scanner.nextLine();
        activeBank.showAccOfCustomer(scanner, customerId);
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
                    showCustomers();
                    break;
                case 2:
                    addCustomers(scanner);
                    break;
                case 3:
                    addAccountSaving(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    withdraw(scanner);
                    break;
                case 6:
                    displayTransactions(scanner);
                    break;
                case 0:
                    System.out.println("Thoát chương trình, cảm ơn đã sử dụng!");
                    break;
                default:
                    if (choice > 6 && choice < -1) {
                        System.out.println("Phím chức năng là số từ 0 đến 6, vui lòng nhập lại phím chức năng");
                    }
                    break;
            }
        }

    }





}
