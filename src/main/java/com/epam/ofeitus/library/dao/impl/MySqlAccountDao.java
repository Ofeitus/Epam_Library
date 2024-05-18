package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.AccountDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.bank.Account;

import java.util.List;

public class MySqlAccountDao extends AbstractMySqlDao<Account> implements AccountDao {
    private static final String SAVE_ACCOUNT_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?)",
            Table.ACCOUNT_TABLE,
            Column.ACCOUNT_ID,
            Column.ACCOUNT_TYPE,
            Column.ACCOUNT_NUMBER,
            Column.ACCOUNT_NAME,
            Column.ACCOUNT_CODE,
            Column.ACCOUNT_PURPOSE,
            Column.ACCOUNT_BALANCE,
            Column.ACCOUNT_CLIENT_TYPE,
            Column.ACCOUNT_CLIENT
    );
    private static final String FIND_ALL_ACCOUNTS_QUERY =
            "SELECT * FROM accounts AS a JOIN users AS u ON (a.acc_client = u.user_id) LIMIT ?, ?";
    private static final String COUNT_ALL_QUERY =
            "SELECT COUNT(*) FROM accounts";
    private static final String FIND_BY_USER_ID_QUERY =
            "SELECT * FROM accounts AS a JOIN users AS u ON (a.acc_client = u.user_id) WHERE acc_client = ?";

    public MySqlAccountDao() {
        super(RowMapperFactory.getInstance().getAccountRowMapper(), Table.ACCOUNT_TABLE, Column.ACCOUNT_ID);
    }

    @Override
    public int save(Account entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_ACCOUNT_QUERY,
                entity.getType().getName(),
                entity.getNumber(),
                entity.getName(),
                entity.getCode(),
                entity.getPurpose().getName(),
                entity.getBalance(),
                entity.getClientType().getName(),
                entity.getClient().getUserId()
        );
    }

    @Override
    public int update(Account entity) throws DaoException {
        return 0;
    }

    @Override
    public List<Account> findAll(int offset, int itemsOnPage) throws DaoException {
        return queryOperator.executeQuery(FIND_ALL_ACCOUNTS_QUERY, offset, itemsOnPage);
    }

    @Override
    public int countAll() throws DaoException {
        return queryOperator.executeCountQuery(COUNT_ALL_QUERY);
    }

    @Override
    public List<Account> findByUserId(int userId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_USER_ID_QUERY, userId);
    }
}
