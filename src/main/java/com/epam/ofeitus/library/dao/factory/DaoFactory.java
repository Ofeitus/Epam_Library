package com.epam.ofeitus.library.dao.factory;

import com.epam.ofeitus.library.dao.*;

/**
 * Factory, that provides dao's.
 */
public interface DaoFactory {
    SubjectDao getBookDao();

    UserDao getUserDao();
}
