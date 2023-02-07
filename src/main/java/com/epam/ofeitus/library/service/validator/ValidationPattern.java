package com.epam.ofeitus.library.service.validator;

public class ValidationPattern {
    public static final String PASSPORT_NUMBER = "[\\d]{7}$";
    public static final String PASSPORT_ID = "[A-Z0-9]{15}$";
    public static final String NAME_PATTERN = "^([А-Я][а-яё]{1,29}|[A-Z][a-z]{1,29})$";
    public static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,30}$";
    public static final String MOBILE_PHONE_PATTERN = "(?:\\+375|80)(?:\\d{9})";
    public static final String HOME_PHONE_PATTERN = "[\\d]{9}$";
    public static final String ISBN_PATTERN = "^([\\d]{10}|[\\d]{13})$";

    private ValidationPattern() {
    }
}
