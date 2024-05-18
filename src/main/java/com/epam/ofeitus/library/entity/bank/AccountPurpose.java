package com.epam.ofeitus.library.entity.bank;

public enum AccountPurpose {
    CURRENT("Текущий"),
    PERCENT("Процентный");

    private String name;

    AccountPurpose(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static AccountPurpose fromString(String name) {
        for (AccountPurpose at : AccountPurpose.values()) {
            if (at.name.equalsIgnoreCase(name)) {
                return at;
            }
        }
        return null;
    }
}
