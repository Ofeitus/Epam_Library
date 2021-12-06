package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.LoanDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.ParametrizedQuery;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.order.Loan;

import java.util.ArrayList;
import java.util.List;

public class MySqlLoanDao extends AbstractMySqlDao<Loan> implements LoanDao {
    public final static String SAVE_LOAN_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (0, ?, ?, ?, ?, ?)",
            Table.LOAN_TABLE,
            Column.LOAN_ID,
            Column.LOAN_ISSUE_DATE,
            Column.LOAN_DUE_DATE,
            Column.LOAN_RETURN_DATE,
            Column.LOAN_FINE_AMOUNT,
            Column.LOAN_USER_ID);
    public final static String ADD_COPY_OF_BOOK_TO_LOAN_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?)",
            Table.LOAN_HAS_COPY_OF_BOOK_TABLE,
            Column.BOOK_INVENTORY_ID,
            Column.LOAN_ID);
    public final static String UPDATE_LOAN_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.LOAN_TABLE,
            Column.LOAN_ISSUE_DATE,
            Column.LOAN_DUE_DATE,
            Column.LOAN_RETURN_DATE,
            Column.LOAN_FINE_AMOUNT,
            Column.LOAN_USER_ID,
            Column.LOAN_ID);
    private static final String FIND_BY_USER_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.LOAN_TABLE,
            Column.LOAN_USER_ID);

    public MySqlLoanDao() {
        super(RowMapperFactory.getLoanRowMapper(), Table.LOAN_TABLE, Column.LOAN_ID);
    }

    @Override
    public int save(Loan entity) throws DaoException {
        throw new DaoException("Unsupported operation for Loan table.");
    }

    @Override
    public int save(Loan entity, List<CopyOfBook> copiesOfBooks) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                SAVE_LOAN_QUERY,
                entity.getIssueDate(),
                entity.getDueDate(),
                entity.getReturnDate(),
                entity.getFineAmount(),
                entity.getUserId()
        ));
        for (CopyOfBook copyOfBook : copiesOfBooks) {
            parametrizedQueries.add(new ParametrizedQuery(
                    ADD_COPY_OF_BOOK_TO_LOAN_QUERY,
                    copyOfBook.getInventoryId(),
                    entity.getLoanId()
            ));
        }
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int update(Loan entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_LOAN_QUERY,
                entity.getLoanId(),
                entity.getIssueDate(),
                entity.getDueDate(),
                entity.getReturnDate(),
                entity.getFineAmount(),
                entity.getUserId()
        );
    }

    @Override
    public List<Loan> findByUserId(int userId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_USER_ID_QUERY, userId);
    }
}
