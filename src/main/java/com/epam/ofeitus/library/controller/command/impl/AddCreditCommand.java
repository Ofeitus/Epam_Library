package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.bank.Currency;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;
import com.epam.ofeitus.library.service.CreditService;
import com.epam.ofeitus.library.service.DepositService;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class AddCreditCommand implements Command {
    private final Logger logger = LogManager.getLogger(AddCreditCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();
        CreditService creditService = ServiceFactory.getInstance().getCreditService();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));

            User user = userService.getByUserId(userId);
            if (user == null || user.getUserRole() == UserRole.ADMIN) {
                session.setAttribute(SessionAttribute.ERROR, "Выбранный клиент не существует");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            int creditType;
            try {
                creditType = Integer.parseInt(request.getParameter(RequestParameter.TYPE));
                if (creditType < 1 || creditType > 2) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный тип кредита");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            int number;
            try {
                number = Integer.parseInt(request.getParameter(RequestParameter.AGREEMENT_NUMBER));
                if (number < 1) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный номер договора");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            Date startDate;
            try {
                String date = request.getParameter(RequestParameter.DATE_OF_START);
                //date = "2015-02-29";
                startDate = formatter.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                if (cal.getActualMaximum(Calendar.DAY_OF_YEAR) == 365 && date.endsWith("02-29")) {
                    throw new ParseException("Nice try", 0);
                }
            } catch (ParseException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверная дата начала");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            Date endDate;
            try {
                String date = request.getParameter(RequestParameter.DATE_OF_END);
                //date = "2015-02-29";
                endDate = formatter.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(endDate);
                if (cal.getActualMaximum(Calendar.DAY_OF_YEAR) == 365 && date.endsWith("02-29")) {
                    throw new ParseException("Nice try", 0);
                }
            } catch (ParseException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверная дата окончания");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            long diffInMillis = Math.abs(endDate.getTime() - startDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            if (diff < 1) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный срок договора");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }
            int term = (int) diff;

            Currency currency;
            try {
                currency = Currency.valueOf(request.getParameter(RequestParameter.CURRENCY));
            } catch (IllegalArgumentException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверная валюта");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            BigDecimal amount;
            try {
                amount = new BigDecimal(request.getParameter(RequestParameter.AMOUNT));
            } catch (NumberFormatException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверная сумма");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            BigDecimal percent;
            try {
                percent = new BigDecimal(request.getParameter(RequestParameter.PERCENT));
            } catch (NumberFormatException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный процент");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            creditService.addCredit(user, creditType, number, startDate, endDate, term, currency, amount, percent);

            int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));

            String command = "?" + RequestParameter.COMMAND + "=" + CommandName.GOTO_MANAGE_USERS_PAGE_COMMAND;
            session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);
            String url = "/controller" + command +
                    "&" + RequestParameter.PAGE + "=" + page;
            session.setAttribute(SessionAttribute.URL, url);

            return new CommandResult(url, RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to add credit.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
