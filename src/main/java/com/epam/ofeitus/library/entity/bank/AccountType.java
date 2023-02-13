package com.epam.ofeitus.library.entity.bank;

public enum AccountType {
    ACTIVE("А"),
    PASSIVE("П");

    private String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static AccountType fromString(String name) {
        for (AccountType at : AccountType.values()) {
            if (at.name.equalsIgnoreCase(name)) {
                return at;
            }
        }
        return null;
    }
}
