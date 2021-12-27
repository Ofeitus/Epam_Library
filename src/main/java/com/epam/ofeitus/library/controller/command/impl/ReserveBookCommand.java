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
import com.mysql.cj.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReserveBookCommand implements Command {
    Logger logger = LogManager.getLogger(ReserveBookCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-book-details-page&book-isbn=" + bookIsbn);

        int userId = (int) session.getAttribute(SessionAttribute.USER_ID);
        ReservationsService reservationsService = ServiceFactory.getInstance().getReservationsService();

        try {
            boolean reserved = reservationsService.makeReservation(userId, bookIsbn);
            return new CommandResult("/controller?command=goto-book-details-page&book-isbn=" + bookIsbn, RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Unable to cancel reservation.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
