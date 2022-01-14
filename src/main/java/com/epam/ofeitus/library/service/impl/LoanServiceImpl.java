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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanServiceImpl implements LoansService {
    @Override
    public List<LoanDto> getLoansDtoByUserId(int userId, int page, int itemsOnPage) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        LoanDao loanDao = daoFactory.getLoanDao();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        BookDao bookDao = daoFactory.getBookDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            List<Loan> loans = loanDao.findByUserId(userId, offset, itemsOnPage);
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
    public int countLoansDtoByUserId(int userId) throws ServiceException {
        LoanDao loanDao = MySqlDaoFactory.getInstance().getLoanDao();

        try {
            return loanDao.countByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<LoanDto> getLoansDtoByUserIdWithFine(int userId, int page, int itemsOnPage) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        LoanDao loanDao = daoFactory.getLoanDao();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        BookDao bookDao = daoFactory.getBookDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            List<Loan> loans = loanDao.findByUserIdWithFine(userId, offset, itemsOnPage);
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
    public int countLoansDtoByUserIdWithFine(int userId) throws ServiceException {
        LoanDao loanDao = MySqlDaoFactory.getInstance().getLoanDao();
        try {
            return loanDao.countByUserIdWithFine(userId);
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
    public boolean loanBook(int userId, String bookIsbn, int loanPeriod) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        LoanDao loanDao = daoFactory.getLoanDao();
        try {
            return loanDao.loan(userId, bookIsbn, loanPeriod) != -1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean loanByInventoryId(int userId, int inventoryId, int loanPeriod) throws ServiceException {
        LoanDao loanDao = MySqlDaoFactory.getInstance().getLoanDao();
        try {
            return loanDao.loanByInventoryId(userId, inventoryId, loanPeriod) != -1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean loanFromReservation(int reservationId, int loanPeriod) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        LoanDao loanDao = daoFactory.getLoanDao();
        try {
            Reservation reservation = reservationDao.findById(reservationId);
            return loanDao.loanFromReservation(reservation, loanPeriod) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void returnBook(int loanId, BigDecimal fineRate) throws ServiceException {
        LoanDao loanDao = MySqlDaoFactory.getInstance().getLoanDao();
        try {
            Loan loan = loanDao.findById(loanId);
            if (loan.getDueDate().before(new Date())) {
                loanDao.returnWithFine(loan, fineRate);
            } else {
                loanDao.returnNoFine(loan);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void payFine(int loanId) throws ServiceException {
        LoanDao loanDao = MySqlDaoFactory.getInstance().getLoanDao();
        try {
            Loan loan = loanDao.findById(loanId);
            loan.setLoanStatus(LoanStatus.PAID);
            loanDao.update(loan);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
