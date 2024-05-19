package com.epam.ofeitus.library.constant;

/**
 * Class with constants corresponding to database column names
 */
public final class Column {
    public static final String SUBJECT_ID = "id";
    public static final String SUBJECT_NAME = "name";
    public static final String SUBJECT_HOURS = "hours";

    public static final String USER_ID = "user_id";
    public static final String USER_REGISTRATION_DATE = "registration_date";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD_HASH = "password_hash";
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_DELETED = "deleted";

    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "role_name";

    private Column() {
    }
}
