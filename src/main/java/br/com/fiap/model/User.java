package br.com.fiap.model;
import br.com.fiap.util.EncryptionUtils;

import java.sql.Timestamp;

public class User {

    private String email;
    private String documentNumber;
    private String name;
    private String surname;
    private String phoneNumber;
    private String password;
    private Timestamp createdAt;
    private String profilePictureUrl;

    public User() {
    }

    public User(String email, String documentNumber, String name, String surname, String phoneNumber, Timestamp createdAt, String password, String profilePictureUrl) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.documentNumber = documentNumber;
        setPassword(password);
        this.createdAt = createdAt;
        this.profilePictureUrl = profilePictureUrl;
    }


    public User(String email, String password) {
        super();
        this.email = email;
        setPassword(password);
    }

    // Getters e Setters

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        try {
            this.password = EncryptionUtils.encrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
