package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.connectionpool.ConnectionPool;
import com.epam.ofeitus.library.dao.connectionpool.ConnectionPoolException;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.QueryOperator;
import com.epam.ofeitus.library.entity.order.Loan;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.entity.order.constiuent.LoanStatus;
import org.apache.commons.lang.time.DateUtils;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.mockito.Mockito.*;

class MySqlLoanDaoTest {
    @Mock
    QueryOperator<Loan> queryOperator;
    @InjectMocks
    MySqlLoanDao mySqlLoanDao;
    @InjectMocks
    MySqlReservationDao mySqlReservationDao;

    @BeforeAll
    static void createDB() throws ConnectionPoolException, SQLException, IOException, SqlToolError {
        ConnectionPool.getInstance().initPoolData();
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            SqlFile sqlFileCreate = new SqlFile(new File("./src/test/resources/sql/createTestDB.sql"));
            sqlFileCreate.setConnection(connection);
            sqlFileCreate.execute();
            SqlFile sqlFileInsert = new SqlFile(new File("./src/test/resources/sql/insertTestData.sql"));
            sqlFileInsert.setConnection(connection);
            sqlFileInsert.execute();
        }
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdate() throws DaoException {
        when(queryOperator.executeUpdate(anyString(), anyVararg())).thenReturn(0);

        Loan loan = mySqlLoanDao.findById(4);
        loan.setFineAmount(new BigDecimal("5"));
        int result = mySqlLoanDao.update(loan);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testFindByUserId() throws DaoException {
        when(queryOperator.executeQuery(anyString(), anyVararg())).thenReturn(Collections.singletonList(new Loan()));

        Loan loan = mySqlLoanDao.findById(1);
        List<Loan> result = mySqlLoanDao.findByUserId(3, 0, 10);
        Assertions.assertEquals(loan, result.get(0));
    }

    @Test
    void testCountByUserId() throws DaoException {
        when(queryOperator.executeCountQuery(anyString(), anyVararg())).thenReturn(0);

        int result = mySqlLoanDao.countByUserId(3);
        Assertions.assertEquals(7, result);
    }

    @Test
    void testFindByInventoryId() throws DaoException {
        when(queryOperator.executeQuery(anyString(), anyVararg())).thenReturn(Collections.singletonList(new Loan()));

        Loan loan = mySqlLoanDao.findById(1);
        List<Loan> result = mySqlLoanDao.findByInventoryId(6);
        Assertions.assertEquals(loan, result.get(0));
    }

    @Test
    void testFindByUserIdWithFine() throws DaoException {
        when(queryOperator.executeQuery(anyString(), anyVararg())).thenReturn(Collections.singletonList(new Loan()));

        Loan loan = mySqlLoanDao.findById(4);
        List<Loan> result = mySqlLoanDao.findByUserIdWithFine(3, 0, 10);
        Assertions.assertEquals(loan, result.get(0));
    }

    @Test
    void testCountByUserIdWithFine() throws DaoException {
        when(queryOperator.executeCountQuery(anyString(), anyVararg())).thenReturn(0);

        int result = mySqlLoanDao.countByUserIdWithFine(3);
        Assertions.assertEquals(3, result);
    }

    @Test
    void testCountDebtsByUserId() throws DaoException {
        when(queryOperator.executeCountQuery(anyString(), anyVararg())).thenReturn(0);

        int result = mySqlLoanDao.countDebtsByUserId(3);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testCountByUserIdAndStatusId() throws DaoException {
        when(queryOperator.executeCountQuery(anyString(), anyVararg())).thenReturn(0);

        int result = mySqlLoanDao.countByUserIdAndStatusId(3, 1);
        Assertions.assertEquals(3, result);
    }

    @Test
    void testCountAll() throws DaoException {
        when(queryOperator.executeCountQuery(anyString(), anyVararg())).thenReturn(0);

        int result = mySqlLoanDao.countAll(new Date());
        Assertions.assertEquals(10, result);
    }

    @Test
    void testLoanByUserId() throws DaoException {
        when(queryOperator.executeTransaction(any())).thenReturn(0);

        int result = mySqlLoanDao.loanByUserId(4, "9785389077881", 30);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testLoanByInventoryId() throws DaoException {
        when(queryOperator.executeTransaction(any())).thenReturn(0);

        int result = mySqlLoanDao.loanByInventoryId(5, 2, 30);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testLoanFromReservation() throws DaoException {
        when(queryOperator.executeTransaction(any())).thenReturn(0);

        Reservation reservation = mySqlReservationDao.findById(1);
        int result = mySqlLoanDao.loanFromReservation(reservation, 30);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testReturnNoFine() throws DaoException {
        when(queryOperator.executeTransaction(any())).thenReturn(0);

        Loan loan = mySqlLoanDao.findById(2);
        int result = mySqlLoanDao.returnNoFine(loan);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testReturnWithFine() throws DaoException {
        when(queryOperator.executeTransaction(any())).thenReturn(0);

        Loan loan = mySqlLoanDao.findById(3);
        int result = mySqlLoanDao.returnWithFine(loan, new BigDecimal("10"));
        Assertions.assertEquals(1, result);
    }

    @Test
    void testFindById() throws DaoException {
        when(queryOperator.executeSingleEntityQuery(anyString(), anyVararg())).thenReturn(new Loan());

        Loan result = mySqlLoanDao.findById(7);
        Loan expected = new Loan(7, new Date(1582146000000L), new Date(1582146000000L), new Date(1582146000000L), new BigDecimal("2.00"), 3, 5, LoanStatus.FINED);
        Assertions.assertEquals(expected, result);
    }
}
