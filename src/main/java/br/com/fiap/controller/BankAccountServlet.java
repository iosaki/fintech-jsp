package br.com.fiap.controller;

import br.com.fiap.dao.BankAccountDao;
import br.com.fiap.dao.BankDao;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.Bank;
import br.com.fiap.model.BankAccount;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.io.IOException;


@WebServlet("/bankaccount")
public class BankAccountServlet extends HttpServlet {

    private BankAccountDao dao;
    private BankDao bankDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new BankAccountDao();
        bankDao = new BankDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            boolean isEditing = (idParam != null && !idParam.isEmpty());

            if (isEditing) {
                // Lógica de edição
                int id = Integer.parseInt(idParam);
                String name = request.getParameter("name");
                int bankId = Integer.parseInt(request.getParameter("bankId"));
                String userEmail = request.getParameter("userEmail");

                BankAccount bankAccount = new BankAccount(id, name, bankId, userEmail, new Timestamp(System.currentTimeMillis()));
                dao.update(bankAccount);

                // Redireciona para a URL de listagem após a edição
                response.sendRedirect(request.getContextPath() + "/bankaccount");
                return;
            } else {
                // Lógica de criação
                String name = request.getParameter("name");
                int bankId = Integer.parseInt(request.getParameter("bankId"));
                String userEmail = request.getParameter("userEmail");

                BankAccount bankAccount = new BankAccount(0, name, bankId, userEmail, new Timestamp(System.currentTimeMillis()));
                dao.add(bankAccount);

                // Redireciona para a URL de listagem após a criação
                response.sendRedirect(request.getContextPath() + "/bankaccount");
                return;
            }
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao processar a conta bancária.");
            request.getRequestDispatcher("/views/pages/wallet/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Dados inválidos. Por favor, revise os campos.");
            request.getRequestDispatcher("/views/pages/wallet/list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        System.out.println("idParam" + idParam);

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                BankAccount bankAccount = dao.findById(id);

                if (bankAccount != null) {
                    request.setAttribute("bankAccount", bankAccount);

                    // Carrega a lista de bancos e passa para a página JSP
                    List<Bank> banks = bankDao.findAll();
                    request.setAttribute("banks", banks);

                    request.getRequestDispatcher("/views/pages/wallet/bankAccount/edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/notFound.jsp");
                }
            } catch (DBException | NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("/errorPage.jsp");
            }
        } else {
            try {
                HttpSession session = request.getSession();
                String userEmail = (String) session.getAttribute("user");

                if (userEmail != null && !userEmail.isEmpty()) {
                    List<BankAccount> bankAccounts = dao.findAllWithLogo(userEmail);
                    request.setAttribute("bankAccounts", bankAccounts);
                } else {
                    response.sendRedirect("/login");
                    return;
                }
            } catch (DBException e) {
                e.printStackTrace();
                request.setAttribute("error", "Erro ao buscar contas bancárias.");
            }
            request.getRequestDispatcher("/views/pages/wallet/list.jsp").forward(request, response);
        }
    }

}
