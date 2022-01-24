package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.order.Reservation;

import java.util.Date;
import java.util.List;

/**
 * Reservation dao interface.
 */
public interface ReservationDao extends AbstractDao<Reservation> {
    /**
     * Find by user id.
     *
     * @param userId      user id
     * @param offset      offset
     * @param itemsOnPage items on page
     * @return reservations list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<Reservation> findByUserId(int userId, int offset, int itemsOnPage) throws DaoException;

    /**
     * Count by user id.
     *
     * @param userId user id
     * @return reservations count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countByUserId(int userId) throws DaoException;

    /**
     * Find by inventory id.
     *
     * @param inventoryId inventory id
     * @return reservations list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<Reservation> findByInventoryId(int inventoryId) throws DaoException;

    /**
     * Find by status id.
     *
     * @param statusId    status id
     * @param offset      offset
     * @param itemsOnPage items on page
     * @return reservations list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<Reservation> findByStatusId(int statusId, int offset, int itemsOnPage) throws DaoException;

    /**
     * Count by status id.
     *
     * @param statusId status id
     * @param date     date
     * @return reservations count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countByStatusId(int statusId, Date date) throws DaoException;

    /**
     * Count by user id and status id.
     *
     * @param userId   user id
     * @param statusId status id
     * @return reservations count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countByUserIdAndStatusId(int userId, int statusId) throws DaoException;

    /**
     * Make copy of book reserved, save reservation.
     *
     * @param userId   user id
     * @param bookIsbn book isbn
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int reserve(int userId, String bookIsbn) throws DaoException;

    /**
     * Delete reservation from data source.
     *
     * @param reservation reservation
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int cancel(Reservation reservation) throws DaoException;

    /**
     * Update reservation status.
     *
     * @param reservationId reservation id
     * @param statusId      status id
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int setStatus(int reservationId, int statusId) throws DaoException;
}
