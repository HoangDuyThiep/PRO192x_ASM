package vn.funix.FX21316.java.asm04;

import vn.funix.FX21316.java.asm02.models.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    // cập nhật số dư cho tài khoản
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

            for (Account account : accounts) {

                if (account.getAccountNumer().equals(editAccount.getAccountNumer())
                && account.getCustomerId().equals(editAccount.getCustomerId())) {
                    updateAccounts.add(editAccount);
                } else {
                    updateAccounts.add(account);
                }
            }
        }
        try {
            save(updateAccounts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

