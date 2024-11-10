package br.com.fiap.controller;

import br.com.fiap.dao.BankAccountDao;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.BankAccount;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.io.IOException;


@WebServlet("/bankaccount")
public class BankAccountServlet extends HttpServlet {

    private BankAccountDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new BankAccountDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String name = request.getParameter("name");
            int bankId = Integer.parseInt(request.getParameter("bankId"));
            String userEmail = request.getParameter("userEmail");

            BankAccount bankAccount = new BankAccount(0, name, bankId, userEmail, new Timestamp(System.currentTimeMillis()));

            dao.add(bankAccount);
            request.setAttribute("mensagem", "Conta bancária cadastrada!");

        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar conta bancária");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor valide os dados.");
        }
        request.getRequestDispatcher("/views/pages/wallet/list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String user = (String) request.getSession().getAttribute("user");
            List<BankAccount> bankAccounts = dao.findAllByUserEmailWithLogo(user);
            request.setAttribute("bankAccounts", bankAccounts);
        } catch (DBException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao listar contas bancárias.");
        }
        request.getRequestDispatcher("/views/pages/wallet/list.jsp").forward(request, response);
    }

}
