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
        List<BankAccount> bankAccounts = new ArrayList<>();
        String sql = "SELECT * FROM bankaccounts";
        System.out.println("Iniciando a busca de contas bancárias no banco de dados.");

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                // Adiciona dados conforme necessário
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
            throw new DBException("Erro ao listar contas.");
        }
        System.out.println("Total de contas encontradas: " + bankAccounts.size());
        return bankAccounts;
    }

    public List<BankAccount> findAllWithLogo() throws DBException {
        List<BankAccount> bankAccounts = new ArrayList<>();
        String sql = "SELECT ba.*, b.logo_url, b.name as bank_name FROM bankaccounts ba JOIN banks b ON ba.banks_id = b.id";

        System.out.println("Executando findAllWithLogo para buscar contas com logo");

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int bankId = rs.getInt("bank_id");
                String userEmail = rs.getString("user_email");
                Timestamp createdAt = rs.getTimestamp("created_at");
                int banksId = rs.getInt("banks_id");
                String logoUrl = rs.getString("logo_url");
                String bankName = rs.getString("bank_name");

                bankAccounts.add(new BankAccount(id, name, bankId, userEmail, createdAt, banksId, logoUrl, bankName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao listar contas com logos.");
        }

        System.out.println("Total de contas com logos encontradas: " + bankAccounts.size());
        return bankAccounts;
    }

}