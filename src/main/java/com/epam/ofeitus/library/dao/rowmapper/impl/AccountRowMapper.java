package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.bank.Account;
import com.epam.ofeitus.library.entity.bank.AccountPurpose;
import com.epam.ofeitus.library.entity.bank.AccountType;
import com.epam.ofeitus.library.entity.bank.ClientType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account map(ResultSet resultSet) throws SQLException {
        UserRowMapper userRowMapper = RowMapperFactory.getInstance().getUserRowMapper();
        Account account = new Account();
        account.setBalance(resultSet.getBigDecimal(Column.ACCOUNT_BALANCE));
        account.setName(resultSet.getString(Column.ACCOUNT_NAME));
        account.setId(resultSet.getInt(Column.ACCOUNT_ID));
        account.setCode(resultSet.getInt(Column.ACCOUNT_CODE));
        account.setNumber(resultSet.getString(Column.ACCOUNT_NUMBER));
        account.setType(AccountType.fromString(resultSet.getString(Column.ACCOUNT_TYPE)));
        account.setPurpose(AccountPurpose.fromString(resultSet.getString(Column.ACCOUNT_PURPOSE)));
        account.setClientType(ClientType.fromString(resultSet.getString(Column.ACCOUNT_CLIENT_TYPE)));
        account.setClient(userRowMapper.map(resultSet));
        return account;
    }
}
