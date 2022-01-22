package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.constant.ConfigResourceManager;
import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
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

public class IssueByUserIdCommand implements Command {
    private final Logger logger = LogManager.getLogger(IssueByUserIdCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        LoansService loansService = serviceFactory.getLoansService();
        ReservationsService reservationsService = serviceFactory.getReservationsService();

        ConfigResourceManager configResourceManager = ConfigResourceManager.getInstance();

        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);

        try {
            int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));

            User user = userService.getByUserId(userId);
            // Invalid user id case
            if (user == null || user.getUserRole() != UserRole.MEMBER) {
                session.setAttribute(SessionAttribute.ERROR, "Member with id: " + userId + " does not exist");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            int maxMemberBooks = configResourceManager.getMaxMemberBooks();

            int reservedBooksCount = reservationsService.getReservationsCountByUserIdAndStatusId(userId, ReservationStatus.RESERVED.ordinal() + 1) +
                    reservationsService.getReservationsCountByUserIdAndStatusId(userId, ReservationStatus.READY_TO_ISSUE.ordinal() + 1);
            int issuedBooksCount = loansService.getLoansCountByUserIdAndStatusId(userId, LoanStatus.ISSUED.ordinal() + 1);
            // Issue limit reached case
            if (reservedBooksCount + issuedBooksCount >= maxMemberBooks) {
                session.setAttribute(SessionAttribute.ERROR, "Issue limit reached");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            int loanPeriod = configResourceManager.getLoanPeriod();
            // No available copies case
            if (!loansService.loanByUserId(userId, bookIsbn, loanPeriod)) {
                session.setAttribute(SessionAttribute.ERROR, "No copies available");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String url = "/controller?" + RequestParameter.COMMAND + "=" + CommandName.GOTO_USER_LOANS_PAGE_COMMAND +
                    "&" + RequestParameter.USER_ID + "=" + userId;
            session.setAttribute(SessionAttribute.URL, url);

            return new CommandResult(url, RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to issue book.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
