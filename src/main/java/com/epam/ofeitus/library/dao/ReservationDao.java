package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.order.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationDao extends AbstractDao<Reservation> {
    List<Reservation> findByUserId(int userId, int offset, int itemsOnPage) throws DaoException;

    int countByUserId(int userId) throws DaoException;

    List<Reservation> findByInventoryId(int inventoryId) throws DaoException;

    List<Reservation> findByStatusId(int statusId, int offset, int itemsOnPage) throws DaoException;

    int countByStatusId(int statusId, Date date) throws DaoException;

    List<Reservation> findByUserIdAndStatusId(int userId, int statusId) throws DaoException;

    int cancel(Reservation reservation) throws DaoException;

    int reserve(int userId, String bookIsbn) throws DaoException;

    int setStatus(int reservationId, int statusId) throws DaoException;
}
