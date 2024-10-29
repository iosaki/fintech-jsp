package br.com.fiap.model;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int bankAccountId;
    private int value;
    private String type;
    private Timestamp transactionDate;
    private Timestamp createdAt;

    // Construtor completo
    public Transaction(int id, int bankAccountId, int value, String type, Timestamp transactionDate, Timestamp createdAt) {
        this.id = id;
        this.bankAccountId = bankAccountId;
        this.value = value;
        this.type = type;
        this.transactionDate = transactionDate;
        this.createdAt = createdAt;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}