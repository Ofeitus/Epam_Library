package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.dto.LoanDto;
import com.epam.ofeitus.library.service.LoansService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToUserFinesPageCommand implements Command {
    Logger logger = LogManager.getLogger(GoToUserLoanPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-user-fines-page");

        LoansService loansService = ServiceFactory.getInstance().getLoansService();
        try {
            List<LoanDto> fines = loansService.getLoansDtoByUserIdWithFine((int)session.getAttribute(SessionAttribute.USER_ID));
            request.setAttribute(RequestAttribute.FINES, fines);
            return new CommandResult(Page.USER_FINES_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get user loans (fines) DTO", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
