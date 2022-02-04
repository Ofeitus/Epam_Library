package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.connectionpool.ConnectionPool;
import com.epam.ofeitus.library.dao.connectionpool.ConnectionPoolException;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.QueryOperator;
import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.book.Book;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hsqldb.cmdline.SqlFile;

import static org.mockito.Mockito.*;

class MySqlBookDaoTest {
    @Mock
    QueryOperator<Book> queryOperator;
    @InjectMocks
    MySqlBookDao mySqlBookDao;

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
    void testSave() throws DaoException {
        when(queryOperator.executeTransaction(any())).thenReturn(0);

        int result = mySqlBookDao.save(new Book("1111111111", "title", 2000, 1, "language", ""),
                Collections.singletonList(new Author(1, "Джоан", "Роулинг")));
        mySqlBookDao.deleteByIsbn("1111111111");
        Assertions.assertEquals(1, result);
    }

    @Test
    void testUpdate() throws DaoException {
        when(queryOperator.executeTransaction(any())).thenReturn(0);

        ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author(4, "Рэй", "Брэдбери"));
        int result = mySqlBookDao.update(new Book("5941170688", "Порри Гаттер и Каменный Философ", 2000, 1, "language", ""),
                authors);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testDeleteByIsbn() throws DaoException {
        when(queryOperator.executeTransaction(any())).thenReturn(0);

        mySqlBookDao.save(new Book("2222222222", "title", 2000, 1, "language", ""));
        int result = mySqlBookDao.deleteByIsbn("2222222222");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testFindByIsbn() throws DaoException {
        when(queryOperator.executeSingleEntityQuery(anyString(), anyVararg())).thenReturn(new Book());

        Book result = mySqlBookDao.findByIsbn("9785389074354");
        Assertions.assertEquals(new Book("9785389074354", "Гарри Поттер и философский камень", 2016, 1, "Русский", "Фентези, фантастика"), result);
    }

    @Test
    void testFindBySearchRequest() throws DaoException {
        when(queryOperator.executeQuery(anyString(), anyVararg())).thenReturn(Collections.singletonList(new Book()));

        List<Book> result = mySqlBookDao.findBySearchRequest("гарри", 1, 1, 2010, 2020, 0, 5);
        Assertions.assertEquals(new Book("9785389074354", "Гарри Поттер и философский камень", 2016, 1, "Русский", "Фентези, фантастика"), result.get(0));
    }

    @Test
    void testCountBySearchRequest() throws DaoException {
        when(queryOperator.executeCountQuery(anyString(), anyVararg())).thenReturn(0);

        int result = mySqlBookDao.countBySearchRequest("гарри", 1, 1, 2010, 2020);
        Assertions.assertEquals(7, result);
    }
}
