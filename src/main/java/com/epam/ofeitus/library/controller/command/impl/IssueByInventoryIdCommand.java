package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.constant.ConfigResourceManager;
import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.order.constiuent.LoanStatus;
import com.epam.ofeitus.library.entity.order.constiuent.ReservationStatus;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.LoansService;
import com.epam.ofeitus.library.service.ReservationsService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command to issue book by inventory id.
 */
public class IssueByInventoryIdCommand implements Command {
    private final Logger logger = LogManager.getLogger(IssueByInventoryIdCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        LoansService loansService = serviceFactory.getLoansService();
        ReservationsService reservationsService = serviceFactory.getReservationsService();

        ConfigResourceManager configResourceManager = ConfigResourceManager.getInstance();

        try {
            int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
            int inventoryId = Integer.parseInt(request.getParameter(RequestParameter.INVENTORY_ID));

            // Copy does not exist case
            if (bookService.getCopyOfBookByInventoryId(inventoryId) == null) {
                session.setAttribute(SessionAttribute.ERROR, "Copy does not exist");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            int maxMemberBooks = configResourceManager.getMaxMemberBooks();

            int reservedBooksCount = reservationsService.countReservationsByUserIdAndStatusId(userId, ReservationStatus.RESERVED.ordinal() + 1) +
                reservationsService.countReservationsByUserIdAndStatusId(userId, ReservationStatus.READY_TO_ISSUE.ordinal() + 1);
            int issuedBooksCount = loansService.countLoansByUserIdAndStatusId(userId, LoanStatus.ISSUED.ordinal() + 1);
            // Issue limit reached case
            if (reservedBooksCount + issuedBooksCount >= maxMemberBooks) {
                session.setAttribute(SessionAttribute.ERROR, "Issue limit reached");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            int loanPeriod = configResourceManager.getLoanPeriod();
            // Copy is not available
            if (!loansService.loanByInventoryId(userId, inventoryId, loanPeriod)) {
                session.setAttribute(SessionAttribute.ERROR, "Copy is not available");
            }

            return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to issue book by inventory id.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
