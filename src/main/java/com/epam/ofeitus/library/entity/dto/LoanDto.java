package com.epam.ofeitus.library.entity.dto;

import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.order.constiuent.LoanStatus;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * Loan Dto bean class.
 */
public class LoanDto {
    private int loanId;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private BigDecimal fineAmount;
    private int userId;
    private int inventoryId;
    private LoanStatus loanStatus;
    private Book book;

    public LoanDto() {
    }

    public LoanDto(int loanId, Date issueDate, Date dueDate, Date returnDate, BigDecimal fineAmount, int userId, int inventoryId, LoanStatus loanStatus, Book book) {
        this.loanId = loanId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
        this.userId = userId;
        this.inventoryId = inventoryId;
        this.loanStatus = loanStatus;
        this.book = book;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDto loanDto = (LoanDto) o;
        return loanId == loanDto.loanId && userId == loanDto.userId && inventoryId == loanDto.inventoryId && Objects.equals(issueDate, loanDto.issueDate) && Objects.equals(dueDate, loanDto.dueDate) && Objects.equals(returnDate, loanDto.returnDate) && Objects.equals(fineAmount, loanDto.fineAmount) && loanStatus == loanDto.loanStatus && Objects.equals(book, loanDto.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, issueDate, dueDate, returnDate, fineAmount, userId, inventoryId, loanStatus, book);
    }

    @Override
    public String toString() {
        return "LoanDto{" +
                "loanId=" + loanId +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", fineAmount=" + fineAmount +
                ", userId=" + userId +
                ", inventoryId=" + inventoryId +
                ", loanStatus=" + loanStatus +
                ", book=" + book +
                '}';
    }
}
