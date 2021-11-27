package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.ReservationDao;
import com.epam.ofeitus.library.entity.order.Reservation;

public class MySqlReservationDao extends AbstractMySqlDao<Reservation> implements ReservationDao {
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
    public int deleteById(int id) {
        // TODO
        return 0;
    }
}
