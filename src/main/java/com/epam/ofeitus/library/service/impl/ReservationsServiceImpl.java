package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.BookDao;
import com.epam.ofeitus.library.dao.CopyOfBookDao;
import com.epam.ofeitus.library.dao.LoanDao;
import com.epam.ofeitus.library.dao.ReservationDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.dto.ReservationDto;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.entity.order.constiuent.LoanStatus;
import com.epam.ofeitus.library.service.ReservationsService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ReservationsServiceImpl implements ReservationsService {
    @Override
    public List<ReservationDto> getReservationsDtoByUserId(int userId) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        BookDao bookDao = daoFactory.getBookDao();

        try {
            List<Reservation> reservations = reservationDao.findByUserId(userId);
            List<ReservationDto> reservationsDto = new ArrayList<>();
            for (Reservation reservation : reservations) {
                CopyOfBook copyOfBook = copyOfBookDao.findById(reservation.getInventoryId());
                Book book = bookDao.findByIsbn(copyOfBook.getBookIsbn());
                reservationsDto.add(new ReservationDto(
                        reservation.getReservationId(),
                        reservation.getDate(),
                        reservation.getUserId(),
                        reservation.getInventoryId(),
                        book,
                        reservation.getReservationStatus()
                        )
                );
            }
            return reservationsDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getReadyReservationsCount(int userId) throws ServiceException {
        ReservationDao reservationDao = MySqlDaoFactory.getInstance().getReservationDao();
        try {
            return reservationDao.findReadyByUserId(userId).size();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
