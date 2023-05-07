package vn.funix.FX21316.java.asm02;

import vn.funix.FX21316.java.asm02.models.Bank;
import vn.funix.FX21316.java.asm02.models.Customer;

import java.util.Scanner;

public class Asm02 {
    private  static final Bank bank = new Bank();

    //hiển thị đầu ------------------------
    static  void menu() {
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  NGÂN HÀNG SỐ  | Hoàng Duy Thiệp FX21316@V1.0.0 |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  1. Thêm khách hàng                             |");
        System.out.println("|  2. Thêm tài khoản cho khách hàng               |");
        System.out.println("|  3. Hiển thị danh sách khách hàng               |");
        System.out.println("|  4. Tìm theo CCCD                               |");
        System.out.println("|  5. Tìm theo tên khách hàng                     |");
        System.out.println("|  0. Thoát                                       |");
        System.out.println("+----------+---------------------------+----------+");
        System.out.print("Chức năng: ");
    }
    //kiểm tra nhập phím chức năng--------------------
    static int ktChucNang(int choice) {
        Scanner sc = new Scanner(System.in);
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Phím chức năng là số từ 0 đến 5, vui lòng nhập lại phím chức năng");
            ktChucNang(choice);
        }
        sc.nextLine();
        return choice;
    }
    //chức năng 1 ------------thêm khách hàng-------------
    private static void addCustomer(Scanner scanner) {
        System.out.println("Chức năng thêm khách hàng");
        System.out.println("+----------+---------------------------+----------+");
        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();
        System.out.print("Nhập số chứng minh nhân dân: ");
        String id = scanner.nextLine();
        Customer customer = new Customer(name, id);
        bank.addCuctomer(customer);
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("Hoàn thành chức năng");
    }

    //chức năng 2 -------------tạo tài khoản------------------
    private static void addAccount(Scanner scanner) {
        System.out.print("Nhập căn cước công dân: ");
        String customerId = scanner.nextLine();
        Customer customer = bank.searchById(customerId);
        if (customer == null) {
            System.out.println("Khách hàng chưa có tài khoản");
        } else {
            System.out.print("Nhập mã tài khoản gồm 6 chữ số: ");
            int accNumber = scanner.nextInt();
            scanner.nextLine();
            int balance;
            String type = null;
            do {
                System.out.print("Nhâp số dư tài khoản ban đầu, yêu cầu lớn hơn 50 000 đồng: ");
                balance = Integer.parseInt(scanner.nextLine());
                if (balance > 10000000){
                    type = "Premium";
                } else {
                    type = "Normal";
                }
            } while (balance < 50000);

            customer.addAccount(accNumber, balance, type);
        }
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("Hoàn thành chức năng");
    }

    //chức năng 3 ------------hiển thị danh sách khách hàng-------------
    private static void showCustomerList() {
        bank.getCustomers();
    }

    //chức năng 4 ----------------- tìm tài khoản bằng tên -------------
    private static void searchCustomerByName(Scanner scanner) {
        System.out.print("Nhập tên khách hàng: ");
        String keyword = scanner.nextLine();
        Customer customer = bank.searchByName(keyword);
        customer.displayInformation();
    }

    //chức năng 5 ----------- tìm tên theo căn cước công dân -------------
    private static void searchCustomerById(Scanner scanner) {
        System.out.print("Nhập căn cước công dân: ");
        String idNumber = scanner.nextLine();
        Customer customer = bank.searchById(idNumber);
        customer.displayInformation();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice = -1;
        //vòng lặp lựa chon chức năng-------------------
        while (choice != 0) {
            menu();

            choice = ktChucNang(choice);


            switch (choice) {
                case 1:
                    addCustomer(sc);
                    break;
                case 2:
                    addAccount(sc);
                    break;
                case 3:
                    bank.getCustomers();
                    break;
                case 4:
                    searchCustomerByName(sc);
                    break;
                case 5:
                    searchCustomerById(sc);
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
