package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUpCommand implements Command {
    Logger logger = LogManager.getLogger(SignUpCommand.class);

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
            request.setAttribute(RequestAttribute.EMAIL, email);
            logger.info("User" + email + "is registered.");
        } catch (ServiceException e) {
            logger.error("Unable to register new user.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
        return new CommandResult(Page.SIGN_IN_PAGE, RoutingType.FORWARD);
    }
}
