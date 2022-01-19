package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.BookDao;
import com.epam.ofeitus.library.dao.CopyOfBookDao;
import com.epam.ofeitus.library.dao.ReservationDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.dto.ReservationDto;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.entity.order.constiuent.ReservationStatus;
import com.epam.ofeitus.library.service.ReservationsService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationsServiceImpl implements ReservationsService {
    @Override
    public List<ReservationDto> getReservationsDtoByUserId(int userId, int page, int itemsOnPage) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        BookDao bookDao = daoFactory.getBookDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            List<Reservation> reservations = reservationDao.findByUserId(userId, offset, itemsOnPage);
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
    public int countReservationsDtoByUserId(int userId) throws ServiceException {
        ReservationDao reservationDao = MySqlDaoFactory.getInstance().getReservationDao();

        try {
            return reservationDao.countByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getReservationsCountByUserIdAndStatusId(int userId, int statusId) throws ServiceException {
        ReservationDao reservationDao = MySqlDaoFactory.getInstance().getReservationDao();
        try {
            return reservationDao.findByUserIdAndStatusId(userId, statusId).size();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean makeReservation(int userId, String bookIsbn) throws ServiceException {
        ReservationDao reservationDao = MySqlDaoFactory.getInstance().getReservationDao();

        try {
            return reservationDao.reserve(userId, bookIsbn) != -1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int cancelReservation(int reservationId) throws ServiceException {
        ReservationDao reservationDao = MySqlDaoFactory.getInstance().getReservationDao();

        try {
            Reservation reservation = reservationDao.findById(reservationId);
            if (reservation.getReservationStatus() == ReservationStatus.ISSUED) {
                throw new ServiceException("Unable to cancel reservation with status 'ISSUED'");
            }
            return reservationDao.cancel(reservation);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Reservation getByReservationId(int reservationId) throws ServiceException {
        ReservationDao reservationDao = MySqlDaoFactory.getInstance().getReservationDao();

        try {
            return reservationDao.findById(reservationId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ReservationDto> getUnconfirmedReservationsDto(int page, int itemsOnPage) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        BookDao bookDao = daoFactory.getBookDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            List<Reservation> reservations = reservationDao.findByStatusId(ReservationStatus.RESERVED.ordinal() + 1, offset, itemsOnPage);
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
    public int countUnconfirmedReservationsDto() throws ServiceException {
        ReservationDao reservationDao = MySqlDaoFactory.getInstance().getReservationDao();
        try {
            return reservationDao.countByStatusId(ReservationStatus.RESERVED.ordinal() + 1, new Date());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void confirmReservation(int reservationId) throws ServiceException {
        ReservationDao reservationDao = MySqlDaoFactory.getInstance().getReservationDao();

        try {
            reservationDao.setStatus(reservationId, ReservationStatus.READY_TO_ISSUE.ordinal() + 1);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
