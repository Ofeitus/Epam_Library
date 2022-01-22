package com.epam.ofeitus.library.entity.book;

import java.io.Serializable;
import java.util.Objects;

/**
 * Book bean class.
 */
public class Book implements Serializable {
    private String isbn;
    private String title;
    private int publicationYear;
    private int categoryId;
    private String language;
    private String keyWords;

    public Book() {
    }

    public Book(String isbn, String title, int publicationYear, int categoryId, String language, String keyWords) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.categoryId = categoryId;
        this.language = language;
        this.keyWords = keyWords;
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

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publicationYear == book.publicationYear && categoryId == book.categoryId && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(language, book.language) && Objects.equals(keyWords, book.keyWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, publicationYear, categoryId, language, keyWords);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", categoryId=" + categoryId +
                ", language='" + language + '\'' +
                ", keyWords='" + keyWords + '\'' +
                '}';
    }
}