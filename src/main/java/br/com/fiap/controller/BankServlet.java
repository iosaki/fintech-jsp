package br.com.fiap.controller;

import br.com.fiap.dao.BankDao;
import br.com.fiap.model.Bank;
import br.com.fiap.exception.DBException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;
import java.io.IOException;

@WebServlet("/bank")
public class BankServlet extends HttpServlet {

    private BankDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new BankDao();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Bank> banks = dao.findAll();
            request.setAttribute("banks", banks);
        } catch (DBException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar bancos.");
        }
        request.getRequestDispatcher("/views/pages/wallet/bankAccount/new.jsp").forward(request, response);
    }

}
