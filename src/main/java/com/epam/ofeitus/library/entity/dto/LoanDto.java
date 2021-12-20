package com.epam.ofeitus.library.entity.dto;

import com.epam.ofeitus.library.entity.book.CopyOfBook;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class LoanDto {
    private int loanId;
    private List<CopyOfBook> copiesOfBooks;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private BigDecimal fineAmount;
    private int userId;

    public LoanDto() {
    }

    public LoanDto(int loanId, List<CopyOfBook> copiesOfBooks, Date issueDate, Date dueDate, Date returnDate, BigDecimal fineAmount, int userId) {
        this.loanId = loanId;
        this.copiesOfBooks = copiesOfBooks;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
        this.userId = userId;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public List<CopyOfBook> getCopiesOfBooks() {
        return copiesOfBooks;
    }

    public void setCopiesOfBooks(List<CopyOfBook> copiesOfBooks) {
        this.copiesOfBooks = copiesOfBooks;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDto loanDto = (LoanDto) o;
        return loanId == loanDto.loanId && userId == loanDto.userId && Objects.equals(copiesOfBooks, loanDto.copiesOfBooks) && Objects.equals(issueDate, loanDto.issueDate) && Objects.equals(dueDate, loanDto.dueDate) && Objects.equals(returnDate, loanDto.returnDate) && Objects.equals(fineAmount, loanDto.fineAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, copiesOfBooks, issueDate, dueDate, returnDate, fineAmount, userId);
    }

    @Override
    public String toString() {
        return "LoanDto{" +
                "loanId=" + loanId +
                ", copiesOfBooks=" + copiesOfBooks +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", fineAmount=" + fineAmount +
                ", userId=" + userId +
                '}';
    }
}
