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
import java.util.Optional;

public class IssueReservedBookCommand implements Command {
    private final Logger logger = LogManager.getLogger(IssueReservedBookCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoansService loansService = ServiceFactory.getInstance().getLoansService();

        ConfigResourceManager configResourceManager = ConfigResourceManager.getInstance();

        try {
            int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
            int reservationId = Integer.parseInt(request.getParameter(RequestParameter.RESERVATION_ID));
            int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));

            String command = "?command=goto-user-reservations-page&user-id=" + userId;
            session.setAttribute(SessionAttribute.URL, "/controller" + command + "&page=" + page);
            session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);

            int loanPeriod = 30;
            try {
                loanPeriod = Integer.parseInt(configResourceManager.getValue(ConfigParameter.LOAN_PERIOD));
            } catch (NumberFormatException | MissingResourceException e) {
                logger.error("Unable to get loan period.", e);
            }

            loansService.loanFromReservation(reservationId, loanPeriod);

            return new CommandResult("/controller" + command + "&page=" + page, RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to loan from reservation.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
