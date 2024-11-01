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
        System.out.println("BankAccountServlet iniciado");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String name = request.getParameter("name");
            int bankId = Integer.parseInt(request.getParameter("bankId"));
            String userEmail = request.getParameter("userEmail");
            int banksId = Integer.parseInt(request.getParameter("banksId"));

            BankAccount bankAccount = new BankAccount(0, name, bankId, userEmail, new Timestamp(System.currentTimeMillis()), banksId);

            dao.add(bankAccount);
            request.setAttribute("mensagem", "Conta bancária cadastrada!");

        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar conta bancária");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor valide os dados.");
        }
        request.getRequestDispatcher("/").forward(request, response);
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        System.out.println("Chamando o metodo doGet em BankAccountServlet"); // Verifique se o doGet está sendo executado
//        try {
//            List<BankAccount> bankAccounts = dao.findAll();
//            request.setAttribute("bankAccounts", bankAccounts);
//            System.out.println("Total de contas no servlet: " + bankAccounts.size());
//        } catch (DBException e) {
//            e.printStackTrace();
//        }
//        request.getRequestDispatcher("/views/pages/wallet/list.jsp").forward(request, response);
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<BankAccount> bankAccounts = dao.findAllWithLogo();
            request.setAttribute("bankAccounts", bankAccounts);
        } catch (DBException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/pages/wallet/list.jsp").forward(request, response);
    }

}
