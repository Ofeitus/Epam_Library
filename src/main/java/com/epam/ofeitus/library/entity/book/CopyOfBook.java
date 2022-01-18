package com.epam.ofeitus.library.entity.book;

import com.epam.ofeitus.library.entity.book.constituent.CopyOfBookStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class CopyOfBook implements Serializable {
    private int inventoryId;
    private Date receiptDate;
    private BigDecimal price;
    private String bookIsbn;
    private CopyOfBookStatus copyOfBookStatus;

    public CopyOfBook() {
    }

    public CopyOfBook(int inventoryId, Date receiptDate, BigDecimal price, String bookIsbn, CopyOfBookStatus copyOfBookStatus) {
        this.inventoryId = inventoryId;
        this.receiptDate = receiptDate;
        this.price = price;
        this.bookIsbn = bookIsbn;
        this.copyOfBookStatus = copyOfBookStatus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyOfBook that = (CopyOfBook) o;
        return inventoryId == that.inventoryId && Objects.equals(receiptDate, that.receiptDate) && Objects.equals(price, that.price) && Objects.equals(bookIsbn, that.bookIsbn) && copyOfBookStatus == that.copyOfBookStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, receiptDate, price, bookIsbn, copyOfBookStatus);
    }

    @Override
    public String toString() {
        return "CopyOfBook{" +
                "inventoryId=" + inventoryId +
                ", receiptDate=" + receiptDate +
                ", price=" + price +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", copyOfBookStatus=" + copyOfBookStatus +
                '}';
    }
}
