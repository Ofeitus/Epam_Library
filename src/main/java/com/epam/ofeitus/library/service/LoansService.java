package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.dto.LoanDto;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface LoansService {
    List<LoanDto> getLoansDtoByUserId(int userId) throws ServiceException;

    List<LoanDto> getLoansDtoByUserIdWithFine(int userId) throws ServiceException;

    int getDebtsCountByUserId(int userId) throws ServiceException;

    int getLoansCountByUserIdAndStatusId(int userId, int statusId) throws ServiceException;

    boolean loanFromReservation(int reservationId, int loanPeriod) throws ServiceException;

    void returnBook(int loanId, BigDecimal fineRate) throws ServiceException;
}
