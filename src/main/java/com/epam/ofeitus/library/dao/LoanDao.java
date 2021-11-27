package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.entity.order.Loan;

import java.util.List;

public interface LoanDao extends AbstractDao<Loan> {
    List<Loan> findByUserId(int userId);

    List<Loan> findOverdueLoans();
}
