package com.epam.ofeitus.library.controller;

import com.epam.ofeitus.library.dao.connectionpool.ConnectionPool;
import com.epam.ofeitus.library.dao.connectionpool.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TODO log application is starting
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException("Unable to initialize connection pool.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO log application is shutting down
        ConnectionPool.getInstance().dispose();
    }
}
