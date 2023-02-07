package com.epam.ofeitus.library.constant;

/**
 * Class with constants corresponding to database column names
 */
public final class Column {
    public static final String BOOK_ISBN = "book_isbn";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_PUBLICATION_YEAR = "publication_year";
    public static final String BOOK_CATEGORY_ID = "category_id";
    public static final String BOOK_LANGUAGE = "language";
    public static final String BOOK_KEY_WORDS = "key_words";

    public static final String COPY_OF_BOOK_INVENTORY_ID = "inventory_id";
    public static final String COPY_OF_BOOK_RECEIPT_DATE = "receipt_date";
    public static final String COPY_OF_BOOK_PRICE = "price";
    public static final String COPY_OF_BOOK_ISBN = "book_isbn";
    public static final String COPY_OF_BOOK_STATUS_ID = "copy_of_book_status_id";

    public static final String AUTHOR_ID = "author_id";
    public static final String AUTHOR_NAME = "name";
    public static final String AUTHOR_SURNAME = "surname";

    public static final String BOOK_HAS_AUTHOR_BOOK_ISBN = "book_isbn";
    public static final String BOOK_HAS_AUTHOR_AUTHOR_ID = "author_id";

    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";

    public static final String LOAN_ID = "loan_id";
    public static final String LOAN_ISSUE_DATE = "issue_date";
    public static final String LOAN_DUE_DATE = "due_date";
    public static final String LOAN_RETURN_DATE = "return_date";
    public static final String LOAN_FINE_AMOUNT = "fine_amount";
    public static final String LOAN_USER_ID = "user_id";
    public static final String LOAN_INVENTORY_ID = "inventory_id";
    public static final String LOAN_STATUS_ID = "loan_status_id";
    public static final String LOAN_STATUS_VALUE = "loan_status_value";

    public static final String RESERVATION_ID = "reservation_id";
    public static final String RESERVATION_DATE = "date";
    public static final String RESERVATION_USER_ID = "user_id";
    public static final String RESERVATION_INVENTORY_ID = "inventory_id";
    public static final String RESERVATION_STATUS_ID = "reservation_status_id";
    public static final String RESERVATION_STATUS_VALUE = "reservation_status_value";

    public static final String USER_ID = "user_id";
    public static final String USER_REGISTRATION_DATE = "registration_date";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_PATRONYMIC = "patronymic";
    public static final String USER_DATE_OF_BIRTH = "date_of_birth";
    public static final String USER_GENDER = "gender";
    public static final String USER_PASSWORD_SERIES = "password_series";
    public static final String USER_PASSWORD_NUMBER = "password_number";
    public static final String USER_ISSUED_BY = "issued_by";
    public static final String USER_DATE_OF_ISSUING = "date_of_issuing";
    public static final String USER_PASSWORD_ID = "password_id";
    public static final String USER_PLACE_OF_BIRTH = "place_of_birth";
    public static final String USER_CITY_OF_LIVING = "city_of_living";
    public static final String USER_ADDRESS = "address";
    public static final String USER_PHONE_HOME = "phone_home";
    public static final String USER_PHONE_MOBILE = "phone_mobile";
    public static final String USER_PLACE_OF_WORK = "place_of_work";
    public static final String USER_JOB_TITLE = "job_title";
    public static final String USER_CITY_OF_REGISTRATION = "city_of_registration";
    public static final String USER_ADDRESS_OF_REGISTRATION = "address_of_registration";
    public static final String USER_FAMILY_STATUS = "family_status";
    public static final String USER_DISABILITY = "disability";
    public static final String USER_PENSIONER = "pensioner";
    public static final String USER_SALARY = "salary";
    public static final String USER_CONSCRIPT = "conscript";
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
