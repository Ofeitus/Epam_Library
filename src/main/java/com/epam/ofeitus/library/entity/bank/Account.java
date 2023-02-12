package com.epam.ofeitus.library.entity.bank;

import com.epam.ofeitus.library.entity.user.User;

import java.math.BigInteger;
import java.util.Objects;

public class Account {
    private int id;
    private AccountType type;
    private String number;
    private String name;
    private int code;
    private AccountPurpose purpose;
    private BigInteger balance;
    private ClientType clientType;
    private User client;

    public Account() {
    }

    public Account(int id, AccountType type, String number, String name, int code, AccountPurpose purpose, BigInteger balance, ClientType clientType, User client) {
        this.id = id;
        this.type = type;
        this.number = number;
        this.name = name;
        this.code = code;
        this.purpose = purpose;
        this.balance = balance;
        this.clientType = clientType;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AccountPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(AccountPurpose purpose) {
        this.purpose = purpose;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && code == account.code && type == account.type && Objects.equals(number, account.number) && Objects.equals(name, account.name) && purpose == account.purpose && Objects.equals(balance, account.balance) && clientType == account.clientType && Objects.equals(client, account.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, number, name, code, purpose, balance, clientType, client);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type=" + type +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", purpose=" + purpose +
                ", balance=" + balance +
                ", clientType=" + clientType +
                ", client=" + client +
                '}';
    }
}
