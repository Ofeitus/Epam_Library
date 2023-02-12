package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.report.UserCompositionReport;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import com.epam.ofeitus.library.service.validator.EntityValidator;
import com.epam.ofeitus.library.service.validator.ValidationPattern;
import com.epam.ofeitus.library.service.validator.ValidatorFactory;
import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    @Override
    public boolean register(String firstName, String lastName, String email, String password) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        EntityValidator<User> userValidator = ValidatorFactory.getInstance().getUserValidator();

        User user = new User(0, new Date(), firstName, lastName, "", email, DigestUtils.sha256Hex(password), UserRole.MEMBER, false);

        if (password == null ||
                !Pattern.compile(ValidationPattern.PASSWORD_PATTERN).matcher(password).matches() ||
                !userValidator.validate(user)) {
            return false;
        }

        try {
            return userDao.save(user) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addUser(String name, String surname, String patronymic, Date dateOfBirth, boolean gender, String passportSeries, String passportNumber, String issuedBy, Date dateOfIssuing, String passportId, String placeOfBirth, int cityOfLiving, String address, String phoneHome, String phoneMobile, String email, String placeOfWork, String jobTitle, int cityOfRegistration, String addressOfRegistration, int familyStatus, int disability, boolean pensioner, BigDecimal salary, boolean conscript) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        User user = new User(0, new Date(), name, surname, patronymic, dateOfBirth, gender, passportSeries, passportNumber, issuedBy, dateOfIssuing, passportId, placeOfBirth, cityOfLiving, address, phoneHome, phoneMobile, placeOfWork, jobTitle, cityOfRegistration, addressOfRegistration, familyStatus, disability, pensioner, salary, conscript, "", email, DigestUtils.sha256Hex(email), UserRole.MEMBER, false);

        try {
            return userDao.save(user) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User login(String email, String password) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        User user = null;
        try {
            User userFromDB = userDao.findByEmail(email);
            if (userFromDB != null && !userFromDB.isDeleted() && userFromDB.getPasswordHash().equals(DigestUtils.sha256Hex(password))) {
                user = userFromDB;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public boolean editUser(int userId, String name, String surname, String patronymic, Date dateOfBirth, boolean gender, String passportSeries, String passportNumber, String issuedBy, Date dateOfIssuing, String passportId, String placeOfBirth, int cityOfLiving, String address, String phoneHome, String phoneMobile, String email, String placeOfWork, String jobTitle, int cityOfRegistration, String addressOfRegistration, int familyStatus, int disability, boolean pensioner, BigDecimal salary, boolean conscript) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        //EntityValidator<User> userValidator = ValidatorFactory.getInstance().getUserValidator();

        try {
            User user = userDao.findExistingById(userId);
            user.setName(name);
            user.setSurname(surname);
            user.setPatronymic(patronymic);
            user.setDateOfBirth(dateOfBirth);
            user.setGender(gender);
            user.setPassportSeries(passportSeries);
            user.setPassportNumber(passportNumber);
            user.setIssuedBy(issuedBy);
            user.setDateOfIssuing(dateOfIssuing);
            user.setPassportId(passportId);
            user.setPlaceOfBirth(placeOfBirth);
            user.setCityOfLiving(cityOfLiving);
            user.setAddress(address);
            user.setPhoneHome(phoneHome);
            user.setPhoneMobile(phoneMobile);
            user.setEmail(email);
            user.setPlaceOfWork(placeOfWork);
            user.setJobTitle(jobTitle);
            user.setCityOfRegistration(cityOfRegistration);
            user.setAddressOfRegistration(addressOfRegistration);
            user.setFamilyStatus(familyStatus);
            user.setDisability(disability);
            user.setPensioner(pensioner);
            user.setSalary(salary);
            user.setConscript(conscript);

            //if (!userValidator.validate(user)) {
            //    return false;
            //}

            return userDao.update(user) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean editPersonalData(int userId, String name, String surname, String phoneNumber) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        EntityValidator<User> userValidator = ValidatorFactory.getInstance().getUserValidator();

        try {
            User user = userDao.findExistingById(userId);
            user.setName(name);
            user.setSurname(surname);
            user.setPhoneNumber(phoneNumber);

            if (!userValidator.validate(user)) {
                return false;
            }

            return userDao.update(user) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(int userId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            User user = userDao.findExistingById(userId);
            if (user.getUserRole() == UserRole.ADMIN) {
                throw new ServiceException("Can't delete.");
            }
            userDao.deleteById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void restoreUser(int userId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            User user = userDao.findById(userId);
            if (user.getUserRole() == UserRole.ADMIN) {
                throw new ServiceException("Can't restore.");
            }
            userDao.restoreById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setRole(int userId, int roleId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            User user = userDao.findById(userId);
            if (user.getUserRole() == UserRole.ADMIN || roleId == 1) {
                throw new ServiceException("Can't change role.");
            }
            user.setUserRole(UserRole.values()[roleId - 1]);
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByUserId(int userId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            return userDao.findExistingById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByEmail(String email) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByPassportNumber(String passportNumber) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            return userDao.findByPassportNumber(passportNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByPassportId(String passportId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            return userDao.findByPassportId(passportId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAll(int page, int itemsOnPage) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            return userDao.findAll(offset, itemsOnPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countAll() throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            return userDao.countAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllMembers(int page, int itemsOnPage) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            return userDao.findByRoleId(UserRole.MEMBER.ordinal() + 1, offset, itemsOnPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countAllMembers() throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            return userDao.countByRoleId(UserRole.MEMBER.ordinal() + 1);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getUsersBySearchRequest(int userRoleId, int userId, String email, int page, int itemsOnPage) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            return userDao.findBySearchRequest(userRoleId, userId, email, offset, itemsOnPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countUsersBySearchRequest(int userRoleId, int userId, String email) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            return userDao.countBySearchRequest(userRoleId, userId, email, new Date());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserCompositionReport getUserCompositionReport(Date fromDate, Date toDate) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            UserCompositionReport userCompositionReport = new UserCompositionReport(
                    userDao.countBySearchRequest(0, 0, "", fromDate),
                    userDao.countBySearchRequest(0, 0, "", toDate),
                    userDao.countBySearchRequest(1, 0, "", fromDate),
                    userDao.countBySearchRequest(1, 0, "", toDate),
                    userDao.countBySearchRequest(2, 0, "", fromDate),
                    userDao.countBySearchRequest(2, 0, "", toDate),
                    userDao.countBySearchRequest(3, 0, "", fromDate),
                    userDao.countBySearchRequest(3, 0, "", toDate),
                    null,
                    null
            );

            List<Date> dynamicsDates = new ArrayList<>();
            List<Integer> dynamicsValues = new ArrayList<>();

            Calendar start = Calendar.getInstance();
            start.setTime(fromDate);
            Calendar end = Calendar.getInstance();
            end.setTime(toDate);

            for (Date date = start.getTime(); start.before(end); start.add(Calendar.MONTH, 1), date = start.getTime()) {
                dynamicsDates.add(date);
                dynamicsValues.add(userDao.countBySearchRequest(3, 0, "", date));
            }

            dynamicsDates.add(new Date());
            dynamicsValues.add(userDao.countBySearchRequest(3, 0, "", new Date()));

            userCompositionReport.setDynamicsDates(dynamicsDates);
            userCompositionReport.setDynamicsValues(dynamicsValues);

            return userCompositionReport;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
