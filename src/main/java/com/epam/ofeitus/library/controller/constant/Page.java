package com.epam.ofeitus.library.controller.constant;

public class Page {
    // errors
    public static final String ERROR_404_PAGE = "WEB-INF/pages/errors/404.jsp";
    public static final String ERROR_500_PAGE = "WEB-INF/pages/errors/500.jsp";

    // common
    public static final String HOME_PAGE = "WEB-INF/pages/home.jsp";
    public static final String HOME_PAGE_REDIRECT = "/controller?command=goto-home-page";
    public static final String CATALOG_PAGE = "WEB-INF/pages/catalog.jsp";
    public static final String SIGN_IN_PAGE = "WEB-INF/pages/log-in.jsp";
    public static final String SIGN_IN_PAGE_REDIRECT = "/controller?command=goto-log-in-page";
    public static final String SIGN_UP_PAGE = "WEB-INF/pages/sign-up.jsp";

    // user
    public static final String USER_LOANS_PAGE = "WEB-INF/pages/user/user-loans.jsp";

    //admin
    public static final String MANAGE_USERS_PAGE = "WEB-INF/pages/admin/manage-users.jsp";
    public static final String BOOK_DETAILS_PAGE = "WEB-INF/pages/book-details.jsp";
}
