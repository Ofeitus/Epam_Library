package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.dto.CopyOfBookDto;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class SearchUsersCommand implements Command {
    Logger logger = LogManager.getLogger(SearchUsersCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String email = request.getParameter(RequestParameter.EMAIL);
        int userId = 0;
        try {
            userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        } catch (NumberFormatException e) {
            logger.warn("Wrong search parameters.", e);
        }
        String userRole = request.getParameter(RequestParameter.USER_ROLE);
        int userRoleId;
        switch (userRole) {
            case "ADMIN":
                userRoleId = 1;
                break;
            case "MANAGER":
                userRoleId = 2;
                break;
            case "MEMBER":
                userRoleId = 3;
                break;
            default:
                userRoleId = 0;
        }

        UserService userService = ServiceFactory.getInstance().getUserService();

        int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));
        int itemsOnPage = 10;

        String command = "?command=search-users" +
                "&user-role=" + userRoleId +
                "&user-id=" + userId +
                "&email=" + email;
        session.setAttribute(SessionAttribute.URL, "/controller" + command + "&page=" + page);
        session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);

        try {
            List<User> users = userService.getUsersBySearchRequest(userRoleId, userId, email, page, itemsOnPage);
            int itemsCount = userService.countUsersBySearchRequest(userRoleId, userId, email);
            int pagesCount = itemsCount / itemsOnPage;
            if (itemsCount % itemsOnPage != 0) {
                pagesCount++;
            }
            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.PAGES_COUNT, pagesCount);
            request.setAttribute(RequestAttribute.USERS, users);
            return new CommandResult(Page.MANAGE_USERS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get users by search request.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
