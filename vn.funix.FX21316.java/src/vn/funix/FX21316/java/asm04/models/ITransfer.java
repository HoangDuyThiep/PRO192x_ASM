package vn.funix.FX21316.java.asm04.models;

import vn.funix.FX21316.java.asm02.models.Account;

public interface ITransfer {
    void transfer(Account receiveAccount, double amount);
}
