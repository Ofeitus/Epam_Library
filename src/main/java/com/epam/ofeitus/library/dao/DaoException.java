package com.epam.ofeitus.library.dao;

/**
 * Class to catch Dao exception.
 *
 * @author Ofeitus
 * @version 1.0
 */
public class DaoException extends Exception {
    /**
     * Instantiates a new Dao exception.
     */
    public DaoException() {
        super();
    }

    /**
     * Instantiates a new Dao exception with specific message
     *
     * @param message the message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Dao exception with specific message and cause
     *
     * @param message the message
     * @param cause   the cause
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Dao exception with specific cause
     *
     * @param cause the cause
     */
    public DaoException(Throwable cause) {
        super(cause);
    }
}