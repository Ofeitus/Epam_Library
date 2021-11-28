package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.entity.order.constiuents.ReservationStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<Reservation> {
    @Override
    public Reservation map(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(resultSet.getInt(Column.RESERVATION_ID));
        reservation.setUserId(resultSet.getInt(Column.RESERVATION_USER_ID));
        reservation.setBookIsbn(resultSet.getString(Column.RESERVATION_BOOK_ISBN));
        reservation.setDate(resultSet.getDate(Column.RESERVATION_DATE));
        reservation.setReservationStatus(ReservationStatus.valueOf(resultSet.getString(Column.RESERVATION_STATUS_VALUE).toUpperCase()));
        return null;
    }
}
