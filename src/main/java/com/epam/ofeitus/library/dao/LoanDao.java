package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.order.Loan;
import com.epam.ofeitus.library.entity.order.Reservation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface LoanDao extends AbstractDao<Loan> {
    List<Loan> findByUserId(int userId, int offset, int itemsOnPage) throws DaoException;

    int countByUserId(int userId) throws DaoException;

    List<Loan> findByInventoryId(int inventoryId) throws DaoException;

    List<Loan> findByUserIdWithFine(int userId, int offset, int itemsOnPage) throws DaoException;

    int countByUserIdWithFine(int userId) throws DaoException;

    int countDebtsByUserId(int userId) throws DaoException;

    int countByUserIdAndStatusId(int userId, int statusId) throws DaoException;

    int loan(int userId, String bookIsbn, int loanPeriod) throws DaoException;

    int loanByInventoryId(int userId, int inventoryId, int loanPeriod) throws DaoException;

    int loanFromReservation(Reservation reservation, int loanPeriod) throws DaoException;

    int returnNoFine(Loan loan) throws DaoException;

    int returnWithFine(Loan loan, BigDecimal fineRate) throws DaoException;

    int countAll(Date date) throws DaoException;
}
