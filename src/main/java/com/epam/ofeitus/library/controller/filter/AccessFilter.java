package com.epam.ofeitus.library.controller.filter;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.factory.CommandFactory;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Filter, that controls access of users with different roles to commands of the system
 */
public class AccessFilter implements Filter {
    private final Map<UserRole, List<String>> commandsAvailableToRoles = new EnumMap<>(UserRole.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);

        commandsAvailableToRoles.put(UserRole.ADMIN, Arrays.asList(
                CommandName.CANCEL_RESERVATION_COMMAND,
                CommandName.CONFIRM_RESERVATION_COMMAND,
                CommandName.DELETE_BOOK_COMMAND,
                CommandName.DELETE_COPY_OF_BOOK_COMMAND,
                CommandName.DELETE_USER_COMMAND,
                CommandName.EDIT_BOOK_DATA_COMMAND,
                CommandName.EDIT_CLIENT_DATA_COMMAND,
                CommandName.EDIT_PERSONAL_DATA_COMMAND,
                CommandName.GET_PERIOD_REPORTS_COMMAND,
                CommandName.GOTO_403_PAGE_COMMAND,
                CommandName.GOTO_404_PAGE_COMMAND,
                CommandName.GOTO_500_PAGE_COMMAND,
                CommandName.GOTO_ADD_NEW_BOOK_PAGE_COMMAND,
                CommandName.GOTO_ADD_CLIENT_PAGE,
                CommandName.GOTO_BOOK_DETAILS_PAGE_COMMAND,
                CommandName.GOTO_CATALOG_PAGE_COMMAND,
                CommandName.GOTO_CONTACTS_PAGE_COMMAND,
                CommandName.GOTO_EDIT_BOOK_DATA_PAGE_COMMAND,
                CommandName.GOTO_EDIT_CLIENT_PAGE_COMMAND,
                CommandName.GOTO_EDIT_PERSONAL_DATA_PAGE_COMMAND,
                CommandName.GOTO_HOME_PAGE_COMMAND,
                CommandName.GOTO_INVENTORY_BOOK_PAGE_COMMAND,
                CommandName.GOTO_LOG_IN_PAGE_COMMAND,
                CommandName.GOTO_MANAGE_MEMBERS_PAGE_COMMAND,
                CommandName.GOTO_MANAGE_RESERVATIONS_PAGE_COMMAND,
                CommandName.GOTO_MANAGE_USERS_PAGE_COMMAND,
                CommandName.GOTO_PROFILE_PAGE_COMMAND,
                CommandName.GOTO_REPORTS_PAGE_COMMAND,
                CommandName.GOTO_SIGN_UP_PAGE_COMMAND,
                CommandName.GOTO_USER_FINES_PAGE_COMMAND,
                CommandName.GOTO_USER_LOANS_PAGE_COMMAND,
                CommandName.GOTO_USER_RESERVATIONS_PAGE_COMMAND,
                CommandName.ISSUE_BY_USER_ID_COMMAND,
                CommandName.ISSUE_BY_INVENTORY_ID_COMMAND,
                CommandName.ISSUE_RESERVED_BOOK_COMMAND,
                CommandName.LOG_IN_COMMAND,
                CommandName.LOG_OUT_COMMAND,
                CommandName.PAY_FINE_COMMAND,
                CommandName.RESERVE_BOOK_COMMAND,
                CommandName.RESTORE_USER_COMMAND,
                CommandName.RETURN_LOANED_BOOK_COMMAND,
                CommandName.SEARCH_BOOKS_COMMAND,
                CommandName.SEARCH_COPIES_OF_BOOKS_COMMAND,
                CommandName.SEARCH_MEMBERS_COMMAND,
                CommandName.SEARCH_USERS_COMMAND,
                CommandName.SET_LOCALE_COMMAND,
                CommandName.SET_ROLE_COMMAND,
                CommandName.SIGN_UP_COMMAND,
                CommandName.WRITE_IN_COPIES_OF_BOOK_COMMAND,
                CommandName.WRITE_IN_COPIES_OF_NEW_BOOK_COMMAND,
                CommandName.WRITE_OFF_COPIES_OF_BOOKS_COMMAND,
                CommandName.ADD_CLIENT_COMMAND,
                CommandName.GOTO_ACCOUNTS_PAGE_COMMAND,
                CommandName.GOTO_ADD_DEPOSIT_PAGE_COMMAND,
                CommandName.GOTO_ADD_CREDIT_PAGE_COMMAND,
                CommandName.ADD_DEPOSIT_COMMAND,
                CommandName.ADD_CREDIT_COMMAND
        ));
        commandsAvailableToRoles.put(UserRole.MANAGER, Arrays.asList(
                CommandName.CANCEL_RESERVATION_COMMAND,
                CommandName.CONFIRM_RESERVATION_COMMAND,
                CommandName.DELETE_BOOK_COMMAND,
                CommandName.DELETE_COPY_OF_BOOK_COMMAND,
                CommandName.EDIT_BOOK_DATA_COMMAND,
                CommandName.EDIT_PERSONAL_DATA_COMMAND,
                CommandName.GOTO_403_PAGE_COMMAND,
                CommandName.GOTO_404_PAGE_COMMAND,
                CommandName.GOTO_500_PAGE_COMMAND,
                CommandName.GOTO_ADD_NEW_BOOK_PAGE_COMMAND,
                CommandName.GOTO_BOOK_DETAILS_PAGE_COMMAND,
                CommandName.GOTO_CATALOG_PAGE_COMMAND,
                CommandName.GOTO_CONTACTS_PAGE_COMMAND,
                CommandName.GOTO_EDIT_BOOK_DATA_PAGE_COMMAND,
                CommandName.GOTO_EDIT_PERSONAL_DATA_PAGE_COMMAND,
                CommandName.GOTO_HOME_PAGE_COMMAND,
                CommandName.GOTO_INVENTORY_BOOK_PAGE_COMMAND,
                CommandName.GOTO_LOG_IN_PAGE_COMMAND,
                CommandName.GOTO_MANAGE_MEMBERS_PAGE_COMMAND,
                CommandName.GOTO_MANAGE_RESERVATIONS_PAGE_COMMAND,
                CommandName.GOTO_PROFILE_PAGE_COMMAND,
                CommandName.GOTO_SIGN_UP_PAGE_COMMAND,
                CommandName.GOTO_USER_FINES_PAGE_COMMAND,
                CommandName.GOTO_USER_LOANS_PAGE_COMMAND,
                CommandName.GOTO_USER_RESERVATIONS_PAGE_COMMAND,
                CommandName.ISSUE_BY_USER_ID_COMMAND,
                CommandName.ISSUE_BY_INVENTORY_ID_COMMAND,
                CommandName.ISSUE_RESERVED_BOOK_COMMAND,
                CommandName.LOG_IN_COMMAND,
                CommandName.LOG_OUT_COMMAND,
                CommandName.PAY_FINE_COMMAND,
                CommandName.RESERVE_BOOK_COMMAND,
                CommandName.RETURN_LOANED_BOOK_COMMAND,
                CommandName.SEARCH_BOOKS_COMMAND,
                CommandName.SEARCH_COPIES_OF_BOOKS_COMMAND,
                CommandName.SEARCH_MEMBERS_COMMAND,
                CommandName.SET_LOCALE_COMMAND,
                CommandName.SIGN_UP_COMMAND,
                CommandName.WRITE_IN_COPIES_OF_BOOK_COMMAND,
                CommandName.WRITE_IN_COPIES_OF_NEW_BOOK_COMMAND,
                CommandName.WRITE_OFF_COPIES_OF_BOOKS_COMMAND
        ));
        commandsAvailableToRoles.put(UserRole.MEMBER, Arrays.asList(
                CommandName.CANCEL_RESERVATION_COMMAND,
                CommandName.EDIT_PERSONAL_DATA_COMMAND,
                CommandName.GOTO_403_PAGE_COMMAND,
                CommandName.GOTO_404_PAGE_COMMAND,
                CommandName.GOTO_500_PAGE_COMMAND,
                CommandName.GOTO_BOOK_DETAILS_PAGE_COMMAND,
                CommandName.GOTO_CATALOG_PAGE_COMMAND,
                CommandName.GOTO_CONTACTS_PAGE_COMMAND,
                CommandName.GOTO_EDIT_PERSONAL_DATA_PAGE_COMMAND,
                CommandName.GOTO_HOME_PAGE_COMMAND,
                CommandName.GOTO_LOG_IN_PAGE_COMMAND,
                CommandName.GOTO_PROFILE_PAGE_COMMAND,
                CommandName.GOTO_SIGN_UP_PAGE_COMMAND,
                CommandName.GOTO_USER_FINES_PAGE_COMMAND,
                CommandName.GOTO_USER_LOANS_PAGE_COMMAND,
                CommandName.GOTO_USER_RESERVATIONS_PAGE_COMMAND,
                CommandName.LOG_IN_COMMAND,
                CommandName.LOG_OUT_COMMAND,
                CommandName.RESERVE_BOOK_COMMAND,
                CommandName.SEARCH_BOOKS_COMMAND,
                CommandName.SET_LOCALE_COMMAND,
                CommandName.SIGN_UP_COMMAND,
                CommandName.GOTO_ATM_PAGE_COMMAND
        ));
        commandsAvailableToRoles.put(UserRole.GUEST, Arrays.asList(
                CommandName.GOTO_403_PAGE_COMMAND,
                CommandName.GOTO_404_PAGE_COMMAND,
                CommandName.GOTO_500_PAGE_COMMAND,
                CommandName.GOTO_BOOK_DETAILS_PAGE_COMMAND,
                CommandName.GOTO_CATALOG_PAGE_COMMAND,
                CommandName.GOTO_CONTACTS_PAGE_COMMAND,
                CommandName.GOTO_HOME_PAGE_COMMAND,
                CommandName.GOTO_LOG_IN_PAGE_COMMAND,
                CommandName.GOTO_SIGN_UP_PAGE_COMMAND,
                CommandName.LOG_IN_COMMAND,
                CommandName.SEARCH_BOOKS_COMMAND,
                CommandName.SET_LOCALE_COMMAND,
                CommandName.SIGN_UP_COMMAND
        ));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        UserRole role = (UserRole) session.getAttribute(SessionAttribute.USER_ROLE);

        if (role == null) {
            role = UserRole.GUEST;
        }

        String commandName = request.getParameter("command");

        if (commandName != null) {
            Command command = CommandFactory.getInstance().getCommand(commandName);

            if (command == null) {
                String url = "/controller?" +
                        RequestParameter.COMMAND + "=" + CommandName.GOTO_404_PAGE_COMMAND;
                session.setAttribute(SessionAttribute.URL, url);
                response.sendRedirect(request.getContextPath() + url);
                return;
            }

            if (!commandsAvailableToRoles.get(role).contains(commandName)) {
                String url = "/controller?" +
                        RequestParameter.COMMAND + "=" + CommandName.GOTO_403_PAGE_COMMAND;
                session.setAttribute(SessionAttribute.URL, url);
                response.sendRedirect(request.getContextPath() + url);
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
