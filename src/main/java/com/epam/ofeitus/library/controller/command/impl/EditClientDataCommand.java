package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
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
import java.util.Date;
import java.util.Optional;

public class EditClientDataCommand implements Command {
    private final Logger logger = LogManager.getLogger(EditClientDataCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
            String name = request.getParameter(RequestParameter.USER_NAME);
            String surname = request.getParameter(RequestParameter.USER_SURNAME);
            String patronymic = request.getParameter(RequestParameter.USER_PATRONYMIC);
            Date dateOfBirth = formatter.parse(request.getParameter(RequestParameter.USER_DATE_OF_BIRTH));
            boolean gender = Boolean.getBoolean(request.getParameter(RequestParameter.USER_GENDER));
            String passportSeries = request.getParameter(RequestParameter.USER_PASSPORT_SERIES);
            String passportNumber = request.getParameter(RequestParameter.USER_PASSPORT_NUMBER);
            String issuedBy = request.getParameter(RequestParameter.USER_ISSUED_BY);
            Date dateOfIssuing = formatter.parse(request.getParameter(RequestParameter.USER_DATE_OF_ISSUING));
            String passportId = request.getParameter(RequestParameter.USER_PASSPORT_ID);
            String placeOfBirth = request.getParameter(RequestParameter.USER_PLACE_OF_BIRTH);
            int cityOfLiving = Integer.parseInt(request.getParameter(RequestParameter.CITY_OF_LIVING));
            String address = request.getParameter(RequestParameter.USER_ADDRESS);
            String phoneHome = request.getParameter(RequestParameter.USER_PHONE_HOME);
            String phoneMobile = request.getParameter(RequestParameter.USER_PHONE_MOBILE);
            String email = request.getParameter(RequestParameter.USER_EMAIL);
            String placeOfWork = request.getParameter(RequestParameter.USER_PLACE_OF_WORK);
            String jobTitle = request.getParameter(RequestParameter.USER_JOB_TITLE);
            int cityOfRegistration = Integer.parseInt(request.getParameter(RequestParameter.CITY_OF_REGISTRATION));
            String addressOfRegistration = request.getParameter(RequestParameter.USER_ADDRESS_OF_REGISTRATION);
            int familyStatus = Integer.parseInt(request.getParameter(RequestParameter.FAMILY_STATUS));
            int disability = Integer.parseInt(request.getParameter(RequestParameter.DISABILITY));
            boolean pensioner = Boolean.getBoolean(request.getParameter(RequestParameter.USER_PENSIONER));
            BigDecimal salary;
            if (request.getParameter(RequestParameter.USER_SALARY).equals("")) {
                salary = null;
            } else {
                salary = new BigDecimal(request.getParameter(RequestParameter.USER_SALARY));
            }
            boolean conscript = Boolean.getBoolean(request.getParameter(RequestParameter.USER_CONSCRIPT));

            userService.editUser(userId, name, surname, patronymic, dateOfBirth, gender, passportSeries, passportNumber, issuedBy, dateOfIssuing, passportId, placeOfBirth, cityOfLiving, address, phoneHome, phoneMobile, email, placeOfWork, jobTitle, cityOfRegistration, addressOfRegistration, familyStatus, disability, pensioner, salary, conscript);

            int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));

            String command = "?" + RequestParameter.COMMAND + "=" + CommandName.GOTO_MANAGE_USERS_PAGE_COMMAND;
            session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);
            String url = "/controller" + command +
                    "&" + RequestParameter.PAGE + "=" + page;
            session.setAttribute(SessionAttribute.URL, url);

            return new CommandResult(url, RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException | ParseException e) {
            logger.error("Unable to update client.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
