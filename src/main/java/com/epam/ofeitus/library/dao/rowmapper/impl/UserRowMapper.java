package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt(Column.USER_ID));
        user.setRegistrationDate(resultSet.getDate(Column.USER_REGISTRATION_DATE));
        user.setName(resultSet.getString(Column.USER_NAME));
        user.setSurname(resultSet.getString(Column.USER_SURNAME));
        user.setPatronymic(resultSet.getString(Column.USER_PATRONYMIC));
        user.setDateOfBirth(resultSet.getDate(Column.USER_DATE_OF_BIRTH));
        user.setGender(resultSet.getBoolean(Column.USER_GENDER));
        user.setPassportSeries(resultSet.getString(Column.USER_PASSWORD_SERIES));
        user.setPassportNumber(resultSet.getString(Column.USER_PASSWORD_NUMBER));
        user.setIssuedBy(resultSet.getString(Column.USER_ISSUED_BY));
        user.setDateOfIssuing(resultSet.getDate(Column.USER_DATE_OF_ISSUING));
        user.setPassportId(resultSet.getString(Column.USER_PASSWORD_ID));
        user.setPlaceOfBirth(resultSet.getString(Column.USER_PLACE_OF_BIRTH));
        user.setCityOfLiving(resultSet.getInt(Column.USER_CITY_OF_LIVING));
        user.setAddress(resultSet.getString(Column.USER_ADDRESS));
        user.setPhoneHome(resultSet.getString(Column.USER_PHONE_HOME));
        user.setPhoneMobile(resultSet.getString(Column.USER_PHONE_MOBILE));
        user.setPlaceOfWork(resultSet.getString(Column.USER_PLACE_OF_WORK));
        user.setJobTitle(resultSet.getString(Column.USER_JOB_TITLE));
        user.setCityOfRegistration(resultSet.getInt(Column.USER_CITY_OF_REGISTRATION));
        user.setAddressOfRegistration(resultSet.getString(Column.USER_ADDRESS_OF_REGISTRATION));
        user.setFamilyStatus(resultSet.getInt(Column.USER_FAMILY_STATUS));
        user.setDisability(resultSet.getInt(Column.USER_DISABILITY));
        user.setPensioner(resultSet.getBoolean(Column.USER_PENSIONER));
        user.setSalary(resultSet.getBigDecimal(Column.USER_SALARY));
        user.setConscript(resultSet.getBoolean(Column.USER_CONSCRIPT));
        user.setPhoneNumber(resultSet.getString(Column.USER_PHONE_NUMBER));
        user.setEmail(resultSet.getString(Column.USER_EMAIL));
        user.setPasswordHash(resultSet.getString(Column.USER_PASSWORD_HASH));
        user.setUserRole(UserRole.values()[resultSet.getInt(Column.USER_ROLE_ID) - 1]);
        user.setDeleted(resultSet.getBoolean(Column.USER_DELETED));
        return user;
    }
}
