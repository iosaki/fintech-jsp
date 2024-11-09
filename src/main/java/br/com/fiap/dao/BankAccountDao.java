package br.com.fiap.dao;

import br.com.fiap.exception.DBException;
import br.com.fiap.model.BankAccount;
import br.com.fiap.factory.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDao {

    private Connection connection;

    // Metodo para adicionar uma nova conta bancária
    public void add(BankAccount bankAccount) throws DBException {
        String sql = "INSERT INTO bankaccounts ( id, name, bank_id, user_email, created_at) VALUES (SEQ_BANKACCOUNT_ID.NEXTVAL, ?, ?, ?, ?)";

        // Usando try-with-resources para garantir o fechamento de connection e statement
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, bankAccount.getName());
            stm.setInt(2, bankAccount.getBankId());
            stm.setString(3, bankAccount.getUserEmail());
            stm.setTimestamp(4, bankAccount.getCreatedAt());

            stm.executeUpdate();
            System.out.println("Conta bancária cadastrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar conta bancária.");
        }
    }

    // Metodo para listar todas as contas bancárias
    public List<BankAccount> findAll(String userEmail) throws DBException {
        List<BankAccount> bankAccounts = new ArrayList<>();
        String sql = "SELECT * FROM bankaccounts WHERE user_email = ?";
        System.out.println("Iniciando a busca de contas bancárias no banco de dados para o usuário: " + userEmail);

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            // Definindo o valor do parâmetro para a query
            stm.setString(1, userEmail);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    // Adiciona dados conforme necessário
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int bankId = rs.getInt("bank_id");
                    String email = rs.getString("user_email");
                    Timestamp createdAt = rs.getTimestamp("created_at");

                    bankAccounts.add(new BankAccount(id, name, bankId, email, createdAt));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao listar contas.");
        }
        System.out.println("Total de contas encontradas: " + bankAccounts.size());
        return bankAccounts;
    }

    public List<BankAccount> findAllWithLogo(String userEmail) throws DBException {
        List<BankAccount> bankAccounts = new ArrayList<>();
        String sql = "SELECT ba.*, b.logo_url, b.name AS bank_name " +
                "FROM bankaccounts ba " +
                "JOIN banks b ON ba.bank_id = b.id " +
                "WHERE ba.user_email = ?";

        System.out.println("Iniciando a busca de contas bancárias com logos para o usuário: " + userEmail);

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            // Define o parâmetro para o user_email
            stm.setString(1, userEmail);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int bankId = rs.getInt("bank_id");
                    Timestamp createdAt = rs.getTimestamp("created_at");
                    String logoUrl = rs.getString("logo_url");
                    String bankName = rs.getString("bank_name");

                    // Adicione a instância de BankAccount à lista
                    bankAccounts.add(new BankAccount(id, name, bankId, userEmail, createdAt, logoUrl, bankName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao listar contas com logos.");
        }

        System.out.println("Total de contas encontradas: " + bankAccounts.size());
        return bankAccounts;
    }

    public void update(BankAccount bankAccount) throws DBException {
        String sql = "UPDATE bankaccounts SET name = ?, bank_id = ? WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, bankAccount.getName());
            stm.setInt(2, bankAccount.getBankId());
            stm.setInt(3, bankAccount.getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar a conta bancária.");
        }
    }

    public BankAccount findById(int id) throws DBException {
        String sql = "SELECT * FROM bankaccounts WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    // Crie o objeto BankAccount com os dados retornados
                    int bankId = rs.getInt("bank_id");
                    String name = rs.getString("name");
                    String userEmail = rs.getString("user_email");
                    Timestamp createdAt = rs.getTimestamp("created_at");

                    // Retorna a instância de BankAccount preenchida
                    return new BankAccount(id, name, bankId, userEmail, createdAt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao buscar conta bancária com ID: " + id);
        }
        // Retorna null se não encontrar nenhum registro
        return null;
    }
}