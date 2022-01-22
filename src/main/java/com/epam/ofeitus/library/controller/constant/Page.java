package com.epam.ofeitus.library.controller.constant;


public class Page {
    // errors
    public static final String ERROR_403_PAGE = "WEB-INF/pages/errors/403.jsp";
    public static final String ERROR_404_PAGE = "WEB-INF/pages/errors/404.jsp";
    public static final String ERROR_500_PAGE = "WEB-INF/pages/errors/500.jsp";

    // common
    public static final String HOME_PAGE = "WEB-INF/pages/home.jsp";
    public static final String CATALOG_PAGE = "WEB-INF/pages/catalog.jsp";
    public static final String BOOK_DETAILS_PAGE = "WEB-INF/pages/book-details.jsp";
    public static final String CONTACTS_PAGE = "WEB-INF/pages/contacts.jsp";
    public static final String LOG_IN_PAGE = "WEB-INF/pages/log-in.jsp";
    public static final String SIGN_UP_PAGE = "WEB-INF/pages/sign-up.jsp";
    public static final String EDIT_PERSONAL_DATA_PAGE = "WEB-INF/pages/edit-personal-data.jsp";

    // member-manager
    public static final String PROFILE_PAGE = "WEB-INF/pages/profile.jsp";
    public static final String USER_LOANS_PAGE = "WEB-INF/pages/user/user-loans.jsp";
    public static final String USER_RESERVATIONS_PAGE = "WEB-INF/pages/user/user-reservations.jsp";
    public static final String USER_FINES_PAGE = "WEB-INF/pages/user/user-fines.jsp";

    // manager
    public static final String EDIT_BOOK_DATA_PAGE = "WEB-INF/pages/manager/edit-book-data.jsp";
    public static final String INVENTORY_BOOK_PAGE = "WEB-INF/pages/manager/inventory-book.jsp";
    public static final String MANAGE_RESERVATIONS_PAGE = "WEB-INF/pages/manager/manage-reservations.jsp";
    public static final String MANAGE_MEMBERS_PAGE = "WEB-INF/pages/manager/manage-members.jsp";
    public static final String ADD_NEW_BOOK_DATA_PAGE = "WEB-INF/pages/manager/add-new-book.jsp";

    // admin
    public static final String MANAGE_USERS_PAGE = "WEB-INF/pages/admin/manage-users.jsp";
    public static final String REPORTS_PAGE = "WEB-INF/pages/admin/reports.jsp";

    private Page() {
    }
}
