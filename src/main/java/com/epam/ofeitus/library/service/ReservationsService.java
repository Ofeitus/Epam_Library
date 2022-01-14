package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.dto.ReservationDto;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public interface ReservationsService {
    List<ReservationDto> getReservationsDtoByUserId(int userId, int page, int itemsOnPage) throws ServiceException;

    int countReservationsDtoByUserId(int userId) throws ServiceException;

    int getReservationsCountByUserIdAndStatusId(int userId, int statusId) throws ServiceException;

    boolean makeReservation(int userId, String bookIsbn) throws ServiceException;

    int cancelReservation(int reservationId) throws ServiceException;

    Reservation getByReservationId(int reservationId) throws ServiceException;
}
