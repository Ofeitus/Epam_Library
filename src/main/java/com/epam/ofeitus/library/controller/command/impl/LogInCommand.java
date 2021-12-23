package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogInCommand implements Command {
    Logger logger = LogManager.getLogger(LogInCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter((RequestParameter.PASSWORD));

        UserService userService = ServiceFactory.getInstance().getUserService();
        User user;
        try {
            user = userService.login(email, password);
        } catch (ServiceException e) {
            logger.error("Unable to check user log-in data.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
        if (user != null) {
            if (user.getUserRole() == UserRole.ADMIN) {
                logger.info("User " + user.getEmail() + " logged in as admin.");
            }
            session.removeAttribute(SessionAttribute.ERROR);
            session.setAttribute(SessionAttribute.USER_ID, user.getUserId());
            session.setAttribute(SessionAttribute.USER_NAME, user.getName());
            session.setAttribute(SessionAttribute.USER_SURNAME, user.getSurname());
            session.setAttribute(SessionAttribute.USER_EMAIL, user.getEmail());
            session.setAttribute(SessionAttribute.USER_ROLE, user.getUserRole());
        } else {
            session.setAttribute(SessionAttribute.ERROR, "Invalid login or password");
            session.setAttribute(SessionAttribute.URL, "/controller?command=goto-log-in-page");
            return new CommandResult(Page.LOG_IN_PAGE, RoutingType.FORWARD);
        }

        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-home-page");
        return new CommandResult(Page.HOME_PAGE, RoutingType.FORWARD);
    }
}
