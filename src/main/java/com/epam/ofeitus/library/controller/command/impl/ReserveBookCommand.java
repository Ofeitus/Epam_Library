package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.service.ReservationsService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReserveBookCommand implements Command {
    private final Logger logger = LogManager.getLogger(ReserveBookCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ReservationsService reservationsService = ServiceFactory.getInstance().getReservationsService();

        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);
        int userId = (int) session.getAttribute(SessionAttribute.USER_ID);

        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-book-details-page&book-isbn=" + bookIsbn);

        try {
            if (!reservationsService.makeReservation(userId, bookIsbn)) {
                session.setAttribute(SessionAttribute.ERROR, "No copies available");
            }

            return new CommandResult("/controller?command=goto-book-details-page&book-isbn=" + bookIsbn, RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Unable to reserve book.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
