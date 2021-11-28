package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.order.Loan;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanRowMapper implements RowMapper<Loan> {
    @Override
    public Loan map(ResultSet resultSet) throws SQLException {
        Loan loan = new Loan();
        loan.setLoanId(resultSet.getInt(Column.LOAN_ID));
        loan.setIssueDate(resultSet.getDate(Column.LOAN_ISSUE_DATE));
        loan.setDueDate(resultSet.getDate(Column.LOAN_DUE_DATE));
        loan.setReturnDate(resultSet.getDate(Column.LOAN_RETURN_DATE));
        loan.setFineAmount(resultSet.getBigDecimal(Column.LOAN_FINE_AMOUNT));
        loan.setUserId(resultSet.getInt(Column.LOAN_USER_ID));
        return loan;
    }
}
