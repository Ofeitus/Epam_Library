package com.epam.ofeitus.library.entity.bank;

public enum ClientType {
    LEGAL_ENTITY("ЮЛ"),
    INDIVIDUAL("ФЛ"),
    INDIVIDUAL_ENTREPRENEUR("ИП");

    private String name;

    ClientType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static ClientType fromString(String name) {
        for (ClientType at : ClientType.values()) {
            if (at.name.equalsIgnoreCase(name)) {
                return at;
            }
        }
        return null;
    }
}
