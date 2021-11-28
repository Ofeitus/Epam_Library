package com.epam.ofeitus.library.entity.order;

import com.epam.ofeitus.library.entity.order.constiuents.ReservationStatus;

import java.sql.Date;
import java.util.Objects;

public class Reservation {
    private int reservationId;
    private int userId;
    private String bookIsbn;
    private Date date;
    private ReservationStatus reservationStatus;

    public Reservation() {
    }

    public Reservation(int reservationId, int userId, String bookIsbn, Date date, ReservationStatus reservationStatus) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.bookIsbn = bookIsbn;
        this.date = date;
        this.reservationStatus = reservationStatus;
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

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId && userId == that.userId && bookIsbn.equals(that.bookIsbn) && reservationStatus == that.reservationStatus && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, userId, bookIsbn, date, reservationStatus);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", userId=" + userId +
                ", bookIsbn=" + bookIsbn +
                ", date=" + date +
                ", reservationStatusId=" + reservationStatus +
                '}';
    }
}
