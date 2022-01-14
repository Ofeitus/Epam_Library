package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.constant.ConfigParameter;
import com.epam.ofeitus.library.constant.ConfigResourceManager;
import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.service.LoansService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.MissingResourceException;

public class IssueReservedBookCommand implements Command {
    Logger logger = LogManager.getLogger(IssueReservedBookCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        int reservationId = Integer.parseInt(request.getParameter(RequestParameter.RESERVATION_ID));

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-user-reservations-page&user-id=" + userId);

        LoansService loansService = ServiceFactory.getInstance().getLoansService();

        ConfigResourceManager configResourceManager = ConfigResourceManager.getInstance();
        try {
            int loanPeriod = 30;
            try {
                loanPeriod = Integer.parseInt(configResourceManager.getValue(ConfigParameter.LOAN_PERIOD));
            } catch (NumberFormatException | MissingResourceException e) {
                logger.error("Unable to get loan period.", e);
            }
            loansService.loanFromReservation(reservationId, loanPeriod);
            return new CommandResult("/controller?command=goto-user-reservations-page&user-id=" + userId, RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Unable to loan from reservation.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
