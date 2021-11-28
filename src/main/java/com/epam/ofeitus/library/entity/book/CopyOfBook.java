package com.epam.ofeitus.library.entity.book;

import java.util.Objects;

public class CopyOfBook {
    private int inventoryId;
    private String bookIsbn;

    public CopyOfBook() {
    }

    public CopyOfBook(int inventoryId, String bookIsbn) {
        this.inventoryId = inventoryId;
        this.bookIsbn = bookIsbn;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyOfBook that = (CopyOfBook) o;
        return inventoryId == that.inventoryId && Objects.equals(bookIsbn, that.bookIsbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, bookIsbn);
    }

    @Override
    public String toString() {
        return "CopyOfBook{" +
                "inventoryId=" + inventoryId +
                ", bookIsbn='" + bookIsbn + '\'' +
                '}';
    }
}
