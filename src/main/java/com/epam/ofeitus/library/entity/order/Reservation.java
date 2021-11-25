package com.epam.ofeitus.library.entity.order;

import java.sql.Date;
import java.util.Objects;

public class Reservation {
    private int reservationId;
    private int userId;
    private int bookIsbn;
    private Date date;
    private int reservationStatusId;

    public Reservation() {
    }

    public Reservation(int reservationId, int userId, int bookIsbn, Date date, int reservationStatusId) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.bookIsbn = bookIsbn;
        this.date = date;
        this.reservationStatusId = reservationStatusId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(int bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReservationStatusId() {
        return reservationStatusId;
    }

    public void setReservationStatusId(int reservationStatusId) {
        this.reservationStatusId = reservationStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId && userId == that.userId && bookIsbn == that.bookIsbn && reservationStatusId == that.reservationStatusId && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, userId, bookIsbn, date, reservationStatusId);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", userId=" + userId +
                ", bookIsbn=" + bookIsbn +
                ", date=" + date +
                ", reservationStatusId=" + reservationStatusId +
                '}';
    }
}
