package com.epam.ofeitus.library.entity.order;

import com.epam.ofeitus.library.entity.order.constiuent.LoanStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Loan bean class.
 */
public class Loan implements Serializable {
    private int loanId;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private BigDecimal fineAmount;
    private int userId;
    private int inventoryId;
    private LoanStatus loanStatus;

    public Loan() {
    }

    public Loan(int loanId, Date issueDate, Date dueDate, Date returnDate, BigDecimal fineAmount, int userId, int inventoryId, LoanStatus loanStatus) {
        this.loanId = loanId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
        this.userId = userId;
        this.inventoryId = inventoryId;
        this.loanStatus = loanStatus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanId == loan.loanId && userId == loan.userId && inventoryId == loan.inventoryId && Objects.equals(issueDate, loan.issueDate) && Objects.equals(dueDate, loan.dueDate) && Objects.equals(returnDate, loan.returnDate) && Objects.equals(fineAmount, loan.fineAmount) && loanStatus == loan.loanStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, issueDate, dueDate, returnDate, fineAmount, userId, inventoryId, loanStatus);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", fineAmount=" + fineAmount +
                ", userId=" + userId +
                ", inventoryId=" + inventoryId +
                ", loanStatus=" + loanStatus +
                '}';
    }
}
