package com.epam.ofeitus.library.entity.book;

import java.util.Objects;

public class Book {
    private int inventoryId;
    private String isbn;
    private String title;
    private int publicationYear;
    private int categoryId;
    private String language;

    public Book(int inventoryId, String isbn, String title, int publicationYear, int categoryId, String language) {
        this.inventoryId = inventoryId;
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.categoryId = categoryId;
        this.language = language;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return inventoryId == book.inventoryId && publicationYear == book.publicationYear && categoryId == book.categoryId && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, isbn, title, publicationYear, categoryId, language);
    }
}

