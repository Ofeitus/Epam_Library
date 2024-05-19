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
import java.util.List;

/**
 * Command to go to catalog page.
 */
public class GoToSubjectsPageCommand implements Command {
    private final Logger logger = LogManager.getLogger(GoToSubjectsPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        SubjectService subjectService = ServiceFactory.getInstance().getBookService();

        session.setAttribute(SessionAttribute.URL, "/controller?" +
                RequestParameter.COMMAND + "=" + CommandName.GOTO_SUBJECTS_PAGE_COMMAND);

        try {
            List<Subject> subjects = subjectService.getAllSubjects();

            request.setAttribute(RequestAttribute.SUBJECTS, subjects);

            return new CommandResult(Page.SUBJECTS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get book categories.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
