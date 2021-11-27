package com.epam.ofeitus.library.entity.book;

import java.util.Objects;

public class CopyOfBook extends Book {
    private int inventoryId;

    public CopyOfBook() {
    }

    public CopyOfBook(String isbn, String title, int publicationYear, int categoryId, String language, int inventoryId) {
        super(isbn, title, publicationYear, categoryId, language);
        this.inventoryId = inventoryId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CopyOfBook that = (CopyOfBook) o;
        return inventoryId == that.inventoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), inventoryId);
    }

    @Override
    public String toString() {
        return "CopyOfBook{" +
                "inventoryId=" + inventoryId +
                "isbn='" + this.getIsbn() + '\'' +
                ", title='" + this.getTitle() + '\'' +
                ", publicationYear=" + this.getPublicationYear() +
                ", categoryId=" + this.getCategoryId() +
                ", language='" + this.getLanguage() + '\'' +
                '}';
    }
}
