package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.order.Loan;
import com.epam.ofeitus.library.entity.order.Reservation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Loan dao interface.
 */
public interface LoanDao extends AbstractDao<Loan> {
    /**
     * Find by inventory id.
     *
     * @param inventoryId inventory id
     * @return loans list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<Loan> findByInventoryId(int inventoryId) throws DaoException;

    /**
     * Find by user id.
     *
     * @param userId      user id
     * @param offset      offset
     * @param itemsOnPage items on page
     * @return loans list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<Loan> findByUserId(int userId, int offset, int itemsOnPage) throws DaoException;

    /**
     * Count by user id.
     *
     * @param userId user id
     * @return loans count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countByUserId(int userId) throws DaoException;

    /**
     * Find by user id with fine.
     *
     * @param userId      user id
     * @param offset      offset
     * @param itemsOnPage items on page
     * @return loans list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<Loan> findByUserIdWithFine(int userId, int offset, int itemsOnPage) throws DaoException;

    /**
     * Count by user id with fine.
     *
     * @param userId user id
     * @return loans count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countByUserIdWithFine(int userId) throws DaoException;

    /**
     * Count overdue loans by user id.
     *
     * @param userId user id
     * @return loans count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countDebtsByUserId(int userId) throws DaoException;

    /**
     * Count loans by user id and status id.
     *
     * @param userId   user id
     * @param statusId status id
     * @return loans count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countByUserIdAndStatusId(int userId, int statusId) throws DaoException;

    /**
     * Count all before given date.
     *
     * @param date date
     * @return loans count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countAll(Date date) throws DaoException;

    /**
     * Finds available copy of book, makes it loaned and saves loan with given user id.
     *
     * @param userId     user id
     * @param bookIsbn   book isbn
     * @param loanPeriod loan period
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int loanByUserId(int userId, String bookIsbn, int loanPeriod) throws DaoException;

    /**
     * Makes copy of book loaned and saves loan.
     *
     * @param userId      user id
     * @param inventoryId inventory id
     * @param loanPeriod  loan period
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int loanByInventoryId(int userId, int inventoryId, int loanPeriod) throws DaoException;

    /**
     * Makes copy of book and reservation loaned, saves loan.
     *
     * @param reservation reservation
     * @param loanPeriod  loan period
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    void loanFromReservation(Reservation reservation, int loanPeriod) throws DaoException;

    /**
     * Makes copy of book available, updates loan return date and makes it returned.
     *
     * @param loan loan
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    void returnNoFine(Loan loan) throws DaoException;

    /**
     * Makes copy of book available, updates loan return date and fine amount, makes it fined.
     *
     * @param loan     loan
     * @param fineRate fine rate
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    void returnWithFine(Loan loan, BigDecimal fineRate) throws DaoException;
}
