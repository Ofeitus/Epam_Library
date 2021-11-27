package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.entity.order.Reservation;

import java.util.List;

public interface ReservationDao extends AbstractDao<Reservation> {
    List<Reservation> findByUserId(int userId);

    List<Reservation> findUnconfirmedReservations();
}
