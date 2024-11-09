package br.com.fiap.dao.impl;

import br.com.fiap.dao.UserValidateDao;
import br.com.fiap.factory.ConnectionManager;
import br.com.fiap.dao.UserDao;
import br.com.fiap.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleUserDao implements UserValidateDao {

    private Connection connection;

    @Override
    public boolean validateUser(User user) {
        System.out.println("Iniciando validateUser");

        String sql = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, user.getEmail());
            stm.setString(2, user.getPassword());

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}