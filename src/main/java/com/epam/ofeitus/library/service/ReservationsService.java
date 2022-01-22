package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.dto.ReservationDto;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public interface ReservationsService {
    boolean makeReservation(int userId, String bookIsbn) throws ServiceException;

    void cancelReservation(int reservationId) throws ServiceException;

    void confirmReservation(int reservationId) throws ServiceException;

    int countReservationsByUserIdAndStatusId(int userId, int statusId) throws ServiceException;

    List<ReservationDto> getReservationsDtoByUserId(int userId, int page, int itemsOnPage) throws ServiceException;

    int countReservationsDtoByUserId(int userId) throws ServiceException;

    List<ReservationDto> getUnconfirmedReservationsDto(int page, int itemsOnPage) throws ServiceException;

    int countUnconfirmedReservationsDto() throws ServiceException;
}
