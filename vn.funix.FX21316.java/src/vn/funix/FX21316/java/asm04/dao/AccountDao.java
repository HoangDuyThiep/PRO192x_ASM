package vn.funix.FX21316.java.asm04.dao;

import vn.funix.FX21316.java.asm02.models.Account;
import vn.funix.FX21316.java.asm04.sevice.BinaryFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AccountDao {
    private final static String FILE_PATH = "store//accounts.dat";
    // lưu danh sách khách hàng vào file
    public static void save(List<Account> accounts) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, accounts);
    }
    // lấy ra danh sách khách hàng từ file
    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
    // Sử dụng multi thread cho các vòng lặp đọc ghi dữ liệu
        private static final int MAX_THREAD = 4; // Số lượng luồng tối đa
        public static void update(Account editAccount) {
            List<Account> accounts = list();
            boolean hasExist = accounts.stream()
                    .anyMatch(account -> account.getAccountNumer().equals(editAccount.getAccountNumer()));

            List<Account> updateAccounts;
            if (!hasExist) {
                updateAccounts = new ArrayList<>(accounts);
                updateAccounts.add(editAccount);
            } else {
                updateAccounts = new ArrayList<>();
                ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);

                for (Account account : accounts) {
                    executorService.submit(() -> {
                        if (account.getAccountNumer().equals(editAccount.getAccountNumer())
                                && account.getCustomerId().equals(editAccount.getCustomerId())) {
                            updateAccounts.add(editAccount);
                        } else {
                            updateAccounts.add(account);
                        }
                    });
                }

                executorService.shutdown();
                try {
                    executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                save(updateAccounts);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//    // cập nhật số dư cho tài khoản theo cach don gian
//    public static void update(Account editAccount) {
//        List<Account> accounts = list();
//        boolean hasExist = accounts.stream()
//                .anyMatch(account -> account.getAccountNumer().equals(editAccount.getAccountNumer()));
//
//        List<Account> updateAccounts;
//        if (!hasExist) {
//            updateAccounts = new ArrayList<>(accounts);
//            updateAccounts.add(editAccount);
//        } else {
//            updateAccounts = new ArrayList<>();
//
//            for (Account account : accounts) {
//
//                if (account.getAccountNumer().equals(editAccount.getAccountNumer())
//                && account.getCustomerId().equals(editAccount.getCustomerId())) {
//                    updateAccounts.add(editAccount);
//                } else {
//                    updateAccounts.add(account);
//                }
//            }
//        }
//        try {
//            save(updateAccounts);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}

