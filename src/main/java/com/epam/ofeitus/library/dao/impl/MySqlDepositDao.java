package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.DepositDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.ParametrizedQuery;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.bank.*;
import com.epam.ofeitus.library.entity.user.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MySqlDepositDao extends AbstractMySqlDao<Deposit> implements DepositDao {
    private static final String SAVE_CURRENT_ACCOUNT = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?), (0, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            Table.ACCOUNT_TABLE,
            Column.ACCOUNT_ID,
            Column.ACCOUNT_TYPE,
            Column.ACCOUNT_NUMBER,
            Column.ACCOUNT_NAME,
            Column.ACCOUNT_CODE,
            Column.ACCOUNT_PURPOSE,
            Column.ACCOUNT_CURRENCY,
            Column.ACCOUNT_BALANCE,
            Column.ACCOUNT_CLIENT_TYPE,
            Column.ACCOUNT_CLIENT
    );
    private static final String SAVE_DEPOSIT_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, LAST_INSERT_ID(), LAST_INSERT_ID() + 1)",
            Table.DEPOSIT_TABLE,
            Column.DEPOSIT_ID,
            Column.DEPOSIT_TYPE,
            Column.DEPOSIT_NUMBER,
            Column.DEPOSIT_CURRENCY,
            Column.DEPOSIT_START,
            Column.DEPOSIT_END,
            Column.DEPOSIT_TERM,
            Column.DEPOSIT_AMOUNT,
            Column.DEPOSIT_PERCENT,
            Column.DEPOSIT_CURRENT_ACCOUNT,
            Column.DEPOSIT_PERCENT_ACCOUNT
    );

    public MySqlDepositDao() {
        super(RowMapperFactory.getInstance().getDepositRowMapper(), Table.DEPOSIT_TABLE, Column.DEPOSIT_ID);
    }

    @Override
    public int save(Deposit entity) throws DaoException {
        return 0;
    }

    @Override
    public int save(Deposit entity, User user) throws DaoException {
        Account currentAccount = new Account(0, AccountType.PASSIVE, user.getPassportId() + String.valueOf(Instant.now().toEpochMilli()), "Текущий счёт клиента", 3014, AccountPurpose.CURRENT, entity.getCurrency(), new BigDecimal(0), ClientType.INDIVIDUAL, user);
        Account percentAccount = new Account(0, AccountType.PASSIVE, user.getPassportId() + String.valueOf(Instant.now().toEpochMilli() + 1), "Процентный счёт клиента", 3014, AccountPurpose.PERCENT, entity.getCurrency(), new BigDecimal(0), ClientType.INDIVIDUAL, user);
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                SAVE_CURRENT_ACCOUNT,
                currentAccount.getType().getName(),
                currentAccount.getNumber(),
                currentAccount.getName(),
                currentAccount.getCode(),
                currentAccount.getPurpose().getName(),
                currentAccount.getCurrency().name(),
                currentAccount.getBalance(),
                currentAccount.getClientType().getName(),
                currentAccount.getClient().getUserId(),
                percentAccount.getType().getName(),
                percentAccount.getNumber(),
                percentAccount.getName(),
                percentAccount.getCode(),
                percentAccount.getPurpose().getName(),
                percentAccount.getCurrency().name(),
                percentAccount.getBalance(),
                percentAccount.getClientType().getName(),
                percentAccount.getClient().getUserId()
                )
        );
        parametrizedQueries.add(new ParametrizedQuery(
                SAVE_DEPOSIT_QUERY,
                entity.getType(),
                entity.getNumber(),
                entity.getCurrency().name(),
                entity.getStart(),
                entity.getEnd(),
                entity.getTerm(),
                entity.getAmount(),
                entity.getPercent()
                )
        );
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int update(Deposit entity) throws DaoException {
        return 0;
    }
}
