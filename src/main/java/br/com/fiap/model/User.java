package br.com.fiap.model;

import java.sql.Timestamp;

public class User {
    private String email;
    private String documentNumber;
    private String name;
    private String surname;
    private String phoneNumber;
    private Timestamp createdAt;
    private String password;
    private String profilePictureUrl;

    public User() {
    }

    public User(String email, String documentNumber, String name, String surname, String phoneNumber, Timestamp createdAt, String password, String profilePictureUrl) {
        this.email = email;
        this.documentNumber = documentNumber;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
    }

    // Getters e setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
