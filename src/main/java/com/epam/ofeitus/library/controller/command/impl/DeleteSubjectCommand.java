package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.service.SubjectService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command to delete book.
 */
public class DeleteSubjectCommand implements Command {
    private final Logger logger = LogManager.getLogger(DeleteSubjectCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        SubjectService subjectService = ServiceFactory.getInstance().getBookService();

        int subjectId = Integer.parseInt(request.getParameter(RequestParameter.SUBJECT_ID));

        String url = "/controller?" +
                RequestParameter.COMMAND + "=" + CommandName.GOTO_SUBJECTS_PAGE_COMMAND;
        session.setAttribute(SessionAttribute.URL, url);

        try {
            subjectService.deleteSubject(subjectId);

            return new CommandResult(url, RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Unable to delete subject.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
