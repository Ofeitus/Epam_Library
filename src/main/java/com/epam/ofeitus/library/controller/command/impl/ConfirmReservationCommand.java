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

/**
 * Command to confirm book reservation.
 */
public class ConfirmReservationCommand implements Command {
    private final Logger logger = LogManager.getLogger(ConfirmReservationCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ReservationsService reservationsService = ServiceFactory.getInstance().getReservationsService();

        try {
            int reservationId = Integer.parseInt(request.getParameter(RequestParameter.RESERVATION_ID));

            reservationsService.confirmReservation(reservationId);

            return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Unable to confirm reservation.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
