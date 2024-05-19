package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.*;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.Subject;
import com.epam.ofeitus.library.service.SubjectService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.*;

public class SubjectServiceImpl implements SubjectService {

    @Override
    public boolean saveSubject(String name, int hours) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        SubjectDao subjectDao = daoFactory.getSubjectDao();

        try {
            Subject subject = new Subject(name, hours);
            return subjectDao.save(subject) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateSubject(int id, String name, int hours) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        SubjectDao subjectDao = daoFactory.getSubjectDao();

        try {
            Subject subject = new Subject(id, name, hours);
            return subjectDao.update(subject) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteSubject(int id) throws ServiceException {
        SubjectDao subjectDao = MySqlDaoFactory.getInstance().getSubjectDao();

        try {
            subjectDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Subject> getAllSubjects() throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        SubjectDao subjectDao = daoFactory.getSubjectDao();

        try {
            return subjectDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Subject getSubjectById(int id) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        SubjectDao subjectDao = daoFactory.getSubjectDao();

        try {
            return subjectDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
