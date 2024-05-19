package com.epam.ofeitus.library.controller.filter;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.factory.CommandFactory;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.UserRole;

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
                CommandName.ADD_NEW_SUBJECT_COMMAND,
                CommandName.DELETE_SUBJECT_COMMAND,
                CommandName.DELETE_USER_COMMAND,
                CommandName.EDIT_SUBJECT_COMMAND,
                CommandName.GOTO_403_PAGE_COMMAND,
                CommandName.GOTO_404_PAGE_COMMAND,
                CommandName.GOTO_500_PAGE_COMMAND,
                CommandName.GOTO_ADD_NEW_SUBJECT_PAGE_COMMAND,
                CommandName.GOTO_SUBJECT_DETAILS_PAGE_COMMAND,
                CommandName.GOTO_SUBJECTS_PAGE_COMMAND,
                CommandName.GOTO_EDIT_SUBJECT_PAGE_COMMAND,
                CommandName.GOTO_LOG_IN_PAGE_COMMAND,
                CommandName.GOTO_SIGN_UP_PAGE_COMMAND,
                CommandName.LOG_IN_COMMAND,
                CommandName.LOG_OUT_COMMAND,
                CommandName.RESTORE_USER_COMMAND,
                CommandName.SET_LOCALE_COMMAND,
                CommandName.SET_ROLE_COMMAND,
                CommandName.SIGN_UP_COMMAND
        ));
        commandsAvailableToRoles.put(UserRole.MANAGER, Arrays.asList(
                CommandName.ADD_NEW_SUBJECT_COMMAND,
                CommandName.DELETE_SUBJECT_COMMAND,
                CommandName.DELETE_USER_COMMAND,
                CommandName.EDIT_SUBJECT_COMMAND,
                CommandName.GOTO_403_PAGE_COMMAND,
                CommandName.GOTO_404_PAGE_COMMAND,
                CommandName.GOTO_500_PAGE_COMMAND,
                CommandName.GOTO_ADD_NEW_SUBJECT_PAGE_COMMAND,
                CommandName.GOTO_SUBJECT_DETAILS_PAGE_COMMAND,
                CommandName.GOTO_SUBJECTS_PAGE_COMMAND,
                CommandName.GOTO_EDIT_SUBJECT_PAGE_COMMAND,
                CommandName.GOTO_LOG_IN_PAGE_COMMAND,
                CommandName.GOTO_SIGN_UP_PAGE_COMMAND,
                CommandName.LOG_IN_COMMAND,
                CommandName.LOG_OUT_COMMAND,
                CommandName.RESTORE_USER_COMMAND,
                CommandName.SET_LOCALE_COMMAND,
                CommandName.SET_ROLE_COMMAND,
                CommandName.SIGN_UP_COMMAND
        ));
        commandsAvailableToRoles.put(UserRole.MEMBER, Arrays.asList(
                CommandName.ADD_NEW_SUBJECT_COMMAND,
                CommandName.DELETE_SUBJECT_COMMAND,
                CommandName.DELETE_USER_COMMAND,
                CommandName.EDIT_SUBJECT_COMMAND,
                CommandName.GOTO_403_PAGE_COMMAND,
                CommandName.GOTO_404_PAGE_COMMAND,
                CommandName.GOTO_500_PAGE_COMMAND,
                CommandName.GOTO_ADD_NEW_SUBJECT_PAGE_COMMAND,
                CommandName.GOTO_SUBJECT_DETAILS_PAGE_COMMAND,
                CommandName.GOTO_SUBJECTS_PAGE_COMMAND,
                CommandName.GOTO_EDIT_SUBJECT_PAGE_COMMAND,
                CommandName.GOTO_LOG_IN_PAGE_COMMAND,
                CommandName.GOTO_SIGN_UP_PAGE_COMMAND,
                CommandName.LOG_IN_COMMAND,
                CommandName.LOG_OUT_COMMAND,
                CommandName.RESTORE_USER_COMMAND,
                CommandName.SET_LOCALE_COMMAND,
                CommandName.SET_ROLE_COMMAND,
                CommandName.SIGN_UP_COMMAND
        ));
        commandsAvailableToRoles.put(UserRole.GUEST, Arrays.asList(
                CommandName.GOTO_403_PAGE_COMMAND,
                CommandName.GOTO_404_PAGE_COMMAND,
                CommandName.GOTO_500_PAGE_COMMAND,
                CommandName.GOTO_LOG_IN_PAGE_COMMAND,
                CommandName.GOTO_SIGN_UP_PAGE_COMMAND,
                CommandName.LOG_IN_COMMAND,
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
