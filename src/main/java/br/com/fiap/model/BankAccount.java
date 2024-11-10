package br.com.fiap.model;

import java.sql.Timestamp;

public class BankAccount {
    private int id;
    private String name;
    private int bankId;
    private String userEmail;
    private Timestamp createdAt;
    private String logoUrl;
    private String bankName;
    private double saldo; // Novo campo

    // Atualizar construtores e getters/setters
    public BankAccount(int id, String name, int bankId, String userEmail, Timestamp createdAt, String logoUrl, String bankName, Double saldo) {
        this.id = id;
        this.name = name;
        this.bankId = bankId;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
        this.logoUrl = logoUrl;
        this.bankName = bankName;
        this.saldo = 0.0;

    }

    // Construtor sem logoUrl (caso não precise dela)
    public BankAccount(int id, String name, int bankId, String userEmail, Timestamp createdAt) {
        this(id, name, bankId, userEmail, createdAt, null, null, null);
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}