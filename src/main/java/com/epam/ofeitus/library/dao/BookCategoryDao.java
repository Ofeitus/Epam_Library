package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;

/**
 * Book category dao interface.
 */
public interface BookCategoryDao extends AbstractDao<BookCategory> {
    /**
     * Find book category by name.
     *
     * @param name book category name
     * @return book category
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    BookCategory findByName(String name) throws DaoException;
}
