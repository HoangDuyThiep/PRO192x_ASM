package vn.funix.FX21316.java.asm04.test;

import org.junit.jupiter.api.Test;
import vn.funix.FX21316.java.asm02.models.Account;
import vn.funix.FX21316.java.asm04.dao.AccountDao;

import java.util.List;

class AccountDaoTest {

    @Test
    void save() {
    }

    @Test
    void list() {
        List<Account> accounts = AccountDao.list();
        for (Account account : accounts) System.out.println(account);
    }

    @Test
    void update() {
    }
}