package com.epam.ofeitus.library.entity.dto;

import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.order.constiuent.ReservationStatus;

import java.sql.Date;
import java.util.Objects;

public class ReservationDto {
    private int reservationId;
    private int userId;
    private String bookIsbn;
    private Book book;
    private Date date;
    private ReservationStatus reservationStatus;

    public ReservationDto() {
    }

    public ReservationDto(int reservationId, int userId, String bookIsbn, Book book, Date date, ReservationStatus reservationStatus) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.bookIsbn = bookIsbn;
        this.book = book;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
        ReservationDto that = (ReservationDto) o;
        return reservationId == that.reservationId && userId == that.userId && Objects.equals(bookIsbn, that.bookIsbn) && Objects.equals(book, that.book) && Objects.equals(date, that.date) && reservationStatus == that.reservationStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, userId, bookIsbn, book, date, reservationStatus);
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "reservationId=" + reservationId +
                ", userId=" + userId +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", book=" + book +
                ", date=" + date +
                ", reservationStatus=" + reservationStatus +
                '}';
    }
}
