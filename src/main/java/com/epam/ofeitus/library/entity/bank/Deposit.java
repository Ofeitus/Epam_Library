package com.epam.ofeitus.library.entity.bank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Deposit {
    private int id;
    private int type;
    private int number;
    private Currency currency;
    private Date start;
    private Date end;
    private int term;
    private BigDecimal amount;
    private BigDecimal percent;
    private Account currentAccount;
    private Account percentAccount;

    public Deposit() {
    }

    public Deposit(int id, int type, int number, Currency currency, Date start, Date end, int term, BigDecimal amount, BigDecimal percent, Account currentAccount, Account percentAccount) {
        this.id = id;
        this.type = type;
        this.number = number;
        this.currency = currency;
        this.start = start;
        this.end = end;
        this.term = term;
        this.amount = amount;
        this.percent = percent;
        this.currentAccount = currentAccount;
        this.percentAccount = percentAccount;
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

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
    }

    public Account getPercentAccount() {
        return percentAccount;
    }

    public void setPercentAccount(Account percentAccount) {
        this.percentAccount = percentAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return id == deposit.id && type == deposit.type && number == deposit.number && term == deposit.term && Objects.equals(currency, deposit.currency) && Objects.equals(start, deposit.start) && Objects.equals(end, deposit.end) && Objects.equals(amount, deposit.amount) && Objects.equals(percent, deposit.percent) && Objects.equals(currentAccount, deposit.currentAccount) && Objects.equals(percentAccount, deposit.percentAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, number, currency, start, end, term, amount, percent, currentAccount, percentAccount);
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
                ", currentAccount=" + currentAccount +
                ", percentAccount=" + percentAccount +
                '}';
    }
}
