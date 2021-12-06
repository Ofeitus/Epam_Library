package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.order.Loan;

import java.util.List;

public interface LoanDao extends AbstractDao<Loan> {
    int save(Loan entity, List<CopyOfBook> copiesOfBooks) throws DaoException;

    List<Loan> findByUserId(int userId) throws DaoException;
}
