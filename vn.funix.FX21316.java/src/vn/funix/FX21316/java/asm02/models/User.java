package vn.funix.FX21316.java.asm02.models;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;;

    private String name;
    private String customerId;

    public User(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
