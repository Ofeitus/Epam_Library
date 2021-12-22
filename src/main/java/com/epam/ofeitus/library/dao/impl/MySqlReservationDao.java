package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.ReservationDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.order.Reservation;

import java.util.List;

public class MySqlReservationDao extends AbstractMySqlDao<Reservation> implements ReservationDao {
    public final static String SAVE_RESERVATION_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (0, ?, ?, ?, ?)",
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
    private static final String FIND_BY_USER_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_USER_ID);
    private static final String FIND_BY_STATUS_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.RESERVATION_TABLE,
            Column.RESERVATION_STATUS_ID);

    public MySqlReservationDao() {
        super(RowMapperFactory.getReservationRowMapper(), Table.RESERVATION_TABLE, Column.RESERVATION_ID);
    }

    @Override
    public int save(Reservation entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_RESERVATION_QUERY,
                entity.getDate(),
                entity.getUserId(),
                entity.getInventoryId(),
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
    public List<Reservation> findByUserId(int userId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_USER_ID_QUERY, userId);
    }

    @Override
    public List<Reservation> findByStatusId(int statusId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_STATUS_ID_QUERY, statusId);
    }
}
