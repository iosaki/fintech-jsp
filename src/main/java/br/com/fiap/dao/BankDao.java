package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDao {

    private Connection connection;

    public BankDao() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void add(Bank bank) throws SQLException {
        String sql = "INSERT INTO banks (id, name, created_at) VALUES (?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, bank.getId());
            stm.setString(2, bank.getName());
            stm.setTimestamp(3, bank.getCreatedAt());

            stm.executeUpdate();
            System.out.println("Banco cadastrado com sucesso!");
        }
    }

    public List<Bank> findAll() throws SQLException {
        List<Bank> banks = new ArrayList<>();
        String sql = "SELECT * FROM banks";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Timestamp createdAt = rs.getTimestamp("created_at");

                banks.add(new Bank(id, name, createdAt));
            }
        }
        return banks;
    }

    public void clear() throws SQLException {
        String sql = "DELETE FROM banks";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.executeUpdate();
            System.out.println("Tabela de bancos esvaziada.");
        }
    }

    // Metodo para fechar a conexão
    public void closeConnection() throws SQLException {
        try {
            connection.close();
            System.out.println("Conexão fechada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }


}