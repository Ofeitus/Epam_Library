package com.epam.ofeitus.library.entity.book;

import java.io.Serializable;
import java.util.Objects;

public class Author implements Serializable {
    private int authorId;
    private String name;
    private String surname;

    public Author() {
    }

    public Author(int authorId, String name, String surname) {
        this.authorId = authorId;
        this.name = name;
        this.surname = surname;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && Objects.equals(name, author.name) && Objects.equals(surname, author.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, name, surname);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", surName='" + surname + '\'' +
                '}';
    }
}
