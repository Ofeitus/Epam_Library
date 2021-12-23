package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.dto.ReservationDto;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public interface ReservationsService {
    List<ReservationDto> getReservationsDtoByUserId(int userId) throws ServiceException;

    int getReadyReservationsCount(int userId) throws ServiceException;
}
