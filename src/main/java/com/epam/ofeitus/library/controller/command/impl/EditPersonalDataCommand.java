package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
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

/**
 * Command to edit personal data.
 */
public class EditPersonalDataCommand implements Command {
    private final Logger logger = LogManager.getLogger(EditPersonalDataCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();

        String name = request.getParameter(RequestParameter.USER_NAME);
        String surname = request.getParameter(RequestParameter.USER_SURNAME);
        String phoneNumber = request.getParameter(RequestParameter.PHONE_NUMBER);

        try {
            int userId = (int) session.getAttribute(SessionAttribute.USER_ID);

            if (!userService.editPersonalData(userId, name, surname, phoneNumber)) {
                session.setAttribute(SessionAttribute.ERROR, "Invalid data");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            session.setAttribute(SessionAttribute.USER_NAME, name);
            session.setAttribute(SessionAttribute.USER_SURNAME, surname);
            session.setAttribute(SessionAttribute.USER_PHONE_NUMBER, phoneNumber);

            String url = "/controller?" +
                    RequestParameter.COMMAND + "=" + CommandName.GOTO_PROFILE_PAGE_COMMAND;
            session.setAttribute(SessionAttribute.URL, url);

            return new CommandResult(url, RoutingType.REDIRECT);
        } catch (ServiceException | ClassCastException e) {
            logger.error("Unable to edit personal data.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
