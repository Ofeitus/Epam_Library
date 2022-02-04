package com.epam.ofeitus.library.entity.order;

import com.epam.ofeitus.library.entity.order.constiuent.ReservationStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Reservation bean class.
 */
public class Reservation implements Serializable {
    private int reservationId;
    private Date date;
    private int userId;
    private int inventoryId;
    private ReservationStatus reservationStatus;

    public Reservation() {
    }

    public Reservation(int reservationId, Date date, int userId, int inventoryId, ReservationStatus reservationStatus) {
        this.reservationId = reservationId;
        this.date = date;
        this.userId = userId;
        this.inventoryId = inventoryId;
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
        return reservationId == that.reservationId && userId == that.userId && inventoryId == that.inventoryId && Objects.equals(date, that.date) && reservationStatus == that.reservationStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, date, userId, inventoryId, reservationStatus);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", date=" + date +
                ", userId=" + userId +
                ", inventoryId=" + inventoryId +
                ", reservationStatus=" + reservationStatus +
                '}';
    }
}
