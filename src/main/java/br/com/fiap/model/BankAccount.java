package br.com.fiap.model;

import java.sql.Timestamp;

public class BankAccount {
    private int id;
    private String name;
    private int bankId;
    private String userEmail;
    private Timestamp createdAt;
    private int banksId;

    // Construtor completo
    public BankAccount(int id, String name, int bankId, String userEmail, Timestamp createdAt, int banksId) {
        this.id = id;
        this.name = name;
        this.bankId = bankId;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
        this.banksId = banksId;
    }

    // Getters e setters
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

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getBanksId() {
        return banksId;
    }

    public void setBanksId(int banksId) {
        this.banksId = banksId;
    }

    private String logoUrl;

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}