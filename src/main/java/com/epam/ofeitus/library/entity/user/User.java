package com.epam.ofeitus.library.entity.user;

import com.epam.ofeitus.library.entity.user.constituent.UserRole;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int userId;
    private String name;
    private String surName;
    private String email;
    private String passwordHash;
    private UserRole userRole;

    public User() {
    }

    public User(int userId, String name, String surName, String email, String passwordHash, UserRole userRole) {
        this.userId = userId;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && userRole == user.userRole && Objects.equals(name, user.name) && Objects.equals(surName, user.surName) && Objects.equals(email, user.email) && Objects.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, surName, email, passwordHash, userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + name + '\'' +
                ", secondName='" + surName + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", userRoleId=" + userRole +
                '}';
    }
}
