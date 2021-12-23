package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.dto.LoanDto;
import com.epam.ofeitus.library.entity.dto.ReservationDto;
import com.epam.ofeitus.library.service.LoansService;
import com.epam.ofeitus.library.service.ReservationsService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToUserProfilePageCommand implements Command {
    Logger logger = LogManager.getLogger(GoToUserProfilePageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-user-profile-page");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ReservationsService reservationsService = serviceFactory.getReservationsService();
        LoansService loansService = serviceFactory.getLoansService();

        try {
            int debtsCount = loansService.getDebtsCountByUserId((int)session.getAttribute(SessionAttribute.USER_ID));
            request.setAttribute(RequestAttribute.DEBTS_COUNT, debtsCount);
        } catch (ServiceException e) {
            logger.error("Unable to get user debts count.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }

        try {
            int unpaidFinesCount  = loansService.getUnpaidFinesCountByUserId((int)session.getAttribute(SessionAttribute.USER_ID));
            request.setAttribute(RequestAttribute.UNPAID_FINES_COUNT, unpaidFinesCount);
        } catch (ServiceException e) {
            logger.error("Unable to get user unpaid fines count.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }

        try {
            int readyReservationsCount = reservationsService.getReadyReservationsCount((int)session.getAttribute(SessionAttribute.USER_ID));
            request.setAttribute(RequestAttribute.READY_RESERVATIONS_COUNT, readyReservationsCount);
        } catch (ServiceException e) {
            logger.error("Unable to get user ready reservations count.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }

        return new CommandResult(Page.USER_PROFILE_PAGE, RoutingType.FORWARD);
    }
}
