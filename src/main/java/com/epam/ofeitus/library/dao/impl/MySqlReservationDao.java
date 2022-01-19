package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.ReservationDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.ParametrizedQuery;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.entity.order.constiuent.ReservationStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlReservationDao extends AbstractMySqlDao<Reservation> implements ReservationDao {
    public final static String SAVE_RESERVATION_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (0, ?, ?, LAST_INSERT_ID(), ?)",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_ID,
            Column.RESERVATION_DATE,
            Column.RESERVATION_USER_ID,
            Column.RESERVATION_INVENTORY_ID,
            Column.RESERVATION_STATUS_ID);
    public final static String UPDATE_RESERVATION_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_DATE,
            Column.RESERVATION_USER_ID,
            Column.RESERVATION_INVENTORY_ID,
            Column.RESERVATION_STATUS_ID,
            Column.RESERVATION_ID);
    public final static String SET_STATUS_QUERY = String.format(
            "UPDATE %s SET %s=? WHERE %s=?",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_STATUS_ID,
            Column.RESERVATION_ID);
    private static final String FIND_BY_USER_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? ORDER BY %s DESC, %s LIMIT ?, ?",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_USER_ID,
            Column.RESERVATION_DATE,
            Column.RESERVATION_STATUS_ID);
    private static final String COUNT_BY_USER_ID_QUERY = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s=?",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_USER_ID);
    private static final String FIND_BY_INVENTORY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? ORDER BY %s DESC",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_INVENTORY_ID,
            Column.RESERVATION_DATE);
    private static final String FIND_BY_STATUS_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? LIMIT ?, ?",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_STATUS_ID);
    private static final String COUNT_BY_STATUS_ID_QUERY = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s=? AND %s <= ?",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_STATUS_ID,
            Column.RESERVATION_DATE);
    private static final String FIND_BY_USER_ID_AND_STATUS_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s=?",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_USER_ID,
            Column.RESERVATION_STATUS_ID);
    private static final String MAKE_COPY_OF_BOOK_AVAILABLE_QUERY = String.format(
            "UPDATE %s SET %s='1' WHERE %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID);
    private static final String MAKE_COPY_OF_BOOK_RESERVED_QUERY = String.format(
            "UPDATE %s SET %s='3', %s=LAST_INSERT_ID(%s) WHERE %s=? AND %s='1' ORDER BY %s LIMIT 1",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_ISBN,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID);

    public MySqlReservationDao() {
        super(RowMapperFactory.getReservationRowMapper(), Table.RESERVATION_TABLE, Column.RESERVATION_ID);
    }

    @Override
    public int save(Reservation entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_RESERVATION_QUERY,
                entity.getDate(),
                entity.getUserId(),
                entity.getReservationStatus().ordinal() + 1
        );
    }

    @Override
    public int update(Reservation entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_RESERVATION_QUERY,
                entity.getDate(),
                entity.getUserId(),
                entity.getInventoryId(),
                entity.getReservationStatus().ordinal() + 1,
                entity.getReservationId());
    }

    @Override
    public List<Reservation> findByUserId(int userId, int offset, int itemsOnPage) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_USER_ID_QUERY, userId, offset, itemsOnPage);
    }

    @Override
    public int countByUserId(int userId) throws DaoException {
        return queryOperator.executeCountQuery(COUNT_BY_USER_ID_QUERY, userId);
    }

    @Override
    public List<Reservation> findByInventoryId(int inventoryId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_INVENTORY_ID_QUERY, inventoryId);
    }

    @Override
    public List<Reservation> findByStatusId(int statusId, int offset, int itemsOnPage) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_STATUS_ID_QUERY, statusId, offset, itemsOnPage);
    }

    @Override
    public int countByStatusId(int statusId, Date date) throws DaoException {
        return queryOperator.executeCountQuery(COUNT_BY_STATUS_ID_QUERY, statusId, date);
    }

    @Override
    public List<Reservation> findByUserIdAndStatusId(int userId, int statusId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_USER_ID_AND_STATUS_ID_QUERY, userId, statusId);
    }

    @Override
    public int cancel(Reservation reservation) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                MAKE_COPY_OF_BOOK_AVAILABLE_QUERY,
                reservation.getInventoryId()));
        parametrizedQueries.add(new ParametrizedQuery(
                DELETE_BY_ID_QUERY,
                reservation.getReservationId()));
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int reserve(int userId, String bookIsbn) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                MAKE_COPY_OF_BOOK_RESERVED_QUERY,
                bookIsbn));
        parametrizedQueries.add(new ParametrizedQuery(
                SAVE_RESERVATION_QUERY,
                new Date(),
                userId,
                ReservationStatus.RESERVED.ordinal() + 1));
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int setStatus(int reservationId, int statusId) throws DaoException {
        return queryOperator.executeUpdate(SET_STATUS_QUERY, statusId, reservationId);
    }
}
