package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.constant.ConfigParameter;
import com.epam.ofeitus.library.constant.ConfigResourceManager;
import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.dto.BookDto;
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
import java.util.MissingResourceException;

public class GoToBookDetailsPageCommand implements Command {
    private final Logger logger = LogManager.getLogger(GoToBookDetailsPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        LoansService loansService = serviceFactory.getLoansService();
        ReservationsService reservationsService = serviceFactory.getReservationsService();

        ConfigResourceManager configResourceManager = ConfigResourceManager.getInstance();

        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);


        session.setAttribute(SessionAttribute.URL, "/controller?" +
                RequestParameter.COMMAND + "=" + CommandName.GOTO_BOOK_DETAILS_PAGE_COMMAND +
                "&" + RequestParameter.BOOK_ISBN + "=" + bookIsbn);

        try {
            Object userIdAtr = session.getAttribute(SessionAttribute.USER_ID);
            int userId = userIdAtr != null ? (int)userIdAtr : -1;

            int copiesCount = bookService.getCopiesCount(bookIsbn);
            int availableCopiesCount = bookService.getAvailableCopiesCount(bookIsbn);
            int reservedBooksCount;
            int issuedBooksCount;

            if (userId != -1) {
                reservedBooksCount = reservationsService.getReservationsCountByUserIdAndStatusId(userId, ReservationStatus.RESERVED.ordinal() + 1) +
                        reservationsService.getReservationsCountByUserIdAndStatusId(userId, ReservationStatus.READY_TO_ISSUE.ordinal() + 1);
                issuedBooksCount = loansService.getLoansCountByUserIdAndStatusId(userId, LoanStatus.ISSUED.ordinal() + 1);
            } else {
                reservedBooksCount = 0;
                issuedBooksCount = 0;
            }

            int maxMemberBooks = 5;
            try {
                maxMemberBooks = Integer.parseInt(configResourceManager.getValue(ConfigParameter.MAX_MEMBER_BOOKS));
            } catch (NumberFormatException | MissingResourceException e) {
                logger.error("Unable to get max member books.", e);
            }

            BookDto book = bookService.getBookDtoByIsbn(bookIsbn);

            request.setAttribute(RequestAttribute.BOOK, book);
            request.setAttribute(RequestAttribute.COPIES_COUNT, copiesCount);
            request.setAttribute(RequestAttribute.AVAILABLE_COPIES_COUNT, availableCopiesCount);
            request.setAttribute(RequestAttribute.RESERVED_BOOKS_COUNT, reservedBooksCount);
            request.setAttribute(RequestAttribute.ISSUED_BOOKS_COUNT, issuedBooksCount);
            request.setAttribute(RequestAttribute.MAX_MEMBER_BOOKS, maxMemberBooks);

            return new CommandResult(Page.BOOK_DETAILS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException | ClassCastException e) {
            logger.error("Unable to get book DTO.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
