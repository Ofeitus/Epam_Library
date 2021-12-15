package com.epam.ofeitus.library.entity.dto;

import com.epam.ofeitus.library.entity.book.Author;

import java.util.List;
import java.util.Objects;

public class BookDto {
    private String isbn;
    private String title;
    private List<Author> authors;
    private int publicationYear;
    private String category;
    private String language;

    public BookDto() {
    }

    public BookDto(String isbn, String title, List<Author> authors, int publicationYear, String category, String language) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
        BookDto bookDto = (BookDto) o;
        return publicationYear == bookDto.publicationYear && Objects.equals(isbn, bookDto.isbn) && Objects.equals(title, bookDto.title) && Objects.equals(authors, bookDto.authors) && Objects.equals(category, bookDto.category) && Objects.equals(language, bookDto.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, authors, publicationYear, category, language);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publicationYear=" + publicationYear +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
