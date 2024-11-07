package br.com.fiap.model;
import br.com.fiap.util.EncryptionUtils;

import java.sql.Timestamp;

public class User {

    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private int documentNumber;
    private String password;
    private Timestamp createdAt;

    public User(String email, String name, String surname, String phoneNumber, int documentNumber, String password, Timestamp createdAt) {
        super();
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.documentNumber = documentNumber;
        setPassword(password);
        this.createdAt = createdAt;
    }

    public User() {
        super();
    }

    public User(String email, String password) {
        super();
        this.email = email;
        setPassword(password);
    }


    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public int getDocumentNumber() {return documentNumber;}

    public void setDocumentNumber(int documentNumber) {this.documentNumber = documentNumber;}

    public String getPassword() {return password;}

    public void setPassword(String password) {
        try {
            this.password = EncryptionUtils.encrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
