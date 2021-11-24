package com.epam.ofeitus.library.connectionpool.impl;

import com.epam.ofeitus.library.connectionpool.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPoolImpl implements ConnectionPool {
    private final String url;
    private final String user;
    private final String password;
    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int MAX_TIMEOUT = 28800;

    public ConnectionPoolImpl(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    public static ConnectionPoolImpl create(String url, String user, String password) throws SQLException {
        List<Connection> connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(createConnection(url, user, password));
        }
        return new ConnectionPoolImpl(url, user, password, connectionPool);
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection(url, user, password));
            } else {
                throw new RuntimeException("Maximum pool size reached, no available connections.");
            }
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);

        if(!connection.isValid(MAX_TIMEOUT)) {
            connection = createConnection(url, user, password);
        }

        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
