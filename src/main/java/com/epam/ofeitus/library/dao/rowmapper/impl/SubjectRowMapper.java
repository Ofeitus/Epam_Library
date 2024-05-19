package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectRowMapper implements RowMapper<Subject> {
    @Override
    public Subject map(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getInt(Column.SUBJECT_ID));
        subject.setName(resultSet.getString(Column.SUBJECT_NAME));
        subject.setHours(resultSet.getInt(Column.SUBJECT_HOURS));
        return subject;
    }
}
