package com.epam.ofeitus.library.entity.book;

import com.epam.ofeitus.library.entity.book.constituents.BookCategory;

import java.util.Objects;

public class Book {
    private String isbn;
    private Author author;
    private String title;
    private int publicationYear;
    private BookCategory category;
    private String language;

    public Book() {
    }

    public Book(String isbn, Author author, String title, int publicationYear, BookCategory category, String language) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
        this.category = category;
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
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
        return publicationYear == book.publicationYear && Objects.equals(isbn, book.isbn) && Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(category, book.category) && Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, author, title, publicationYear, category, language);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", category=" + category +
                ", language='" + language + '\'' +
                '}';
    }
}