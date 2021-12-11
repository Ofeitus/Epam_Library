package com.epam.ofeitus.library;

import com.epam.ofeitus.library.dao.AuthorDao;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.dao.impl.MySqlAuthorDao;
import com.epam.ofeitus.library.entity.book.Author;

public class Main {
    public static void main(String[] args) {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        AuthorDao authorDao = daoFactory.getAuthorDao();

    }
}
