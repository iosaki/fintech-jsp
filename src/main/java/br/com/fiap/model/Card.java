package br.com.fiap.model;

public class Card {
    private int id;
    private int bankAccountId;  // camelCase para consistência
    private String name;
    private String cardIssuer;

    // Construtor completo
    public Card(int id, int bankAccountId, String name, String cardIssuer) {
        this.id = id;
        this.bankAccountId = bankAccountId;
        this.name = name;
        this.cardIssuer = cardIssuer;
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

    public String getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(String cardIssuer) {
        this.cardIssuer = cardIssuer;
    }
}