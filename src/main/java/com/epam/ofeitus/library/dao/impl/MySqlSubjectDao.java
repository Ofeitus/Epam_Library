package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.SubjectDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.ParametrizedQuery;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class MySqlSubjectDao extends AbstractMySqlDao<Subject> implements SubjectDao {
    public static final String SAVE_SUBJECT_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?)",
            Table.SUBJECT_TABLE,
            Column.SUBJECT_NAME,
            Column.SUBJECT_HOURS);
    public static final String UPDATE_SUBJECT_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=? WHERE %s=?",
            Table.SUBJECT_TABLE,
            Column.SUBJECT_NAME,
            Column.SUBJECT_HOURS,
            Column.SUBJECT_ID);

    public MySqlSubjectDao() {
        super(RowMapperFactory.getInstance().getSubjectRowMapper(), Table.SUBJECT_TABLE, Column.SUBJECT_ID);
    }

    @Override
    public int save(Subject entity) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                SAVE_SUBJECT_QUERY,
                entity.getName(),
                entity.getHours()
        ));
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int update(Subject entity) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                UPDATE_SUBJECT_QUERY,
                entity.getName(),
                entity.getHours(),
                entity.getId()
        ));
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int deleteById(int id) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                DELETE_BY_ID_QUERY,
                id
        ));
        return queryOperator.executeTransaction(parametrizedQueries);
    }
}
