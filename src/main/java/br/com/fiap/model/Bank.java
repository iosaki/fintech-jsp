package br.com.fiap.model;
import java.sql.Timestamp;

public class Bank {
    private int id;
    private String name;
    private Timestamp createdAt;
    private String logoUrl;

    // Construtores
    public Bank(int id, String name, Timestamp createdAt, String logoUrl) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.logoUrl = logoUrl;
    }

    public Bank() {
    }

    // Getters e Setters
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

    public String getLogoUrl() { return logoUrl; }

    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}