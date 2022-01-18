package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.report.UserCompositionReport;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GoToReportsPageCommand implements Command {
    Logger logger = LogManager.getLogger(GoToReportsPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");

        String from = request.getParameter(RequestParameter.FROM_DATE);
        String to = request.getParameter(RequestParameter.TO_DATE);

        session.setAttribute(SessionAttribute.URL,
                "/controller?command=goto-reports-page" +
                        "&from-date=" + from +
                        "&to-date=" + to);

        Date fromDate;
        Date toDate;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.YEAR, -1);
            fromDate = cal.getTime();
            toDate = new Date();

            UserCompositionReport userCompositionReport = userService.getUserCompositionReport(fromDate, toDate);

            request.setAttribute(RequestAttribute.USER_COMPOSITION_REPORT, userCompositionReport);
        } catch (ServiceException e) {
            logger.error("Unable to get user composition report.");
        }

        return new CommandResult(Page.REPORTS_PAGE, RoutingType.FORWARD);
    }
}
