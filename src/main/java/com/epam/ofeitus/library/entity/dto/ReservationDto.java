package com.epam.ofeitus.library.entity.dto;

import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.order.constiuent.ReservationStatus;

import java.sql.Date;
import java.util.Objects;

public class ReservationDto {
    private int reservationId;
    private Date date;
    private int userId;
    private int inventoryId;
    private Book book;
    private ReservationStatus reservationStatus;

    public ReservationDto() {
    }

    public ReservationDto(int reservationId, Date date, int userId, int inventoryId, Book book, ReservationStatus reservationStatus) {
        this.reservationId = reservationId;
        this.date = date;
        this.userId = userId;
        this.inventoryId = inventoryId;
        this.book = book;
        this.reservationStatus = reservationStatus;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
        ReservationDto that = (ReservationDto) o;
        return reservationId == that.reservationId && userId == that.userId && inventoryId == that.inventoryId && Objects.equals(date, that.date) && Objects.equals(book, that.book) && reservationStatus == that.reservationStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, date, userId, inventoryId, book, reservationStatus);
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "reservationId=" + reservationId +
                ", date=" + date +
                ", userId=" + userId +
                ", inventoryId=" + inventoryId +
                ", book=" + book +
                ", reservationStatus=" + reservationStatus +
                '}';
    }
}
