package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.dto.LoanDto;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public interface LoansService {
    List<LoanDto> getLoansDtoByUserId(int userId) throws ServiceException;
}
