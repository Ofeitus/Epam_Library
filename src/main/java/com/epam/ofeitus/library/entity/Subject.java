package com.epam.ofeitus.library.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Book bean class.
 */
public class Subject implements Serializable {
    private int id;
    private String name;
    private int hours;

    public Subject() {
    }

    public Subject(int id, String name, int hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }

    public Subject(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id&& Objects.equals(name, subject.name) && hours == subject.hours ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hours);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                '}';
    }
}