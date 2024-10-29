package br.com.fiap.model;

public class Card {
    private int id;
    private int bankAccountId;  // camelCase para consistência
    private String name;

    // Construtor completo
    public Card(int id, int bankAccountId, String name) {
        this.id = id;
        this.bankAccountId = bankAccountId;  // Ajuste de nome para consistência
        this.name = name;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBankAccountId() {  // Ajuste de nome para consistência
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {  // Ajuste de nome para consistência
        this.bankAccountId = bankAccountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}