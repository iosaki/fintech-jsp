package br.com.fiap.controller;

import br.com.fiap.dao.UserDao;
import br.com.fiap.model.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.UUID;

@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {

    private Connection connection;
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = new UserDao();
        System.out.println("ForgotPasswordServlet iniciado");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/pages/user/forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email é obrigatório.");
            return;
        }

        try {
            UserDao userDao = new UserDao();
            User user = userDao.findByEmail(email);

            if (user == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuário não encontrado.");
                return;
            }

            String resetToken = UUID.randomUUID().toString();
            String resetLink = "http://localhost:8080/resetPassword?token=" + resetToken;

            System.out.println("Link para redefinir a senha: " + resetLink);

            response.getWriter().write("Um link para redefinir sua senha foi enviado para " + email);

        } catch (SQLException e) {
            log("Erro ao processar a solicitação de redefinição de senha: " + e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar a solicitação.");
        } catch (Exception e) {
            log("Erro inesperado: " + e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro inesperado ao processar a solicitação.");
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            log("Erro ao fechar a conexão com o banco de dados: " + e.getMessage(), e);
        }
    }
}


