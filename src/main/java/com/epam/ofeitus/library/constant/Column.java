package com.epam.ofeitus.library.constant;

public final class Column {
    public static final String BOOK_INVENTORY_ID = "inventory_id";
    public static final String BOOK_ISBN = "isbn";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_PUBLICATION_YEAR = "publication_year";
    public static final String BOOK_CATEGORY_ID = "category_id";
    public static final String BOOK_LANGUAGE = "language";

    public static final String AUTHOR_ID = "author_id";
    public static final String AUTHOR_NAME = "name";
    public static final String AUTHOR_SURNAME = "surname";

    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "name";

    public static final String LOAN_ID = "loan_id";
    public static final String LOAN_ISSUE_DATE = "issue_date";
    public static final String LOAN_DUE_DATE = "due_date";
    public static final String LOAN_RETURN_DATE = "return_date";
    public static final String LOAN_FINE_AMOUNT = "fine_amount";
    public static final String LOAN_USER_ID = "user_id";

    public static final String RESERVATION_ID = "reservation_id";
    public static final String RESERVATION_USER_ID = "reservation_user_id";
    public static final String RESERVATION_BOOK_ISBN = "isbn";
    public static final String RESERVATION_DATE = "date";
    public static final String RESERVATION_STATUS_ID = "reservation_status_id";
    public static final String RESERVATION_STATUS_VALUE = "value";

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD_HASH = "password_hash";
    public static final String USER_ROLE_ID = "user_role_id";

    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "name";

    public static final String FINE_PAYMENT_ID = "payment_id";
    public static final String FINE_USER_ID = "user_id";
    public static final String FINE_DATE = "date";
    public static final String FINE_AMOUNT = "amount";


    private Column() {
    }
}
