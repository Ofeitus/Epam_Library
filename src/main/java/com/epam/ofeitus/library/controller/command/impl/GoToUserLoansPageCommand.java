package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.dto.LoanDto;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;
import com.epam.ofeitus.library.service.LoansService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * Command to go to user loans page.
 */
public class GoToUserLoansPageCommand implements Command {
    private final Logger logger = LogManager.getLogger(GoToUserLoansPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoansService loansService = ServiceFactory.getInstance().getLoansService();

        String requestUserId = request.getParameter(RequestParameter.USER_ID);

        try {
            int userId = (int) session.getAttribute(SessionAttribute.USER_ID);

            if (session.getAttribute(SessionAttribute.USER_ROLE) == UserRole.MANAGER && requestUserId != null) {
                userId = Integer.parseInt(requestUserId);
            }

            int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));
            int itemsOnPage = 10;

            String command = "?" + RequestParameter.COMMAND + "=" + CommandName.GOTO_USER_LOANS_PAGE_COMMAND +
                    "&" + RequestParameter.USER_ID + "=" + userId;
            session.setAttribute(SessionAttribute.URL, "/controller" + command +
                    "&" + RequestParameter.PAGE + "=" + page);
            session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);

            List<LoanDto> loans = loansService.getLoansDtoByUserId(userId, page, itemsOnPage);

            int itemsCount = loansService.countLoansDtoByUserId(userId);
            int pagesCount = itemsCount / itemsOnPage;
            if (itemsCount % itemsOnPage != 0) {
                pagesCount++;
            }

            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.PAGES_COUNT, pagesCount);
            request.setAttribute(RequestAttribute.LOANS, loans);
            request.setAttribute(RequestAttribute.USER_ID, userId);

            return new CommandResult(Page.USER_LOANS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException | ClassCastException | NumberFormatException e) {
            logger.error("Unable to get user loans DTO", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
