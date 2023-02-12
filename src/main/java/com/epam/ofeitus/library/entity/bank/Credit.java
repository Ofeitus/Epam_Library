package com.epam.ofeitus.library.entity.bank;

import java.util.Date;
import java.util.Objects;

public class Credit {
    private int id;
    private int type;
    private int number;
    private Currency currency;
    private Date start;
    private Date end;
    private Date term;
    private int amount;
    private int percent;
    private Account account;

    public Credit() {
    }

    public Credit(int id, int type, int number, Currency currency, Date start, Date end, Date term, int amount, int percent, Account account) {
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
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
        Credit credit = (Credit) o;
        return id == credit.id && type == credit.type && number == credit.number && amount == credit.amount && percent == credit.percent && Objects.equals(currency, credit.currency) && Objects.equals(start, credit.start) && Objects.equals(end, credit.end) && Objects.equals(term, credit.term) && Objects.equals(account, credit.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, number, currency, start, end, term, amount, percent, account);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", type=" + type +
                ", number=" + number +
                ", currency=" + currency +
                ", start=" + start +
                ", end=" + end +
                ", term=" + term +
                ", amount=" + amount +
                ", percent=" + percent +
                ", account=" + account +
                '}';
    }
}
