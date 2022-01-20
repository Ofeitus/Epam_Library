package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.dto.ReservationDto;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;
import com.epam.ofeitus.library.service.ReservationsService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class GoToUserReservationsPageCommand implements Command {
    private final Logger logger = LogManager.getLogger(GoToUserReservationsPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ReservationsService reservationsService = ServiceFactory.getInstance().getReservationsService();

        String requestUserId = request.getParameter(RequestParameter.USER_ID);

        try {
            int userId = (int) session.getAttribute(SessionAttribute.USER_ID);

            if (session.getAttribute(SessionAttribute.USER_ROLE) == UserRole.MANAGER && requestUserId != null) {
                userId = Integer.parseInt(requestUserId);
            }

            int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));
            int itemsOnPage = 10;

            String command = "?command=goto-user-reservations-page&user-id=" + userId;
            session.setAttribute(SessionAttribute.URL, "/controller" + command + "&page=" + page);
            session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);

            List<ReservationDto> reservations = reservationsService.getReservationsDtoByUserId(userId, page, itemsOnPage);

            int itemsCount = reservationsService.countReservationsDtoByUserId(userId);
            int pagesCount = itemsCount / itemsOnPage;
            if (itemsCount % itemsOnPage != 0) {
                pagesCount++;
            }

            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.PAGES_COUNT, pagesCount);
            request.setAttribute(RequestAttribute.RESERVATIONS, reservations);
            request.setAttribute(RequestAttribute.USER_ID, userId);

            return new CommandResult(Page.USER_RESERVATIONS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException | ClassCastException | NumberFormatException e) {
            logger.error("Unable to get user reservations DTO", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
