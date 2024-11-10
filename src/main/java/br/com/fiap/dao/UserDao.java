package br.com.fiap.dao;
import br.com.fiap.factory.ConnectionManager;
import br.com.fiap.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

        private Connection connection;

        public void add(User user) throws SQLException {
            String sql = "INSERT INTO USERS (email, document_number, name, surname, phone_number, created_at, profile_picture_url, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, user.getEmail());
                stm.setString(2, user.getDocumentNumber());
                stm.setString(3, user.getName());
                stm.setString(4, user.getSurname());
                stm.setString(5, user.getPhoneNumber());
                stm.setTimestamp(6, user.getCreatedAt());
                stm.setString(7, user.getProfilePictureUrl());
                stm.setString(8, user.getPassword());

                stm.executeUpdate();
                System.out.println("Usuário adicionado com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao adicionar o usuário: " + e.getMessage());
                throw e;
            }
        }

        public List<User> findAll() throws SQLException {
            List<User> users = new ArrayList<>();
            String sql = "SELECT * FROM USERS";

            try (Connection connection = ConnectionManager.getConnection();
                    PreparedStatement stm = connection.prepareStatement(sql)) {
                ResultSet result = stm.executeQuery();

                while (result.next()) {
                    String email = result.getString("EMAIL");
                    String documentNumber = result.getString("DOCUMENT_NUMBER");
                    String name = result.getString("NAME");
                    String surname = result.getString("SURNAME");
                    String phoneNumber = result.getString("PHONE_NUMBER");
                    Timestamp createdAt = result.getTimestamp("CREATED_AT");
                    String profilePictureUrl = result.getString("PROFILE_PICTURE_URL");

                    User user = new User();
                    user.setEmail(email);
                    user.setDocumentNumber(documentNumber);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setPhoneNumber(phoneNumber);
                    user.setCreatedAt(createdAt);
                    user.setProfilePictureUrl(profilePictureUrl);

                    users.add(user);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao listar os usuários: " + e.getMessage());
                throw e;
            }

            return users;
        }


        public void clear() throws SQLException {
            String sql = "DELETE FROM USERS";
            try (Connection connection = ConnectionManager.getConnection();
                    PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.executeUpdate();
                System.out.println("Tabela de usuários esvaziada.");
            } catch (SQLException e) {
                System.err.println("Erro ao limpar a tabela: " + e.getMessage());
                throw e;
            }
        }


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

        public User findByEmail(String email) throws SQLException {
            String sql = "SELECT * FROM USERS WHERE email = ?";
            try (Connection connection = ConnectionManager.getConnection();
                    PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, email);
                ResultSet result = stm.executeQuery();

                if (result.next()) {
                    String userEmail = result.getString("EMAIL");
                    String documentNumber = result.getString("DOCUMENT_NUMBER");
                    String name = result.getString("NAME");
                    String surname = result.getString("SURNAME");
                    String phoneNumber = result.getString("PHONE_NUMBER");
                    Timestamp createdAt = result.getTimestamp("CREATED_AT");
                    String password = result.getString("PASSWORD");
                    String profilePictureUrl = result.getString("PROFILE_PICTURE_URL");

                    User user = new User();
                    user.setEmail(userEmail);
                    user.setDocumentNumber(documentNumber);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setPhoneNumber(phoneNumber);
                    user.setCreatedAt(createdAt);
                    user.setPassword(password);
                    user.setProfilePictureUrl(profilePictureUrl);

                    return user;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                System.err.println("Erro ao procurar o usuário: " + e.getMessage());
                throw e;
            }
        }
    }
