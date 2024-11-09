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
        String sql = "INSERT INTO transaction (id, bankAccount_id, value, type, transaction_date, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            stm.setInt(1, transaction.getId());  // Definindo o ID manualmente
            stm.setInt(2, transaction.getBankAccountId());
            stm.setInt(3, transaction.getValue());
            stm.setString(4, transaction.getType());
            stm.setTimestamp(5, transaction.getTransactionDate());
            stm.setTimestamp(6, transaction.getCreatedAt());

            stm.executeUpdate();
            System.out.println("Transação cadastrada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar transação.");
        }
    }

    // Método para listar todas as transações
    public List<Transaction> findAll() throws SQLException, DBException {
        List<Transaction> transaction = new ArrayList<>();
        String sql = "SELECT * FROM transaction";
        System.out.println("Iniciando a busca de transações no banco de dados.");

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("id");
                int bankAccountId = result.getInt("bankAccount_id");
                int value = result.getInt("value");
                String type = result.getString("type");

                // Pegando o Timestamp do banco
                Timestamp transactionDate = result.getTimestamp("transaction_date");

                // Pegando o Timestamp do banco
                Timestamp createdAt = result.getTimestamp("created_at");

                transaction.add(new Transaction(id, bankAccountId, value, type, transactionDate, createdAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao listar transações.");
        }
        return transaction;
    }

    public void clear() throws SQLException {
        String sql = "DELETE FROM transaction";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.executeUpdate();
            System.out.println("Tabela de transações esvaziada.");
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