package com.ndungutse.documenting_project;

import java.util.Objects;

/**
 * Model class representing a user with username and password.
 */
public class UserModel {
    private String username;
    private String password;

    /**
     * Default constructor
     */
    public UserModel() {
    }

    /**
     * Parameterized constructor
     * 
     * @param username the username of the user
     * @param password the password of the user
     */
    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get the username of the user
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password of the user
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(username, userModel.username) &&
                Objects.equals(password, userModel.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}