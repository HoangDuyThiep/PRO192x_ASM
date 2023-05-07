package vn.funix.FX21316.java.asm02.models;

public class User {
    protected String name;
    protected String customerId;

    public User(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }
}
