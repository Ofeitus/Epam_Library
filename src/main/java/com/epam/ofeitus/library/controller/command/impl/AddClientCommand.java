package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import com.epam.ofeitus.library.service.validator.ValidationPattern;
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

public class AddClientCommand implements Command {
    private final Logger logger = LogManager.getLogger(AddClientCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            String name = request.getParameter(RequestParameter.USER_NAME);
            if (name == null || !name.matches(ValidationPattern.NAME_PATTERN)) {
                session.setAttribute(SessionAttribute.ERROR, "Неверное имя");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String surname = request.getParameter(RequestParameter.USER_SURNAME);
            if (surname == null || !surname.matches(ValidationPattern.NAME_PATTERN)) {
                session.setAttribute(SessionAttribute.ERROR, "Неверная фамилия");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String patronymic = request.getParameter(RequestParameter.USER_PATRONYMIC);
            if (patronymic == null || !patronymic.matches(ValidationPattern.NAME_PATTERN)) {
                session.setAttribute(SessionAttribute.ERROR, "Неверное отчество");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            Date dateOfBirth;
            try {
                String date = request.getParameter(RequestParameter.USER_DATE_OF_BIRTH);
                //date = "2015-02-29";
                dateOfBirth = formatter.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateOfBirth);
                if (cal.getActualMaximum(Calendar.DAY_OF_YEAR) == 365 && date.endsWith("02-29")) {
                    throw new ParseException("Nice try", 0);
                }
            } catch (ParseException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверная дата рождения");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String genderString = request.getParameter(RequestParameter.USER_GENDER);
            if (genderString == null || (!genderString.equals("m") && !genderString.equals("f"))) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный пол");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }
            boolean gender = genderString.equals("m");

            String passportSeries = request.getParameter(RequestParameter.USER_PASSPORT_SERIES);
            if (passportSeries == null) {
                session.setAttribute(SessionAttribute.ERROR, "Неверная серия пасспорта");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String passportNumber = request.getParameter(RequestParameter.USER_PASSPORT_NUMBER);
            if (passportNumber == null || !passportNumber.matches(ValidationPattern.PASSPORT_NUMBER)) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный номер паспорта");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }
            User testPasswordNumber = userService.getByPassportNumber(passportNumber);
            if (testPasswordNumber != null) {
                session.setAttribute(SessionAttribute.ERROR, "Введённый номер паспорта занят");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }


            String issuedBy = request.getParameter(RequestParameter.USER_ISSUED_BY);
            if (issuedBy == null) {
                session.setAttribute(SessionAttribute.ERROR, "Неверно поле \"кем выдан\"");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            Date dateOfIssuing;
            try {
                String date = request.getParameter(RequestParameter.USER_DATE_OF_ISSUING);
                //date = "2015-02-29";
                dateOfIssuing = formatter.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateOfIssuing);
                if (cal.getActualMaximum(Calendar.DAY_OF_YEAR) == 365 && date.endsWith("02-29")) {
                    throw new ParseException("Nice try", 0);
                }
            } catch (ParseException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверная дата выдачи");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String passportId = request.getParameter(RequestParameter.USER_PASSPORT_ID);
            if (passportId == null || !passportId.matches(ValidationPattern.PASSPORT_ID)) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный идент. номер паспорта");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }
            User testPasswordId = userService.getByPassportId(passportId);
            if (testPasswordId != null) {
                session.setAttribute(SessionAttribute.ERROR, "Введённый идент. номер паспорта занят");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String placeOfBirth = request.getParameter(RequestParameter.USER_PLACE_OF_BIRTH);
            if (placeOfBirth == null) {
                session.setAttribute(SessionAttribute.ERROR, "Неверное место рождения");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            int cityOfLiving;
            try {
                cityOfLiving = Integer.parseInt(request.getParameter(RequestParameter.CITY_OF_LIVING));
                if (cityOfLiving < 1 || cityOfLiving > 5) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный город проживания");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String address = request.getParameter(RequestParameter.USER_ADDRESS);
            if (address == null) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный адрес проживания");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String phoneHome = request.getParameter(RequestParameter.USER_PHONE_HOME);
            if (phoneHome != "" && (phoneHome == null || !phoneHome.matches(ValidationPattern.HOME_PHONE_PATTERN))) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный домашний номер");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String phoneMobile = request.getParameter(RequestParameter.USER_PHONE_MOBILE);
            if (phoneMobile != "" && (phoneMobile == null || !phoneMobile.matches(ValidationPattern.MOBILE_PHONE_PATTERN))) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный мобильный номер");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String email = request.getParameter(RequestParameter.USER_EMAIL);
            if (email != "" && (email == null || !email.matches(ValidationPattern.EMAIL_PATTERN))) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный email");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String placeOfWork = request.getParameter(RequestParameter.USER_PLACE_OF_WORK);
            if (placeOfWork == null) {
                session.setAttribute(SessionAttribute.ERROR, "Неверное место работы");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String jobTitle = request.getParameter(RequestParameter.USER_JOB_TITLE);
            if (jobTitle == null) {
                session.setAttribute(SessionAttribute.ERROR, "Неверная должность");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            int cityOfRegistration;
            try {
                cityOfRegistration = Integer.parseInt(request.getParameter(RequestParameter.CITY_OF_REGISTRATION));
                if (cityOfRegistration < 1 || cityOfRegistration > 5) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный город прописки");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            String addressOfRegistration = request.getParameter(RequestParameter.USER_ADDRESS_OF_REGISTRATION);
            if (addressOfRegistration == null) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный адрес прописки");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            int familyStatus;
            try {
                familyStatus = Integer.parseInt(request.getParameter(RequestParameter.FAMILY_STATUS));
                if (familyStatus < 1 || familyStatus > 4) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверное семейное положение");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            int disability;
            try {
                disability = Integer.parseInt(request.getParameter(RequestParameter.DISABILITY));
                if (disability < 1 || disability > 4) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверная инвалидность");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            boolean pensioner = request.getParameter(RequestParameter.USER_PENSIONER) != null;

            BigDecimal salary = null;
            try {
                if (request.getParameter(RequestParameter.USER_SALARY) != "") {
                    salary = new BigDecimal(request.getParameter(RequestParameter.USER_SALARY));
                }
            } catch (NumberFormatException e) {
                session.setAttribute(SessionAttribute.ERROR, "Неверный доход");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            boolean conscript = request.getParameter(RequestParameter.USER_CONSCRIPT) != null;

            userService.addUser(name, surname, patronymic, dateOfBirth, gender, passportSeries, passportNumber, issuedBy, dateOfIssuing, passportId, placeOfBirth, cityOfLiving, address, phoneHome, phoneMobile, email, placeOfWork, jobTitle, cityOfRegistration, addressOfRegistration, familyStatus, disability, pensioner, salary, conscript);

            int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));

            String command = "?" + RequestParameter.COMMAND + "=" + CommandName.GOTO_MANAGE_USERS_PAGE_COMMAND;
            session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);
            String url = "/controller" + command +
                    "&" + RequestParameter.PAGE + "=" + page;
            session.setAttribute(SessionAttribute.URL, url);

            return new CommandResult(url, RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to update client.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
