package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.BookDao;
import com.epam.ofeitus.library.dao.ReservationDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.dto.ReservationDto;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.service.ReservationsService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ReservationsServiceImpl implements ReservationsService {
    @Override
    public List<ReservationDto> getReservationsDtoByUserId(int userId) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        BookDao bookDao = daoFactory.getBookDao();

        try {
            List<Reservation> reservations = reservationDao.findByUserId(userId);
            List<ReservationDto> reservationsDto = new ArrayList<>();
            for (Reservation reservation : reservations) {
                Book book = bookDao.findByIsbn(reservation.getBookIsbn());
                reservationsDto.add(new ReservationDto(
                        reservation.getReservationId(),
                        reservation.getUserId(),
                        reservation.getBookIsbn(),
                        book,
                        reservation.getDate(),
                        reservation.getReservationStatus()
                        )
                );
            }
            return reservationsDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
