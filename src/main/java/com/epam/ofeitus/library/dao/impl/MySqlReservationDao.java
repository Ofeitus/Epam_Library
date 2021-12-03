package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.ReservationDao;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.entity.order.constiuent.ReservationStatus;

import java.util.List;

public class MySqlReservationDao extends AbstractMySqlDao<Reservation> implements ReservationDao {
    public MySqlReservationDao() {
        super(RowMapperFactory.getReservationRowMapper(), Table.RESERVATION_TABLE, Column.RESERVATION_ID);
    }

    @Override
    public int save(Reservation entity) {
        // TODO
        return 0;
    }

    @Override
    public int update(Reservation entity) {
        // TODO
        return 0;
    }

    @Override
    public List<Reservation> findByUserId(int id) {
        // TODO
        return null;
    }

    @Override
    public List<Reservation> findByStatus(ReservationStatus status) {
        // TODO
        return null;
    }
}
