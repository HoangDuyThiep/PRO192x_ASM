//package vn.funix.FX21316.java.asm03;
//
//import vn.funix.FX21316.java.asm02.models.Account;
//import vn.funix.FX21316.java.asm02.models.Customer;
//import vn.funix.FX21316.java.asm03.models.DigitalBank;
//import vn.funix.FX21316.java.asm03.models.DigitalCustomer;
//import vn.funix.FX21316.java.asm03.models.LoansAccount;
//import vn.funix.FX21316.java.asm03.models.SavingsAccount;
//
//import java.util.Scanner;
//
//public class Asm03 {
//    private static final Scanner scanner = new Scanner(System.in);
//    private static final DigitalBank activeBank = new DigitalBank();
//    private static final String CUSTOMER_ID = "001215000001";
//    private static final String CUSTOMER_NAME = "FUNIX";
//
//    //giới thiệu chương trình
//    static void gioiThieu() {
//        System.out.println("+----------+---------------------------+----------+");
//        System.out.println("|  Chào bạn đến với ứng dụng Ngân hàng số.        |");
//        System.out.println("|  Ứng dụng hỗ trợ lưu trữ và tìm kiếm thông tin  |");
//        System.out.println("|  khách hàng.                                    |");
//    }
//
//    //hiển thị đầu
//    static  void menu() {
//        System.out.println("+----------+---------------------------+----------+");
//        System.out.println("|  NGÂN HÀNG SỐ  | Hoàng Duy Thiệp FX21316@V3.0.0 |");
//        System.out.println("+----------+---------------------------+----------+");
//        System.out.println("|  1. Thông tin khách hàng                        |");
//        System.out.println("|  2. Thêm tài khoản ATM                          |");
//        System.out.println("|  3. Thêm tài khoản tín dụng                     |");
//        System.out.println("|  4. Rút tiền                                    |");
//        System.out.println("|  5. Lịch sử giao dịch                           |");
//        System.out.println("|  0. Thoát                                       |");
//        System.out.println("+----------+---------------------------+----------+");
//        System.out.print("Chức năng: ");
//    }
//    //kiểm tra nhập phím chức năng, sử dụng try catch
//    static int ktChucNang(int choice) {
//        Scanner sc = new Scanner(System.in);
//        try {
//            choice = Integer.parseInt(sc.nextLine());
//        } catch (Exception e) {
//            choice = -1;
//        }
//
//        if (choice < 0 || choice > 5) {
//            System.out.println("Phím chức năng là số từ 0 đến 5, vui lòng nhập lại phím chức năng");
//        }
//        return choice;
//    }
//
//    //chức năng 1 : Thông tin khách hàng
//    static void showCustomer() {
//        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
//        if (customer != null)
//            customer.displayInformation();
//    }
//
//    //chức năng 2 tạo tài khoản ATM
//    private static void addAccountATM(Scanner scanner) {
//        Customer customer = activeBank.searchById(CUSTOMER_ID);
//        if (customer != null){
//            System.out.print("Nhập mã tài khoản gồm 6 chữ số: ");
//            int accNumber;
//            //kiểm tra số tài khoản
//            do {
//                // try catch khi nhập số tài khoản
//                try {
//                    accNumber = Integer.parseInt(scanner.nextLine());
//                } catch (Exception e) {
//                    accNumber = 0;
//                }
//                if (!customer.checkAccNum(accNumber)) {
//                    System.out.println("Số tài khoản đã trùng hoặc không phải 6 số");
//                }
//            } while (!customer.checkAccNum(accNumber));
//            int balance;
//            //vòng lặp do kiểm tra số dư ban đầu hợp lệ
//            do {
//                System.out.print("Nhâp số dư tài khoản ban đầu, yêu cầu lớn hơn 50 000 đồng: ");
//                //catch nhập kí tự khong hợp lệ
//                try {
//                    balance = Integer.parseInt(scanner.nextLine());
//                } catch (Exception e) {
//                    System.out.println("Không hợp lệ:");
//                    balance = 0;
//                }
//
//            } while (balance < 50000);
//
//            Account account = new SavingsAccount(String.valueOf(accNumber), balance, customer.getType());
//            customer.addAccount(account);
//        }
//        System.out.println("+----------+---------------------------+----------+");
//        System.out.println("Hoàn thành chức năng thêm tài khoản ATM");
//    }
//
//    //chức năng 3 tạo tài khoản tí dụng
//    private static void addAccountLoan(Scanner scanner) {
//        Customer customer = activeBank.searchById(CUSTOMER_ID);
//        if (customer != null){
//            System.out.print("Nhập mã tài khoản gồm 6 chữ số: ");
//            int accNumber;
//            //kiểm tra số tài khoản
//            do {
//                // try catch khi nhập số tài khoản
//                try {
//                    accNumber = Integer.parseInt(scanner.nextLine());
//                } catch (Exception e) {
//                    accNumber = 1;
//                }
//                if (!customer.checkAccNum(accNumber)) {
//                    System.out.println("Số tài khoản đã trùng hoặc không phải 6 số");
//                }
//            } while (!customer.checkAccNum(accNumber));
//            int balance;
//            //vòng lặp do kiểm tra số dư ban đầu hợp lệ
//            do {
//                System.out.print("Nhâp số dư tài khoản ban đầu, yêu cầu lớn hơn 50 000 đồng: ");
//                //kiểm tra nhâp cacth
//                try {
//                    balance = Integer.parseInt(scanner.nextLine());
//                } catch (Exception e) {
//                    System.out.println("Không hợp lệ:");
//                    balance = 0;
//                }
//
//            } while (balance < 50000);
//            Account account = new LoansAccount(String.valueOf(accNumber), balance);
//            customer.addAccount(account);
//        }
//        System.out.println("+----------+---------------------------+----------+");
//        System.out.println("Hoàn thành chức năng thêm tài khoản tín dụng");
//    }
//
//    //chức năng 4: rút tiền
//    private static void withdraw(Scanner scanner) {
//        DigitalCustomer customer = (DigitalCustomer) activeBank.searchById(CUSTOMER_ID);
//        if (customer != null){
//            System.out.print("Nhập mã tài khoản bạn muốn rút tiền: ");
//            int accNumber;
//            //kiểm tra số tài khoản
//            do {
//                // try catch khi nhập số tài khoản
//                try {
//                    accNumber = Integer.parseInt(scanner.nextLine());
//                } catch (Exception e) {
//                    accNumber = 1;
//                }
//                if (customer.searchByAccNum(accNumber) == null) {
//                    System.out.println("bạn đã nhập sai mã tài khoản");
//                }
//            } while (customer.searchByAccNum(accNumber) == null);
//            Account account = customer.searchByAccNum(accNumber);
//            System.out.println("Bạn đã chọn tài khoản " + account.getTypeOfAcc());
//            double amount;
//            while (true) {
//                System.out.print("Nhập số tiền bạn muốn rút tiền: ");
//                try {
//                    amount = Double.parseDouble(scanner.nextLine());
//                } catch (Exception e) {
//                    amount = 0;
//                }
//                //kiểm tra loại acc để thực thiện rút tiền
//                customer.withdraw(accNumber,amount);
//                break;
//            }
//
//        }
//        System.out.println("+----------+---------------------------+----------+");
//        System.out.println("Hoàn thành chức năng rút tiền");
//    }
//    //chức năng 5: in các giao dịch
//    private static void displayTransactions() {
//        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
//        if (customer != null) {
//            customer.displayInformation();
//            customer.displaytransactions();
//        }
//    }
//
//
//    // main
//    public static void main(String[] args) {
//        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
//        gioiThieu();
//        int choice = -1;
//        //vòng lặp lựa chon chức năng
//        while (choice != 0) {
//            menu();
//
//            choice = ktChucNang(choice);
//
//
//            switch (choice) {
//                case 1:
//                    showCustomer();
//                    break;
//                case 2:
//                    addAccountATM(scanner);
//                    break;
//                case 3:
//                    addAccountLoan(scanner);
//                    break;
//                case 4:
//                    withdraw(scanner);
//                    break;
//                case 5:
//                    displayTransactions();
//                    break;
//                case 0:
//                    System.out.println("Thoát chương trình, cảm ơn đã sử dụng!");
//                    break;
//                default:
//                    if (choice > 5 && choice < -1) {
//                        System.out.println("Phím chức năng là số từ 0 đến 5, vui lòng nhập lại phím chức năng");
//                    }
//                    break;
//            }
//        }
//
//    }
//
//
//
//
//
//
//}
