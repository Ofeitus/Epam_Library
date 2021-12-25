package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.order.Reservation;

import java.util.Collection;
import java.util.List;

public interface ReservationDao extends AbstractDao<Reservation> {
    List<Reservation> findByUserId(int userId) throws DaoException;

    List<Reservation> findByStatusId(int statusId) throws DaoException;

    List<Reservation> findByUserIdAndStatusId(int userId, int statusId) throws DaoException;
}
