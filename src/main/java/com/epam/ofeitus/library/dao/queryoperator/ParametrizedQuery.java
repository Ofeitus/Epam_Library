package com.epam.ofeitus.library.dao.queryoperator;

/**
 * Parametrized query container.
 */
public class ParametrizedQuery {
    private String query;
    private Object[] params;

    private ParametrizedQuery() {
    }

    public ParametrizedQuery(String query, Object... params) {
        this.query = query;
        this.params = params;
    }

    public String getQuery() {
        return query;
    }

    public Object[] getParams() {
        return params;
    }
}
