package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.LoanDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.ParametrizedQuery;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.order.Loan;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.entity.order.constiuent.LoanStatus;
import org.apache.commons.lang.time.DateUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MySqlLoanDao extends AbstractMySqlDao<Loan> implements LoanDao {
    public final static String SAVE_LOAN_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES (0, ?, ?, ?, ?, ?, ?, ?)",
            Table.LOAN_TABLE,
            Column.LOAN_ID,
            Column.LOAN_ISSUE_DATE,
            Column.LOAN_DUE_DATE,
            Column.LOAN_RETURN_DATE,
            Column.LOAN_FINE_AMOUNT,
            Column.LOAN_USER_ID,
            Column.LOAN_INVENTORY_ID,
            Column.LOAN_STATUS_ID);
    public final static String UPDATE_LOAN_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.LOAN_TABLE,
            Column.LOAN_ISSUE_DATE,
            Column.LOAN_DUE_DATE,
            Column.LOAN_RETURN_DATE,
            Column.LOAN_FINE_AMOUNT,
            Column.LOAN_USER_ID,
            Column.LOAN_INVENTORY_ID,
            Column.LOAN_STATUS_ID,
            Column.LOAN_ID);
    private static final String FIND_BY_USER_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? ORDER BY %s DESC",
            Table.LOAN_TABLE,
            Column.LOAN_USER_ID,
            Column.LOAN_ISSUE_DATE);
    private static final String FIND_BY_INVENTORY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? ORDER BY %s DESC",
            Table.LOAN_TABLE,
            Column.LOAN_INVENTORY_ID,
            Column.LOAN_ISSUE_DATE);
    private static final String FIND_BY_STATUS_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s=?",
            Table.LOAN_TABLE,
            Column.LOAN_USER_ID,
            Column.LOAN_STATUS_ID);
    private static final String FIND_BY_USER_ID_WITH_FINE_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND (%s='3' OR %s='4') ORDER BY %s DESC",
            Table.LOAN_TABLE,
            Column.LOAN_USER_ID,
            Column.LOAN_STATUS_ID,
            Column.LOAN_STATUS_ID,
            Column.LOAN_ISSUE_DATE);
    private static final String FIND_DEBTS_BY_USER_ID = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s='1' AND %s < CURDATE()",
            Table.LOAN_TABLE,
            Column.LOAN_USER_ID,
            Column.LOAN_STATUS_ID,
            Column.LOAN_DUE_DATE);
    private static final String MAKE_RESERVATION_ISSUED_QUERY = String.format(
            "UPDATE %s SET %s='3' WHERE %s=?",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_STATUS_ID,
            Column.RESERVATION_ID);
    private static final String MAKE_COPY_OF_BOOK_LOANED_QUERY = String.format(
            "UPDATE %s SET %s='4' WHERE %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID);
    private static final String MAKE_COPY_OF_BOOK_AVAILABLE_QUERY = String.format(
            "UPDATE %s SET %s='1' WHERE %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID);

    public MySqlLoanDao() {
        super(RowMapperFactory.getLoanRowMapper(), Table.LOAN_TABLE, Column.LOAN_ID);
    }

    @Override
    public int save(Loan entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_LOAN_QUERY,
                entity.getIssueDate(),
                entity.getDueDate(),
                entity.getReturnDate(),
                entity.getFineAmount(),
                entity.getUserId(),
                entity.getInventoryId(),
                entity.getLoanStatus().ordinal() + 1
        );
    }

    @Override
    public int update(Loan entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_LOAN_QUERY,
                entity.getIssueDate(),
                entity.getDueDate(),
                entity.getReturnDate(),
                entity.getFineAmount(),
                entity.getUserId(),
                entity.getInventoryId(),
                entity.getLoanStatus(),
                entity.getLoanId()
        );
    }

    @Override
    public List<Loan> findByUserId(int userId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_USER_ID_QUERY, userId);
    }

    @Override
    public List<Loan> findByInventoryId(int inventoryId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_INVENTORY_ID_QUERY, inventoryId);
    }

    @Override
    public List<Loan> findByUserIdWithFine(int userId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_USER_ID_WITH_FINE_QUERY, userId);
    }

    @Override
    public List<Loan> findDebtsByUserId(int userId) throws DaoException {
        return queryOperator.executeQuery(FIND_DEBTS_BY_USER_ID, userId);
    }

    @Override
    public List<Loan> findByUserIdAndStatusId(int userId, int statusId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_STATUS_ID_QUERY, userId, statusId);
    }

    @Override
    public int loanFromReservation(Reservation reservation, int loanPeriod) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                MAKE_RESERVATION_ISSUED_QUERY,
                reservation.getReservationId()));
        parametrizedQueries.add(new ParametrizedQuery(
                MAKE_COPY_OF_BOOK_LOANED_QUERY,
                reservation.getInventoryId()));
        parametrizedQueries.add(new ParametrizedQuery(
                SAVE_LOAN_QUERY,
                new Date(),
                DateUtils.addDays(new Date(), 30),
                null,
                null,
                reservation.getUserId(),
                reservation.getInventoryId(),
                LoanStatus.ISSUED.ordinal() + 1));
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int returnNoFine(Loan loan) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                MAKE_COPY_OF_BOOK_AVAILABLE_QUERY,
                loan.getInventoryId()));
        parametrizedQueries.add(new ParametrizedQuery(
                UPDATE_LOAN_QUERY,
                loan.getIssueDate(),
                loan.getDueDate(),
                new Date(),
                null,
                loan.getUserId(),
                loan.getInventoryId(),
                LoanStatus.RETURNED.ordinal() + 1,
                loan.getLoanId()));
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int returnWithFine(Loan loan, BigDecimal fineRate) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                MAKE_COPY_OF_BOOK_AVAILABLE_QUERY,
                loan.getInventoryId()));
        parametrizedQueries.add(new ParametrizedQuery(
                UPDATE_LOAN_QUERY,
                loan.getIssueDate(),
                loan.getDueDate(),
                new Date(),
                new BigDecimal(TimeUnit.DAYS.convert(
                        new Date().getTime() - loan.getDueDate().getTime(),
                        TimeUnit.MILLISECONDS
                )).multiply(fineRate),
                loan.getUserId(),
                loan.getInventoryId(),
                LoanStatus.FINED.ordinal() + 1,
                loan.getLoanId()));
        return queryOperator.executeTransaction(parametrizedQueries);
    }
}
