package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SearchMembersCommand implements Command {
    Logger logger = LogManager.getLogger(SearchMembersCommand.class);

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

        session.setAttribute(SessionAttribute.URL,
                "/controller?command=goto-manage-members-page" +
                        "&email=" + email +
                        "&user-id=" + userId);

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            // TODO Pagination
            List<User> users = userService.getMemberBySearchRequest(userId, email);
            request.setAttribute(RequestAttribute.USERS, users);
            return new CommandResult(Page.MANAGE_MEMBERS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get all members", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
