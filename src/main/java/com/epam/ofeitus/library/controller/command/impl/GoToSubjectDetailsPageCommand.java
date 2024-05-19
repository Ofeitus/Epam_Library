package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.Subject;
import com.epam.ofeitus.library.service.SubjectService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command to go to book details page.
 */
public class GoToSubjectDetailsPageCommand implements Command {
    private final Logger logger = LogManager.getLogger(GoToSubjectDetailsPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SubjectService subjectService = serviceFactory.getBookService();

        int subjectId = Integer.parseInt(request.getParameter(RequestParameter.SUBJECT_ID));

        session.setAttribute(SessionAttribute.URL, "/controller?" +
                RequestParameter.COMMAND + "=" + CommandName.GOTO_SUBJECT_DETAILS_PAGE_COMMAND +
                "&" + RequestParameter.SUBJECT_ID + "=" + subjectId);

        try {
            Subject subject = subjectService.getSubjectById(subjectId);

            request.setAttribute(RequestAttribute.SUBJECT, subject);

            return new CommandResult(Page.SUBJECT_DETAILS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException | ClassCastException e) {
            logger.error("Unable to get subject.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
