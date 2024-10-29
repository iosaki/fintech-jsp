package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDao {

    private Connection connection;

    public CardDao() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    // Método para adicionar um novo cartão
    public void add(Card card) throws SQLException {
        String sql = "INSERT INTO cards (id, bankAccount_id, name) VALUES (?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, card.getId());
            stm.setInt(2, card.getBankAccountId());
            stm.setString(3, card.getName());

            stm.executeUpdate();
            System.out.println("Cartão cadastrado com sucesso!");
        }
    }

    // Método para listar todos os cartões
    public List<Card> findAll() throws SQLException {
        List<Card> cards = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM cards");
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                int bankAccountId = result.getInt("bankAccount_id");  // Ajuste de nome para camelCase
                String name = result.getString("name");
                cards.add(new Card(id, bankAccountId, name));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar cartões: " + e.getMessage());
        }
        return cards;
    }

    public void clear() throws SQLException {
        String sql = "DELETE FROM cards";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.executeUpdate();
            System.out.println("Tabela de cartões esvaziada.");
        }
    }

    // Método para fechar a conexão
    public void closeConnection() throws SQLException {
        try {
            connection.close();
            System.out.println("Conexão fechada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}