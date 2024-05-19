package com.epam.ofeitus.library.dao.rowmapper;

import com.epam.ofeitus.library.dao.rowmapper.impl.*;

/**
 * Factory, that provides row mappers.
 */
public class RowMapperFactory {
    private static final RowMapperFactory instance = new RowMapperFactory();
    private static final SubjectRowMapper subjectRowMapper = new SubjectRowMapper();
    private static final UserRowMapper userRowMapper = new UserRowMapper();

    private RowMapperFactory() {
    }

    /**
     * Gets instance.
     *
     * @return instance of RowMapperFactory
     */
    public static RowMapperFactory getInstance() {
        return instance;
    }

    public SubjectRowMapper getSubjectRowMapper() {
        return subjectRowMapper;
    }

    public UserRowMapper getUserRowMapper() {
        return userRowMapper;
    }
}
