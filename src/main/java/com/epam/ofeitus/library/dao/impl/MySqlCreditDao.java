package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.CreditDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.ParametrizedQuery;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.bank.*;
import com.epam.ofeitus.library.entity.user.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MySqlCreditDao extends AbstractMySqlDao<Credit> implements CreditDao {
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
    private static final String SAVE_CREDIT_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, LAST_INSERT_ID(), LAST_INSERT_ID() + 1)",
            Table.CREDIT_TABLE,
            Column.CREDIT_ID,
            Column.CREDIT_TYPE,
            Column.CREDIT_NUMBER,
            Column.CREDIT_CURRENCY,
            Column.CREDIT_START,
            Column.CREDIT_END,
            Column.CREDIT_TERM,
            Column.CREDIT_AMOUNT,
            Column.CREDIT_PERCENT,
            Column.CREDIT_CURRENT_ACCOUNT,
            Column.CREDIT_PERCENT_ACCOUNT
    );

    public MySqlCreditDao() {
        super(RowMapperFactory.getInstance().getCreditRowMapper(), Table.CREDIT_TABLE, Column.CREDIT_ID);
    }

    @Override
    public int save(Credit entity) throws DaoException {
        return 0;
    }

    @Override
    public int save(Credit entity, User user) throws DaoException {
        Account currentAccount = new Account(0, AccountType.ACTIVE, user.getPassportId() + String.valueOf(Instant.now().toEpochMilli()), "Текущий счёт клиента", 3014, AccountPurpose.CREDIT, entity.getCurrency(), new BigDecimal(0), ClientType.INDIVIDUAL, user);
        Account percentAccount = new Account(0, AccountType.ACTIVE, user.getPassportId() + String.valueOf(Instant.now().toEpochMilli() + 1), "Процентный счёт клиента", 3014, AccountPurpose.CREDIT, entity.getCurrency(), new BigDecimal(0), ClientType.INDIVIDUAL, user);
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
                        SAVE_CREDIT_QUERY,
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
    public int update(Credit entity) throws DaoException {
        return 0;
    }
}
