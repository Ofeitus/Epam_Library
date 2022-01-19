package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.order.constiuent.LoanStatus;
import com.epam.ofeitus.library.entity.order.constiuent.ReservationStatus;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;
import com.epam.ofeitus.library.service.LoansService;
import com.epam.ofeitus.library.service.ReservationsService;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GoToProfilePageCommand implements Command {
    private final Logger logger = LogManager.getLogger(GoToProfilePageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        ReservationsService reservationsService = serviceFactory.getReservationsService();
        LoansService loansService = serviceFactory.getLoansService();

        String requestUserId = request.getParameter(RequestParameter.USER_ID);

        int userId = (int) session.getAttribute(SessionAttribute.USER_ID);

        try {
            if (session.getAttribute(SessionAttribute.USER_ROLE) == UserRole.MANAGER && requestUserId != null) {
                userId = Integer.parseInt(requestUserId);
            }

            session.setAttribute(SessionAttribute.URL, "/controller?command=goto-profile-page&user-id=" + userId);

            User user = userService.getByUserId(userId);

            if (user.getUserRole() == UserRole.MEMBER || requestUserId != null) {
                int debtsCount = loansService.getDebtsCountByUserId(userId);
                int unpaidFinesCount = loansService.getLoansCountByUserIdAndStatusId(userId, LoanStatus.FINED.ordinal() + 1);
                int readyReservationsCount = reservationsService.getReservationsCountByUserIdAndStatusId(userId, ReservationStatus.READY_TO_ISSUE.ordinal() + 1);

                request.setAttribute(RequestAttribute.DEBTS_COUNT, debtsCount);
                request.setAttribute(RequestAttribute.UNPAID_FINES_COUNT, unpaidFinesCount);
                request.setAttribute(RequestAttribute.READY_RESERVATIONS_COUNT, readyReservationsCount);
            }

            request.setAttribute(RequestAttribute.USER, user);

            return new CommandResult(Page.PROFILE_PAGE, RoutingType.FORWARD);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to get user data.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
