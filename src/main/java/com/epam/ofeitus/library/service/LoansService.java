package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.dto.LoanDto;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Loans service interface.
 */
public interface LoansService {
    /**
     * Loan book to user with given id.
     *
     * @param userId     user id
     * @param bookIsbn   book isbn
     * @param loanPeriod loan period
     * @return if book successfully loaned
     * @throws ServiceException thrown when dao exception occurs
     */
    boolean loanByUserId(int userId, String bookIsbn, int loanPeriod) throws ServiceException;

    /**
     * Loan book with given inventory id.
     *
     * @param userId      user id
     * @param inventoryId inventory id
     * @param loanPeriod  loan period
     * @return if book successfully loaned
     * @throws ServiceException thrown when dao exception occurs
     */
    boolean loanByInventoryId(int userId, int inventoryId, int loanPeriod) throws ServiceException;

    /**
     * Loan book reserved by user.
     *
     * @param reservationId reservation id
     * @param loanPeriod    loan period
     * @throws ServiceException thrown when dao exception occurs
     */
    void loanFromReservation(int reservationId, int loanPeriod) throws ServiceException;

    /**
     * Return loaned book
     *
     * @param loanId   loan id
     * @param fineRate fine rate to calculate fine amount, fine amount = fine rate * days overdue
     * @throws ServiceException thrown when dao exception occurs
     */
    void returnBook(int loanId, BigDecimal fineRate) throws ServiceException;

    /**
     * Mark fine as paid.
     *
     * @param loanId loan id
     * @throws ServiceException thrown when dao exception occurs
     */
    void payFine(int loanId) throws ServiceException;

    /**
     * Gets loans Dto by user id on given page.
     *
     * @param userId      user id
     * @param page        page
     * @param itemsOnPage items on page
     * @return list of loans dto
     * @throws ServiceException thrown when dao exception occurs
     */
    List<LoanDto> getLoansDtoByUserId(int userId, int page, int itemsOnPage) throws ServiceException;

    /**
     * Gets count of loans Dto by user id.
     *
     * @param userId user id
     * @return loans dto count
     * @throws ServiceException thrown when dao exception occurs
     */
    int countLoansDtoByUserId(int userId) throws ServiceException;

    /**
     * Gets loans Dto by user id with fine.
     *
     * @param userId      user id
     * @param page        page
     * @param itemsOnPage items on page
     * @return list of loans Dto
     * @throws ServiceException thrown when dao exception occurs
     */
    List<LoanDto> getLoansDtoByUserIdWithFine(int userId, int page, int itemsOnPage) throws ServiceException;

    /**
     * Gets count of loans dto by user id with fine.
     *
     * @param userId user id
     * @return loans count
     * @throws ServiceException thrown when dao exception occurs
     */
    int countLoansDtoByUserIdWithFine(int userId) throws ServiceException;

    /**
     * Gets count of overdue loans by user id.
     *
     * @param userId user id
     * @return loans count
     * @throws ServiceException thrown when dao exception occurs
     */
    int countDebtsByUserId(int userId) throws ServiceException;

    /**
     * Gets count of loans by user id and status id.
     *
     * @param userId   user id
     * @param statusId status id
     * @return loans count
     * @throws ServiceException thrown when dao exception occurs
     */
    int countLoansByUserIdAndStatusId(int userId, int statusId) throws ServiceException;
}
