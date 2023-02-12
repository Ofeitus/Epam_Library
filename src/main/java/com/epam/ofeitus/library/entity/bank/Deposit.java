package com.epam.ofeitus.library.entity.bank;

import java.util.Date;
import java.util.Objects;

public class Deposit {
    private int id;
    private int type;
    private int number;
    private String currency;
    private Date start;
    private Date end;
    private Date term;
    private int amount;
    private int percent;
    private Account account;

    public Deposit() {
    }

    public Deposit(int id, int type, int number, String currency, Date start, Date end, Date term, int amount, int percent, Account account) {
        this.id = id;
        this.type = type;
        this.number = number;
        this.currency = currency;
        this.start = start;
        this.end = end;
        this.term = term;
        this.amount = amount;
        this.percent = percent;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
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
        Deposit deposit = (Deposit) o;
        return id == deposit.id && type == deposit.type && number == deposit.number && amount == deposit.amount && percent == deposit.percent && Objects.equals(currency, deposit.currency) && Objects.equals(start, deposit.start) && Objects.equals(end, deposit.end) && Objects.equals(term, deposit.term) && Objects.equals(account, deposit.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, number, currency, start, end, term, amount, percent, account);
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", type=" + type +
                ", number=" + number +
                ", currency='" + currency + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", term=" + term +
                ", amount=" + amount +
                ", percent=" + percent +
                ", account=" + account +
                '}';
    }
}
