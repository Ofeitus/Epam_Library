package com.epam.ofeitus.library.entity.book;

import com.epam.ofeitus.library.entity.book.constituents.BookCategory;

import java.util.Objects;

public class CopyOfBook extends Book {
    private int inventoryId;

    public CopyOfBook() {
    }

    public CopyOfBook(String isbn, Author author, String title, int publicationYear, BookCategory category, String language, int inventoryId) {
        super(isbn, author, title, publicationYear, category, language);
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
                "inventoryId='" + inventoryId + '\'' +
                ", isbn='" + this.getIsbn() + '\'' +
                ", author=" + this.getAuthor() +
                ", title='" + this.getTitle() + '\'' +
                ", publicationYear=" + this.getPublicationYear() +
                ", category=" + this.getCategory() +
                ", language='" + this.getLanguage() + '\'' +
                '}';
    }
}
