package com.epam.ofeitus.library.entity.book;

import java.io.Serializable;
import java.util.Objects;

public class Author implements Serializable {
    private int authorId;
    private String name;
    private String surName;

    public Author() {
    }

    public Author(int authorId, String name, String surName) {
        this.authorId = authorId;
        this.name = name;
        this.surName = surName;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && Objects.equals(name, author.name) && Objects.equals(surName, author.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, name, surName);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}
