package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
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
import java.util.Optional;

/**
 * Command to go to manage members page.
 */
public class GoToManageMembersPageCommand implements Command {
    private final Logger logger = LogManager.getLogger(GoToManageMembersPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));
            int itemsOnPage = 10;

            String command = "?" + RequestParameter.COMMAND + "=" + CommandName.GOTO_MANAGE_MEMBERS_PAGE_COMMAND;
            session.setAttribute(SessionAttribute.URL, "/controller" + command +
                    "&" + RequestParameter.PAGE + "=" + page);
            session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);

            List<User> users = userService.getAllMembers(page, itemsOnPage);

            int itemsCount = userService.countAllMembers();
            int pagesCount = itemsCount / itemsOnPage;
            if (itemsCount % itemsOnPage != 0) {
                pagesCount++;
            }

            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.PAGES_COUNT, pagesCount);
            request.setAttribute(RequestAttribute.USERS, users);

            return new CommandResult(Page.MANAGE_MEMBERS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to get all members", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
