package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.LoanDao;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.order.Loan;

import java.util.List;

public class MySqlLoanDao extends AbstractMySqlDao<Loan> implements LoanDao {
    public MySqlLoanDao() {
        super(RowMapperFactory.getLoanRowMapper(), Table.LOAN_TABLE, Column.LOAN_ID);
    }

    @Override
    public int save(Loan entity) {
        // TODO
        return 0;
    }

    @Override
    public int update(Loan entity) {
        // TODO
        return 0;
    }

    @Override
    public List<Loan> findByUserId(int userId) {
        // TODO
        return null;
    }

    @Override
    public List<Loan> findOverdueLoans() {
        // TODO
        return null;
    }
}
