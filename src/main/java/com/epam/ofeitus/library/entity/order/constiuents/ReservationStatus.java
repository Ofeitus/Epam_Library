package com.epam.ofeitus.library.entity.order.constiuents;

import java.util.Objects;

public class ReservationStatus {
    private int statusId;
    private String value;

    public ReservationStatus() {
    }

    public ReservationStatus(int statusId, String value) {
        this.statusId = statusId;
        this.value = value;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationStatus that = (ReservationStatus) o;
        return statusId == that.statusId && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, value);
    }

    @Override
    public String toString() {
        return "ReservationStatus{" +
                "statusId=" + statusId +
                ", value='" + value + '\'' +
                '}';
    }
}
