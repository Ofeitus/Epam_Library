package com.epam.ofeitus.library.dao.queryoperator;

import java.util.List;

public interface QueryOperator<T> {
    List<T> executeQuery(String query);

    T executeSingleEntityQuery(String query);

    int executeUpdate(String query);
}
