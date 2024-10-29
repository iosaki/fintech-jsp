package br.com.fiap.dao;

import br.com.fiap.model.BankAccount;
import br.com.fiap.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDao {

    private Connection connection;

    public BankAccountDao() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    // Metodo para adicionar uma nova conta bancária
    public void add(BankAccount bankAccount) throws SQLException {
        String sql = "INSERT INTO bankaccounts (id, name, bank_id, user_email, created_at, banks_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, bankAccount.getId());
            stm.setString(2, bankAccount.getName());
            stm.setInt(3, bankAccount.getBankId());
            stm.setString(4, bankAccount.getUserEmail());
            stm.setTimestamp(5, bankAccount.getCreatedAt());
            stm.setInt(6, bankAccount.getBanksId());

            stm.executeUpdate();
            System.out.println("Conta bancária cadastrada com sucesso!");
        }
    }

    // Método para listar todas as contas bancárias
    public List<BankAccount> findAll() throws SQLException {
        List<BankAccount> bankAccounts = new ArrayList<>();
        String sql = "SELECT * FROM bankaccounts";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int bankId = rs.getInt("bank_id");
                String userEmail = rs.getString("user_email");
                Timestamp createdAt = rs.getTimestamp("created_at");
                int banksId = rs.getInt("banks_id");

                bankAccounts.add(new BankAccount(id, name, bankId, userEmail, createdAt, banksId));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contas bancárias: " + e.getMessage());
        }
        return bankAccounts;
    }

    public void clear() throws SQLException {
        String sql = "DELETE FROM bankaccounts";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.executeUpdate();
            System.out.println("Tabela de contas bancárias esvaziada.");
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