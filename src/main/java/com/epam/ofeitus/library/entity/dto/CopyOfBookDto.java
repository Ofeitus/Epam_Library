package com.epam.ofeitus.library.entity.dto;

import com.epam.ofeitus.library.entity.book.constituent.CopyOfBookStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Copy of book Dto bean class.
 */
public class CopyOfBookDto implements Serializable {
    private int inventoryId;
    private Date receiptDate;
    private BigDecimal price;
    private String bookIsbn;
    private CopyOfBookStatus copyOfBookStatus;
    private BookDto book;
    private int userId;
    private boolean canBeDeleted;

    public CopyOfBookDto() {
    }

    public CopyOfBookDto(int inventoryId, Date receiptDate, BigDecimal price, String bookIsbn, CopyOfBookStatus copyOfBookStatus, BookDto book, int userId, boolean canBeDeleted) {
        this.inventoryId = inventoryId;
        this.receiptDate = receiptDate;
        this.price = price;
        this.bookIsbn = bookIsbn;
        this.copyOfBookStatus = copyOfBookStatus;
        this.book = book;
        this.userId = userId;
        this.canBeDeleted = canBeDeleted;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public CopyOfBookStatus getCopyOfBookStatus() {
        return copyOfBookStatus;
    }

    public void setCopyOfBookStatus(CopyOfBookStatus copyOfBookStatus) {
        this.copyOfBookStatus = copyOfBookStatus;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isCanBeDeleted() {
        return canBeDeleted;
    }

    public void setCanBeDeleted(boolean canBeDeleted) {
        this.canBeDeleted = canBeDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyOfBookDto that = (CopyOfBookDto) o;
        return inventoryId == that.inventoryId && userId == that.userId && canBeDeleted == that.canBeDeleted && Objects.equals(receiptDate, that.receiptDate) && Objects.equals(price, that.price) && Objects.equals(bookIsbn, that.bookIsbn) && copyOfBookStatus == that.copyOfBookStatus && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, receiptDate, price, bookIsbn, copyOfBookStatus, book, userId, canBeDeleted);
    }

    @Override
    public String toString() {
        return "CopyOfBookDto{" +
                "inventoryId=" + inventoryId +
                ", receiptDate=" + receiptDate +
                ", price=" + price +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", copyOfBookStatus=" + copyOfBookStatus +
                ", book=" + book +
                ", userId=" + userId +
                ", canBeDeleted=" + canBeDeleted +
                '}';
    }
}
