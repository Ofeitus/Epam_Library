package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUpCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.SECOND_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            if (userService.getByEmail(email) != null) {
                session.setAttribute(SessionAttribute.ERROR, "Email is already taken");
                return new CommandResult(Page.SIGN_UP_PAGE, RoutingType.FORWARD);
            }
            userService.register(firstName, lastName, email, password);
            // TODO logger.debug("IS REGISTERED {}", isRegistered);
        } catch (ServiceException e) {
            // TODO logger.error("Unable to register new user. {}", e.getMessage());
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
        return new CommandResult(Page.HOME_PAGE_REDIRECT, RoutingType.REDIRECT);
    }
}
