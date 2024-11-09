package br.com.fiap.controller;

import br.com.fiap.dao.BankAccountDao;
import br.com.fiap.dao.TransactionDao;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.BankAccount;
import br.com.fiap.model.Transaction;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.io.IOException;
import java.util.List;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {

    private TransactionDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new TransactionDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int bankAccountId = Integer.parseInt(request.getParameter("bankAccountId"));
            int value = Integer.parseInt(request.getParameter("value"));
            String type = request.getParameter("type");

            // `created_at` com data e hora atuais
            Timestamp createdAt = new Timestamp(System.currentTimeMillis());

            // `transaction_date` com data e hora definida pelo usuário
            Timestamp transactionDate = null;
            String transactionDateParam = request.getParameter("transaction_date");
            if (transactionDateParam != null && !transactionDateParam.isEmpty()) {
                transactionDate = Timestamp.valueOf(transactionDateParam);
            }

            // Criando a nova transação
            Transaction transaction = new Transaction(0, bankAccountId, value, type, transactionDate, createdAt);

            dao.add(transaction);
            request.setAttribute("mensagem", "Transação cadastrada com sucesso!");

        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar transação.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados.");
        }
        request.getRequestDispatcher("/views/pages/transactions/transactions_list.jsp").forward(request, response);
    }


//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if ("listTransactions".equals(action)) {
//            // Código para transactions_list.jsp
//            try {
//                List<Transaction> transactions = dao.findAll();
////                double accountBalance = dao.getAccountBalance(); // Método fictício para obter o saldo
//                request.setAttribute("transactions", transactions);
////                request.setAttribute("accountBalance", accountBalance);
//            } catch (DBException e) {
//                e.printStackTrace();
//                request.setAttribute("erro", "Erro ao listar transações.");
//            } catch (SQLException e) {
//                e.printStackTrace();
//                request.setAttribute("erro", "Erro inesperado.");
//            }
//
//            request.getRequestDispatcher("/views/pages/transactions/transactions_list.jsp").forward(request, response);
//
//        } else if ("newTransaction".equals(action)) {
//            // Código para transaction_new.jsp
//            try {
//                BankAccountDao bankAccountDao = new BankAccountDao();
//                List<BankAccount> bankAccounts = bankAccountDao.findAll();
//                request.setAttribute("bankAccounts", bankAccounts);
//            } catch (DBException e) {
//                e.printStackTrace();
//                request.setAttribute("erro", "Erro ao carregar contas bancárias.");
//            }
//
//            request.getRequestDispatcher("/views/pages/transactions/transaction/transaction_new.jsp").forward(request, response);
//        }
//    }


}

