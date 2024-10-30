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
        String sql = "INSERT INTO bankaccounts ( id, name, bank_id, user_email, created_at, banks_id) VALUES (SEQ_BANKACCOUNT_ID.NEXTVAL, ?, ?, ?, ?, ?)";

        // Usando try-with-resources para garantir o fechamento de connection e statement
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, bankAccount.getName());
            stm.setInt(2, bankAccount.getBankId());
            stm.setString(3, bankAccount.getUserEmail());
            stm.setTimestamp(4, bankAccount.getCreatedAt());
            stm.setInt(5, bankAccount.getBanksId());

            stm.executeUpdate();
            System.out.println("Conta bancária cadastrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar conta bancária.");
        }
    }

    // Metodo para listar todas as contas bancárias
    public List<BankAccount> findAll() throws DBException {

        List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
        String sql = "SELECT * FROM bankaccounts";

        try (   Connection connection = ConnectionManager.getConnection();
                PreparedStatement stm = connection.prepareStatement(sql)) {
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
            e.printStackTrace();
            throw new DBException("Erro ao listas contas.");
        }
        return bankAccounts;
    }

    public List<BankAccount> findAllWithBankLogo() throws SQLException {
        List<BankAccount> bankAccounts = new ArrayList<>();
        String sql = "SELECT ba.id, ba.name, ba.bank_id, ba.user_email, ba.created_at, ba.banks_id, b.logo_url " +
                "FROM bankaccounts ba " +
                "JOIN banks b ON ba.bank_id = b.id";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BankAccount bankAccount = new BankAccount(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("bank_id"),
                        rs.getString("user_email"),
                        rs.getTimestamp("created_at"),
                        rs.getInt("banks_id")
                );
                bankAccount.setLogoUrl(rs.getString("logo_url"));
                bankAccounts.add(bankAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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