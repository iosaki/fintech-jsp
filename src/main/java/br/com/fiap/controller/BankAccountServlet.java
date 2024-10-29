package br.com.fiap.controller;

import br.com.fiap.dao.BankAccountDao;
import br.com.fiap.model.BankAccount;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/bankAccounts")
public class BankAccountServlet extends HttpServlet {

    private BankAccountDao bankAccountDao;

    @Override
    public void init() throws ServletException {
        try {
            bankAccountDao = new BankAccountDao();
        } catch (SQLException e) {
            throw new ServletException("Erro ao inicializar o BankAccountDao", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<BankAccount> bankAccounts = bankAccountDao.findAll();
            request.setAttribute("bankAccounts", bankAccounts);
            request.getRequestDispatcher("bankAccounts.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar contas bancárias", e);
        }
    }

    @Override
    public void destroy() {
        try {
            if (bankAccountDao != null) {
                bankAccountDao.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}