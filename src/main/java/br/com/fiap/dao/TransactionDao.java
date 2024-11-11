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

    public List<Transaction> findAllByUserEmail(String email) throws DBException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT t.* FROM transactions t " +
                "JOIN bankaccounts b ON t.bankAccount_id = b.id " +
                "WHERE b.user_email = ?";

        System.out.println("Iniciando a busca de transações para o usuário: " + email);

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            // Define o parâmetro para o user_email
            stm.setString(1, email);

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

    // Método para listar todas as transações (sem filtro)
    public List<Transaction> findAll() throws SQLException, DBException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";

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
                int balance = result.getInt("balance");

                // Adicionando a transação com o saldo
                transactions.add(new Transaction(id, bankAccountId, value, type, transactionDate, createdAt, balance));
            }
        } catch (SQLException e) {
            System.err.println("Erro no método findAll: " + e.getMessage());
            throw new DBException("Erro ao listar transações.", e);
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

    // Método para fechar a conexão
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

    public double getSumOfTransactionsByBankAccountId(int bankAccountId) throws SQLException {
        double sum = 0;
        String sql = "SELECT SUM(value) FROM transactions WHERE bankaccount_id = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, bankAccountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    sum = rs.getDouble(1);
                }
            }
        }
        return sum;
    }

    // Método para encontrar uma transação por ID
    public Transaction findById(int transactionId) throws SQLException, DBException {
        Transaction transaction = null;
        String sql = "SELECT * FROM transactions WHERE id = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            // Define o parâmetro para o id da transação
            stm.setInt(1, transactionId);

            try (ResultSet result = stm.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("id");
                    int bankAccountId = result.getInt("bankAccount_id");
                    int value = result.getInt("value");
                    String type = result.getString("type");
                    Timestamp transactionDate = result.getTimestamp("transaction_date");
                    Timestamp createdAt = result.getTimestamp("created_at");
                    int balance = result.getInt("balance");

                    transaction = new Transaction(id, bankAccountId, value, type, transactionDate, createdAt, balance);
                }
            }

            if (transaction == null) {
                throw new DBException("Transação não encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao buscar transação por ID.", e);
        }

        return transaction;
    }
}
