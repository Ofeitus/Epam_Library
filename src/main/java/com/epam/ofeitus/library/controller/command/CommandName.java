package com.epam.ofeitus.library.controller.command;

/**
 * Class with constants corresponding to command names
 */
public class CommandName {
    public static final String CANCEL_RESERVATION_COMMAND = "cancel-reservation";
    public static final String CONFIRM_RESERVATION_COMMAND = "confirm-reservation";
    public static final String DELETE_BOOK_COMMAND = "delete-book";
    public static final String DELETE_COPY_OF_BOOK_COMMAND = "delete-copy-of-book";
    public static final String DELETE_USER_COMMAND = "delete-user";
    public static final String EDIT_BOOK_DATA_COMMAND = "edit-book-data";
    public static final String EDIT_PERSONAL_DATA_COMMAND = "edit-personal-data";
    public static final String GET_PERIOD_REPORTS_COMMAND = "get-period-reports";
    public static final String GOTO_403_PAGE_COMMAND = "goto-403-page";
    public static final String GOTO_404_PAGE_COMMAND = "goto-404-page";
    public static final String GOTO_500_PAGE_COMMAND = "goto-500-page";
    public static final String GOTO_ADD_NEW_BOOK_PAGE_COMMAND = "goto-add-new-subject-page";
    public static final String GOTO_SUBJECT_DETAILS_PAGE_COMMAND = "goto-subject-details-page";
    public static final String GOTO_SUBJECTS_PAGE_COMMAND = "goto-subjects-page";
    public static final String GOTO_EDIT_SUBJECT_PAGE_COMMAND = "goto-edit-subject-page";
    public static final String GOTO_LOG_IN_PAGE_COMMAND = "goto-log-in-page";
    public static final String GOTO_SIGN_UP_PAGE_COMMAND = "goto-sign-up-page";
    public static final String ISSUE_BY_INVENTORY_ID_COMMAND = "issue-by-inventory-id";
    public static final String ISSUE_BY_USER_ID_COMMAND = "issue-by-user-id-book";
    public static final String ISSUE_RESERVED_BOOK_COMMAND = "issue-reserved-book";
    public static final String LOG_IN_COMMAND = "log-in";
    public static final String LOG_OUT_COMMAND = "log-out";
    public static final String PAY_FINE_COMMAND = "pay-fine";
    public static final String RESERVE_BOOK_COMMAND = "reserve-book";
    public static final String RESTORE_USER_COMMAND = "restore-user";
    public static final String RETURN_LOANED_BOOK_COMMAND = "return-loaned-book";
    public static final String SEARCH_BOOKS_COMMAND = "search-books";
    public static final String SEARCH_COPIES_OF_BOOKS_COMMAND = "search-copies-of-books";
    public static final String SEARCH_MEMBERS_COMMAND = "search-members";
    public static final String SEARCH_USERS_COMMAND = "search-users";
    public static final String SET_LOCALE_COMMAND = "set-locale";
    public static final String SET_ROLE_COMMAND = "set-role";
    public static final String SIGN_UP_COMMAND = "sign-up";

    private CommandName() {
    }
}
