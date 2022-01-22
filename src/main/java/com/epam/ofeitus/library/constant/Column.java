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
