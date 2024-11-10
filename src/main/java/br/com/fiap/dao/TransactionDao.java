package br.com.fiap.dao;

import br.com.fiap.exception.DBException;
import br.com.fiap.factory.ConnectionManager;
import br.com.fiap.model.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    private Connection connection;

    // Metodo para adicionar uma nova transação
    public void add(Transaction transaction) throws SQLException, DBException {
        String sql = "INSERT INTO transactions (id, bankAccount_id, value, type, transaction_date, created_at, balance) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, transaction.getId());  // Definindo o ID manualmente
            stm.setInt(2, transaction.getBankAccountId());
            stm.setInt(3, transaction.getValue());
            stm.setString(4, transaction.getType());
            stm.setTimestamp(5, transaction.getTransactionDate());
            stm.setTimestamp(6, transaction.getCreatedAt());
            stm.setInt(7, transaction.getBalance());  // Incluindo o saldo

            stm.executeUpdate();
            System.out.println("Transação cadastrada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar transação.");
        }
    }

    // Metodo para listar todas as transações
    public List<Transaction> findAll() throws SQLException, DBException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        System.out.println("Iniciando a busca de transações no banco de dados.");

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("id");
                int bankAccountId = result.getInt("bankAccount_id");
                int value = result.getInt("value");
                String type = result.getString("type");
                Timestamp transactionDate = result.getTimestamp("transaction_date");
                Timestamp createdAt = result.getTimestamp("created_at");
                int balance = result.getInt("balance");  // Recuperando o saldo

                // Adicionando a transação com o saldo
                transactions.add(new Transaction(id, bankAccountId, value, type, transactionDate, createdAt, balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao listar transações.");
        }
        return transactions;
    }

    public void clear() throws SQLException {
        String sql = "DELETE FROM transactions";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.executeUpdate();
            System.out.println("Tabela de transações esvaziada.");
        }
    }

    // Metodo para fechar a conexão
    public void closeConnection() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

    public List<Transaction> findAllByUserEmail(String userEmail) throws DBException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT t.* FROM transactions t " +
                "JOIN bankaccounts b ON t.bankaccount_id = b.id " +
                "WHERE b.user_email = ?";

        System.out.println("Iniciando a busca de transações para o usuário: " + userEmail);

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            // Define o parâmetro para o user_email
            stm.setString(1, userEmail);

            try (ResultSet result = stm.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    int bankAccountId = result.getInt("bankAccount_id");
                    int value = result.getInt("value");
                    String type = result.getString("type");
                    Timestamp transactionDate = result.getTimestamp("transaction_date");
                    Timestamp createdAt = result.getTimestamp("created_at");
                    int balance = result.getInt("balance");

                    // Adicione a instância de Transaction à lista
                    transactions.add(new Transaction(id, bankAccountId, value, type, transactionDate, createdAt, balance));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao listar transações do usuário.", e);
        }

        System.out.println("Total de transações encontradas: " + transactions.size());
        return transactions;
    }
}
