package vn.funix.FX21316.java.asm04;

import vn.funix.FX21316.java.asm02.models.TransactionType;

public interface IReport {
    void log(double amount, TransactionType type, String receiveAccount);
}
