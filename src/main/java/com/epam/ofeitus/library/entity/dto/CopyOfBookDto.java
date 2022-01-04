package com.epam.ofeitus.library.entity.dto;

import com.epam.ofeitus.library.entity.book.constituent.CopyOfBookStatus;

import java.util.Date;
import java.util.Objects;

public class CopyOfBookDto {
    private int inventoryId;
    private Date receiptDate;
    private String bookIsbn;
    private CopyOfBookStatus copyOfBookStatus;
    private BookDto book;
    private boolean canBeDeleted;

    public CopyOfBookDto() {
    }

    public CopyOfBookDto(int inventoryId, Date receiptDate, String bookIsbn, CopyOfBookStatus copyOfBookStatus, BookDto book, boolean canBeDeleted) {
        this.inventoryId = inventoryId;
        this.receiptDate = receiptDate;
        this.bookIsbn = bookIsbn;
        this.copyOfBookStatus = copyOfBookStatus;
        this.book = book;
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
        return inventoryId == that.inventoryId && canBeDeleted == that.canBeDeleted && Objects.equals(receiptDate, that.receiptDate) && Objects.equals(bookIsbn, that.bookIsbn) && copyOfBookStatus == that.copyOfBookStatus && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, receiptDate, bookIsbn, copyOfBookStatus, book, canBeDeleted);
    }

    @Override
    public String toString() {
        return "CopyOfBookDto{" +
                "inventoryId=" + inventoryId +
                ", receiptDate=" + receiptDate +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", copyOfBookStatus=" + copyOfBookStatus +
                ", book=" + book +
                ", canBeDeleted=" + canBeDeleted +
                '}';
    }
}
