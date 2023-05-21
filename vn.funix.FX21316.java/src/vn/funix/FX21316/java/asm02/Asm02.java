package vn.funix.FX21316.java.asm02;

import vn.funix.FX21316.java.asm02.models.Account;
import vn.funix.FX21316.java.asm02.models.Bank;
import vn.funix.FX21316.java.asm02.models.Cccd;
import vn.funix.FX21316.java.asm02.models.Customer;

import java.util.Scanner;

public class Asm02 {
    private  static final Bank bank = new Bank();
    //giới thiệu chương trình
    static void gioiThieu() {
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  Chào bạn đến với ứng dụng Ngân hàng số.        |");
        System.out.println("|  Ứng dụng hỗ trợ lưu trữ và tìm kiếm thông tin  |");
        System.out.println("|  khách hàng.                                    |");
    }

    //hiển thị đầu ------------------------
    static  void menu() {
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("|  NGÂN HÀNG SỐ  | Hoàng Duy Thiệp FX21316@V2.0.0 |");
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
    //kiểm tra nhập phím chức năng--------------------sử dụng try catch---------
    static int ktChucNang(int choice) {
        Scanner sc = new Scanner(System.in);
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
        }

        if (choice < 0 || choice > 5) {
            System.out.println("Phím chức năng là số từ 0 đến 5, vui lòng nhập lại phím chức năng");
        }
        return choice;
    }
    //chức năng 1 ------------thêm khách hàng
    private static void setCustomerId(Scanner scanner) {
        System.out.println("Chức năng thêm khách hàng");
        System.out.println("+----------+---------------------------+----------+");
        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();
        System.out.print("Nhập số ăn cước công dân: ");
        //kiểm tra cccd theo form nên không cần sử dụng try catch
        while (true) {
            String id = scanner.nextLine();
            Cccd cccd = new Cccd(id);
            if (cccd.checkcccd() && cccd.checkMaTinh() && cccd.checkGioiTinh() && cccd.checkSoNgayNhien()) {
                Customer customer = new Customer(name, id);
                bank.addCuctomer(customer);
                break;
            } else {
                System.out.println("Căn cước công dân không hợp lệ, vui lòng nhập lại");
            }
        }
        System.out.println("+----------+---------------------------+----------+");
        System.out.println("Hoàn thành chức năng");
    }

    //chức năng 2 -------------tạo tài khoản------------------
    private static void addAccount(Scanner scanner) {
        System.out.print("Nhập căn cước công dân: ");
        String customerId = scanner.nextLine();
        Customer customer = bank.searchById(customerId);
        while (customer == null) {
            System.out.println("Khách hàng chưa có tài khoản hoặc sai số căn cước công dân, vui lòng nhập lại");
            customerId = scanner.nextLine();
            customer = bank.searchById(customerId);
        }
        if (customer != null){
            System.out.print("Nhập mã tài khoản gồm 6 chữ số: ");
            int accNumber;
            //kiểm tra số tài khoản ------------------------------------
            do {
                // try catch khi nhập số tài khoản ---------------------
                try {
                    accNumber = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    accNumber = 1;
                }
                if (!customer.checkAccNum(accNumber)) {
                    System.out.println("Số tài khoản đã trùng hoặc không phải 6 số");
                }
            } while (!customer.checkAccNum(accNumber));
                int balance;
                //vòng lặp do kiểm tra số dư ban đầu hợp lệ--------------------
                do {
                    System.out.print("Nhâp số dư tài khoản ban đầu, yêu cầu lớn hơn 50 000 đồng: ");
                    balance = Integer.parseInt(scanner.nextLine());

                } while (balance < 50000);

                Account account = new Account(accNumber, balance) {
                    @Override
                    public String getTypeOfAcc() {
                        return null;
                    }
                };
                customer.addAccount(account);
            }
            System.out.println("+----------+---------------------------+----------+");
            System.out.println("Hoàn thành chức năng");
        }


    //chức năng 3 ------------hiển thị danh sách khách hàng-------------
    private static void getCustomers() {
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
    private static void searchCustomerByCCCD(Scanner scanner) {
        System.out.print("Nhập căn cước công dân: ");
        String idNumber = scanner.nextLine();
        Customer customer = bank.searchById(idNumber);
        customer.displayInformation();
    }
// ------------------------------------ main ---------------------------------
    public static void main(String[] args) {
        gioiThieu();
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        //vòng lặp lựa chon chức năng-------------------
        while (choice != 0) {
            menu();

            choice = ktChucNang(choice);


            switch (choice) {
                case 1:
                    setCustomerId(sc);
                    break;
                case 2:
                    addAccount(sc);
                    break;
                case 3:
                    getCustomers();
                    break;
                case 4:
                    searchCustomerByName(sc);
                    break;
                case 5:
                    searchCustomerByCCCD(sc);
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
