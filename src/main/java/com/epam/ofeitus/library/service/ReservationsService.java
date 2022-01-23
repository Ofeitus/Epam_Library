package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.dto.ReservationDto;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

/**
 * Reservations service interface.
 */
public interface ReservationsService {
    /**
     * Reserve copy of given book (if there is available).
     *
     * @param userId   user id
     * @param bookIsbn book isbn
     * @return if book was successfully reserved
     * @throws ServiceException thrown when dao exception occurs
     */
    boolean makeReservation(int userId, String bookIsbn) throws ServiceException;

    /**
     * Cancel reservation.
     *
     * @param reservationId reservation id
     * @throws ServiceException thrown when dao exception occurs
     */
    void cancelReservation(int reservationId) throws ServiceException;

    /**
     * Confirm reservation.
     *
     * @param reservationId reservation id
     * @throws ServiceException thrown when dao exception occurs
     */
    void confirmReservation(int reservationId) throws ServiceException;

    /**
     * Gets count of by user id and status id.
     *
     * @param userId   user id
     * @param statusId status id
     * @return reservations count
     * @throws ServiceException thrown when dao exception occurs
     */
    int countReservationsByUserIdAndStatusId(int userId, int statusId) throws ServiceException;

    /**
     * Gets reservations Dto by user id.
     *
     * @param userId      user id
     * @param page        page
     * @param itemsOnPage items on page
     * @return list of reservations dto
     * @throws ServiceException thrown when dao exception occurs
     */
    List<ReservationDto> getReservationsDtoByUserId(int userId, int page, int itemsOnPage) throws ServiceException;

    /**
     * Count  dto by user id.
     *
     * @param userId user id
     * @return reservations count
     * @throws ServiceException thrown when dao exception occurs
     */
    int countReservationsDtoByUserId(int userId) throws ServiceException;

    /**
     * Gets unconfirmed reservations dto.
     *
     * @param page        page
     * @param itemsOnPage items on page
     * @return list of reservations dto
     * @throws ServiceException thrown when dao exception occurs
     */
    List<ReservationDto> getUnconfirmedReservationsDto(int page, int itemsOnPage) throws ServiceException;

    /**
     * Count unconfirmed reservations dto.
     *
     * @return reservations count
     * @throws ServiceException thrown when dao exception occurs
     */
    int countUnconfirmedReservationsDto() throws ServiceException;
}
