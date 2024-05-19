package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.Subject;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

/**
 * Book service interface.
 */
public interface SubjectService {

    boolean saveSubject(String name, int hours) throws ServiceException;

    boolean updateSubject(int id, String name, int hours) throws ServiceException;

    void deleteSubject(int id) throws ServiceException;

    List<Subject> getAllSubjects() throws ServiceException;

    Subject getSubjectById(int id) throws ServiceException;
}
