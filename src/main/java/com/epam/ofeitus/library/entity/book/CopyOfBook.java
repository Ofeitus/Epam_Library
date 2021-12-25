package com.epam.ofeitus.library.entity.book;

import com.epam.ofeitus.library.entity.book.constituent.CopyOfBookStatus;

import java.io.Serializable;
import java.util.Objects;

public class CopyOfBook implements Serializable {
    private int inventoryId;
    private String bookIsbn;
    private CopyOfBookStatus copyOfBookStatus;

    public CopyOfBook() {
    }

    public CopyOfBook(int inventoryId, String bookIsbn, CopyOfBookStatus copyOfBookStatus) {
        this.inventoryId = inventoryId;
        this.bookIsbn = bookIsbn;
        this.copyOfBookStatus = copyOfBookStatus;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
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
        return inventoryId == that.inventoryId && Objects.equals(bookIsbn, that.bookIsbn) && copyOfBookStatus == that.copyOfBookStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, bookIsbn, copyOfBookStatus);
    }

    @Override
    public String toString() {
        return "CopyOfBook{" +
                "inventoryId=" + inventoryId +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", copyOfBookStatus=" + copyOfBookStatus +
                '}';
    }
}
