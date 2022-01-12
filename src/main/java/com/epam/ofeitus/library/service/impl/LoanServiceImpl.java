package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.*;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.dto.LoanDto;
import com.epam.ofeitus.library.entity.order.Loan;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.entity.order.constiuent.LoanStatus;
import com.epam.ofeitus.library.service.LoansService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class LoanServiceImpl implements LoansService {
    @Override
    public List<LoanDto> getLoansDtoByUserId(int userId) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        LoanDao loanDao = daoFactory.getLoanDao();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        BookDao bookDao = daoFactory.getBookDao();

        try {
            List<Loan> loans = loanDao.findByUserId(userId);
            List<LoanDto> loansDto = new ArrayList<>();
            for (Loan loan : loans) {
                CopyOfBook copyOfBook = copyOfBookDao.findById(loan.getInventoryId());
                Book book = bookDao.findByIsbn(copyOfBook.getBookIsbn());
                loansDto.add(new LoanDto(
                        loan.getLoanId(),
                        loan.getIssueDate(),
                        loan.getDueDate(),
                        loan.getReturnDate(),
                        loan.getFineAmount(),
                        loan.getUserId(),
                        loan.getInventoryId(),
                        loan.getLoanStatus(),
                        book
                        )
                );
            }
            return loansDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<LoanDto> getLoansDtoByUserIdWithFine(int userId) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        LoanDao loanDao = daoFactory.getLoanDao();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        BookDao bookDao = daoFactory.getBookDao();

        try {
            List<Loan> loans = loanDao.findByUserIdWithFine(userId);
            List<LoanDto> loansDto = new ArrayList<>();
            for (Loan loan : loans) {
                CopyOfBook copyOfBook = copyOfBookDao.findById(loan.getInventoryId());
                Book book = bookDao.findByIsbn(copyOfBook.getBookIsbn());
                loansDto.add(new LoanDto(
                                loan.getLoanId(),
                                loan.getIssueDate(),
                                loan.getDueDate(),
                                loan.getReturnDate(),
                                loan.getFineAmount(),
                                loan.getUserId(),
                                loan.getInventoryId(),
                                loan.getLoanStatus(),
                                book
                        )
                );
            }
            return loansDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getDebtsCountByUserId(int userId) throws ServiceException {
        LoanDao loanDao = MySqlDaoFactory.getInstance().getLoanDao();
        try {
            return loanDao.findDebtsByUserId(userId).size();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getLoansCountByUserIdAndStatusId(int userId, int statusId) throws ServiceException {
        LoanDao loanDao = MySqlDaoFactory.getInstance().getLoanDao();
        try {
            return loanDao.findByUserIdAndStatusId(userId, statusId).size();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean loanFromReservation(int reservationId) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        LoanDao loanDao = daoFactory.getLoanDao();
        try {
            Reservation reservation = reservationDao.findById(reservationId);
            return loanDao.loanFromReservation(reservation) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
