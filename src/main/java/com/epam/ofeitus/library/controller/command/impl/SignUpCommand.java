package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
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
    private final Logger logger = LogManager.getLogger(SignUpCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();

        String firstName = request.getParameter(RequestParameter.USER_NAME);
        String lastName = request.getParameter(RequestParameter.USER_SURNAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);

        try {
            if (userService.getByEmail(email) != null) {
                session.setAttribute(SessionAttribute.ERROR, "Email is already taken");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }
            if (!userService.register(firstName, lastName, email, password)) {
                session.setAttribute(SessionAttribute.ERROR, "Invalid data");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            request.setAttribute(RequestAttribute.EMAIL, email);

            session.setAttribute(SessionAttribute.URL, "/controller?" +
                    RequestParameter.COMMAND + "=" + CommandName.GOTO_LOG_IN_PAGE_COMMAND);

            logger.info("User {} is registered.", email);
            return new CommandResult(Page.LOG_IN_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to register new user.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
