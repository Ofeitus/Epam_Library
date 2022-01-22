package com.epam.ofeitus.library.entity.user;

import com.epam.ofeitus.library.entity.user.constituent.UserRole;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * User bean class.
 */
public class User implements Serializable {
    private int userId;
    private Date registrationDate;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String passwordHash;
    private UserRole userRole;
    private boolean deleted;

    public User() {
    }

    public User(int userId, Date registrationDate, String name, String surname, String phoneNumber, String email, String passwordHash, UserRole userRole, boolean deleted) {
        this.userId = userId;
        this.registrationDate = registrationDate;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userRole = userRole;
        this.deleted = deleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && deleted == user.deleted && Objects.equals(registrationDate, user.registrationDate) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(passwordHash, user.passwordHash) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, registrationDate, name, surname, phoneNumber, email, passwordHash, userRole, deleted);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", registrationDate=" + registrationDate +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", userRole=" + userRole +
                ", deleted=" + deleted +
                '}';
    }
}
