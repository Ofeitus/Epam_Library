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
 * Command to go to edit subject data page.
 */
public class GoToEditSubjectPageCommand implements Command {
    private final Logger logger = LogManager.getLogger(GoToEditSubjectPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        SubjectService subjectService = ServiceFactory.getInstance().getSubjectService();

        int subjectId = Integer.parseInt(request.getParameter(RequestParameter.SUBJECT_ID));

        session.setAttribute(SessionAttribute.URL, "/controller?" +
                RequestParameter.COMMAND + "=" + CommandName.GOTO_EDIT_SUBJECT_PAGE_COMMAND +
                "&" + RequestParameter.SUBJECT_ID + "=" + subjectId);

        try {
            Subject subject = subjectService.getSubjectById(subjectId);

            request.setAttribute(RequestAttribute.SUBJECT, subject);

            return new CommandResult(Page.EDIT_SUBJECT_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get subject.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
