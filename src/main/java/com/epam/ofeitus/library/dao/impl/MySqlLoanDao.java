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
    public static final String SAVE_LOAN_QUERY = String.format(
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
    private static final String SAVE_LOAN_WITH_LAST_LOANED_COPY_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) " +
                    "SELECT 0, ?, ?, ?, ?, ?, LAST_INSERT_ID(), ? FROM dual WHERE LAST_INSERT_ID() != 0",
            Table.LOAN_TABLE,
            Column.LOAN_ID,
            Column.LOAN_ISSUE_DATE,
            Column.LOAN_DUE_DATE,
            Column.LOAN_RETURN_DATE,
            Column.LOAN_FINE_AMOUNT,
            Column.LOAN_USER_ID,
            Column.LOAN_INVENTORY_ID,
            Column.LOAN_STATUS_ID);
    public static final String UPDATE_LOAN_QUERY = String.format(
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
            "SELECT * FROM %s WHERE %s=? ORDER BY %s DESC, %s LIMIT ?, ?",
            Table.LOAN_TABLE,
            Column.LOAN_USER_ID,
            Column.LOAN_ISSUE_DATE,
            Column.LOAN_STATUS_ID);
    private static final String COUNT_BY_USER_ID_QUERY = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s=?",
            Table.LOAN_TABLE,
            Column.LOAN_USER_ID);
    private static final String FIND_BY_INVENTORY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? ORDER BY %s DESC",
            Table.LOAN_TABLE,
            Column.LOAN_INVENTORY_ID,
            Column.LOAN_ISSUE_DATE);
    private static final String COUNT_BY_USER_ID_AND_STATUS_ID_QUERY = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s=? AND %s=?",
            Table.LOAN_TABLE,
            Column.LOAN_USER_ID,
            Column.LOAN_STATUS_ID);
    private static final String FIND_BY_USER_ID_WITH_FINE_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND (%s='3' OR %s='4') ORDER BY %s DESC, %s LIMIT ?, ?",
            Table.LOAN_TABLE,
            Column.LOAN_USER_ID,
            Column.LOAN_STATUS_ID,
            Column.LOAN_STATUS_ID,
            Column.LOAN_ISSUE_DATE,
            Column.LOAN_STATUS_ID);
    private static final String COUNT_BY_USER_ID_WITH_FINE_QUERY = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s=? AND (%s='3' OR %s='4')",
            Table.LOAN_TABLE,
            Column.LOAN_USER_ID,
            Column.LOAN_STATUS_ID,
            Column.LOAN_STATUS_ID);
    private static final String COUNT_DEBTS_BY_USER_ID = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s=? AND %s='1' AND %s < CURDATE()",
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
            "UPDATE %s SET %s='4', %s=LAST_INSERT_ID(%s) WHERE %s=? AND %s='1' ORDER BY %s LIMIT 1",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_ISBN,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID);
    private static final String MAKE_COPY_OF_BOOK_LOANED_BY_ID_QUERY = String.format(
            "UPDATE %s SET %s='4', %s=LAST_INSERT_ID(%s) WHERE %s=? AND %s='1'",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_STATUS_ID);
    private static final String MAKE_RESERVED_COPY_OF_BOOK_LOANED_BY_ID_QUERY = String.format(
            "UPDATE %s SET %s='4', %s=LAST_INSERT_ID(%s) WHERE %s=? AND %s='3'",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_STATUS_ID);
    private static final String MAKE_COPY_OF_BOOK_AVAILABLE_QUERY = String.format(
            "UPDATE %s SET %s='1' WHERE %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID);
    private static final String COUNT_BY_STATUS_ID_QUERY = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s <= ?",
            Table.LOAN_TABLE,
            Column.LOAN_ISSUE_DATE);

    public MySqlLoanDao() {
        super(RowMapperFactory.getInstance().getLoanRowMapper(), Table.LOAN_TABLE, Column.LOAN_ID);
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
                entity.getLoanStatus().ordinal() + 1,
                entity.getLoanId()
        );
    }

    @Override
    public List<Loan> findByUserId(int userId, int offset, int itemsOnPage) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_USER_ID_QUERY, userId, offset, itemsOnPage);
    }

    @Override
    public int countByUserId(int userId) throws DaoException {
        return queryOperator.executeCountQuery(COUNT_BY_USER_ID_QUERY, userId);
    }

    @Override
    public List<Loan> findByInventoryId(int inventoryId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_INVENTORY_ID_QUERY, inventoryId);
    }

    @Override
    public List<Loan> findByUserIdWithFine(int userId, int offset, int itemsOnPage) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_USER_ID_WITH_FINE_QUERY, userId, offset, itemsOnPage);
    }

    @Override
    public int countByUserIdWithFine(int userId) throws DaoException {
        return queryOperator.executeCountQuery(COUNT_BY_USER_ID_WITH_FINE_QUERY, userId);
    }

    @Override
    public int countDebtsByUserId(int userId) throws DaoException {
        return queryOperator.executeCountQuery(COUNT_DEBTS_BY_USER_ID, userId);
    }

    @Override
    public int countByUserIdAndStatusId(int userId, int statusId) throws DaoException {
        return queryOperator.executeCountQuery(COUNT_BY_USER_ID_AND_STATUS_ID_QUERY, userId, statusId);
    }

    @Override
    public int loanByUserId(int userId, String bookIsbn, int loanPeriod) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                MAKE_COPY_OF_BOOK_LOANED_QUERY,
                bookIsbn));
        parametrizedQueries.add(new ParametrizedQuery(
                SAVE_LOAN_WITH_LAST_LOANED_COPY_QUERY,
                new Date(),
                DateUtils.addDays(new Date(), loanPeriod),
                null,
                null,
                userId,
                LoanStatus.ISSUED.ordinal() + 1));
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int loanByInventoryId(int userId, int inventoryId, int loanPeriod) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                MAKE_COPY_OF_BOOK_LOANED_BY_ID_QUERY,
                inventoryId));
        parametrizedQueries.add(new ParametrizedQuery(
                SAVE_LOAN_WITH_LAST_LOANED_COPY_QUERY,
                new Date(),
                DateUtils.addDays(new Date(), loanPeriod),
                null,
                null,
                userId,
                LoanStatus.ISSUED.ordinal() + 1));
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public void loanFromReservation(Reservation reservation, int loanPeriod) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                MAKE_RESERVED_COPY_OF_BOOK_LOANED_BY_ID_QUERY,
                reservation.getInventoryId()));
        parametrizedQueries.add(new ParametrizedQuery(
                MAKE_RESERVATION_ISSUED_QUERY,
                reservation.getReservationId()));
        parametrizedQueries.add(new ParametrizedQuery(
                SAVE_LOAN_QUERY,
                new Date(),
                DateUtils.addDays(new Date(), loanPeriod),
                null,
                null,
                reservation.getUserId(),
                reservation.getInventoryId(),
                LoanStatus.ISSUED.ordinal() + 1));
        queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public void returnNoFine(Loan loan) throws DaoException {
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
        queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public void returnWithFine(Loan loan, BigDecimal fineRate) throws DaoException {
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
        queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int countAll(Date date) throws DaoException {
        return queryOperator.executeCountQuery(COUNT_BY_STATUS_ID_QUERY,  date);
    }
}
