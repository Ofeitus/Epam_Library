package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class LogInCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter((RequestParameter.PASSWORD));

        if (email == null || password == null) {
            request.getSession().setAttribute(SessionAttribute.ERROR, "invalid_login_data");
            return new CommandResult(Page.ERROR_PAGE, RoutingType.REDIRECT);
        }

        UserService userService = ServiceFactory.getInstance().getUserService();
        User user;
        try {
            user = userService.login(email, password);
        } catch (ServiceException e) {
            // TODO logger.error("Unable to test user sign in data. {}", e.getMessage());
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.REDIRECT);
        }
        if (user != null) {
            session.setAttribute(SessionAttribute.USER_ID, user.getUserId());
            session.setAttribute(SessionAttribute.USER_EMAIL, user.getEmail());
            session.setAttribute(SessionAttribute.USER_ROLE, user.getUserRole());
        } else {
            session.setAttribute(SessionAttribute.ERROR, "invalid_login_data");
            return new CommandResult(Page.SIGN_IN_PAGE, RoutingType.FORWARD);
        }

        return new CommandResult(Page.HOME_PAGE_REDIRECT, RoutingType.REDIRECT);
    }
}
