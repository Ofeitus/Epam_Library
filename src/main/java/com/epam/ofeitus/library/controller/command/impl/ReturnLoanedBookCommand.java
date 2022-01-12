package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.constant.ConfigParameter;
import com.epam.ofeitus.library.constant.ConfigResourceManager;
import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.service.LoansService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.MissingResourceException;

public class ReturnLoanedBookCommand implements Command {
    Logger logger = LogManager.getLogger(ReturnLoanedBookCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        int loanId = Integer.parseInt(request.getParameter(RequestParameter.LOAN_ID));

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-user-loans-page&user-id=" + userId);

        LoansService loansService = ServiceFactory.getInstance().getLoansService();

        ConfigResourceManager configResourceManager = ConfigResourceManager.getInstance();
        try {
            BigDecimal fineRate;
            try {
                fineRate = new BigDecimal(configResourceManager.getValue(ConfigParameter.FINE_RATE));
            } catch (NumberFormatException | MissingResourceException e) {
                logger.error("Unable to get fine rate.", e);
                fineRate = new BigDecimal("0.5");
            }
            loansService.returnBook(loanId, fineRate);
            return new CommandResult("/controller?command=goto-user-loans-page&user-id=" + userId, RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Unable to loan from reservation.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
