package com.epam.ofeitus.library.entity.book;

import java.util.Objects;

public class CopyOfBook {
    private int inventoryId;
    private int bookIsbn;

    public CopyOfBook() {
    }

    public CopyOfBook(int inventoryId, int bookIsbn) {
        this.inventoryId = inventoryId;
        this.bookIsbn = bookIsbn;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(int bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyOfBook that = (CopyOfBook) o;
        return inventoryId == that.inventoryId && bookIsbn == that.bookIsbn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, bookIsbn);
    }

    @Override
    public String toString() {
        return "CopyOfBook{" +
                "inventoryId=" + inventoryId +
                ", bookIsbn=" + bookIsbn +
                '}';
    }
}
