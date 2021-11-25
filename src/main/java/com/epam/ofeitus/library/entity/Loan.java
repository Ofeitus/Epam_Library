package com.epam.ofeitus.library.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Loan {
    private int loanId;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private BigDecimal fineAmount;
    private int userId;
}
