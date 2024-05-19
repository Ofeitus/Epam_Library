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
 * Command to create new subject.
 */
public class AddNewSubjectCommand implements Command {
    private final Logger logger = LogManager.getLogger(AddNewSubjectCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        SubjectService subjectService = ServiceFactory.getInstance().getSubjectService();

        try {
            String name = request.getParameter(RequestParameter.SUBJECT_NAME);
            int hours = Integer.parseInt(request.getParameter(RequestParameter.HOURS));

            if (!subjectService.saveSubject(name, hours)) {
                session.setAttribute(SessionAttribute.ERROR, "Invalid data");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String url = "/controller?" +
                    RequestParameter.COMMAND + "=" + CommandName.GOTO_SUBJECTS_PAGE_COMMAND;
            session.setAttribute(SessionAttribute.URL, url);

            return new CommandResult(url, RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to add new subject.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}