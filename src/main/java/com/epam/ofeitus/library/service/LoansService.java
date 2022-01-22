package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.dto.LoanDto;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface LoansService {
    boolean loanByUserId(int userId, String bookIsbn, int loanPeriod) throws ServiceException;

    boolean loanByInventoryId(int userId, int inventoryId, int loanPeriod) throws ServiceException;

    void loanFromReservation(int reservationId, int loanPeriod) throws ServiceException;

    void returnBook(int loanId, BigDecimal fineRate) throws ServiceException;

    void payFine(int loanId) throws ServiceException;

    List<LoanDto> getLoansDtoByUserId(int userId, int page, int itemsOnPage) throws ServiceException;

    int countLoansDtoByUserId(int userId) throws ServiceException;

    List<LoanDto> getLoansDtoByUserIdWithFine(int userId, int page, int itemsOnPage) throws ServiceException;

    int countLoansDtoByUserIdWithFine(int userId) throws ServiceException;

    int countDebtsByUserId(int userId) throws ServiceException;

    int countLoansByUserIdAndStatusId(int userId, int statusId) throws ServiceException;
}
