package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.*;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.entity.dto.LoanDto;
import com.epam.ofeitus.library.entity.order.Loan;
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

        try {
            List<Loan> loans = loanDao.findByUserId(userId);
            List<LoanDto> loansDto = new ArrayList<>();
            for (Loan loan : loans) {
                List<CopyOfBook> copiesOfBooks = copyOfBookDao.findByLoanId(loan.getLoanId());
                loansDto.add(new LoanDto(
                        loan.getLoanId(),
                        copiesOfBooks,
                        loan.getIssueDate(),
                        loan.getDueDate(),
                        loan.getReturnDate(),
                        loan.getFineAmount(),
                        loan.getUserId()
                        )
                );
            }
            return loansDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
