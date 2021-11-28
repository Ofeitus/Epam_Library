package com.epam.ofeitus.library.entity.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class FinePayment {
    private int paymentId;
    private int userId;
    private Date date;
    private BigDecimal amount;

    public FinePayment() {
    }

    public FinePayment(int paymentId, int userId, Date date, BigDecimal amount) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.date = date;
        this.amount = amount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinePayment that = (FinePayment) o;
        return paymentId == that.paymentId && userId == that.userId && Objects.equals(date, that.date) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, userId, date, amount);
    }

    @Override
    public String toString() {
        return "FinePayment{" +
                "paymentId=" + paymentId +
                ", userId=" + userId +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
