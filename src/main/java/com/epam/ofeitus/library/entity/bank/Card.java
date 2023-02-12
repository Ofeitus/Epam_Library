package com.epam.ofeitus.library.entity.bank;

import java.util.Objects;

public class Card {
    private int id;
    private String number;
    private String pin;
    private Account account;

    public Card() {
    }

    public Card(int id, String number, String pin, Account account) {
        this.id = id;
        this.number = number;
        this.pin = pin;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && Objects.equals(number, card.number) && Objects.equals(pin, card.pin) && Objects.equals(account, card.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, pin, account);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", pin='" + pin + '\'' +
                ", account=" + account +
                '}';
    }
}
