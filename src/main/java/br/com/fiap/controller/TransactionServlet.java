package br.com.fiap.controller;

import br.com.fiap.dao.BankAccountDao;
import br.com.fiap.dao.TransactionDao;
import br.com.fiap.exception.DBException;
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

    private TransactionDao transactionDao;
    private BankAccountDao bankAccountDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        transactionDao = new TransactionDao();
        bankAccountDao = new BankAccountDao();
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

            // Calcula o saldo da transação com base no tipo
            int transactionBalance = type.equalsIgnoreCase("deposit") ? value : -value;

            // Cria uma nova transação com o saldo calculado
            Transaction transaction = new Transaction(0, bankAccountId, value, type, transactionDate, createdAt, transactionBalance);

            transactionDao.add(transaction);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("listTransactions".equals(action)) {
            try {
                // Obtém o email do usuário logado da sessão
                String user = (String) request.getSession().getAttribute("user");
                System.out.println("Iniciando o doGet com usuario" + user);

                // Filtra as transações com base no email do usuário
                List<Transaction> transactions = transactionDao.findAllByUserEmail(user);
                request.setAttribute("transactions", transactions);
            } catch (DBException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao listar transações.");
            }

            request.getRequestDispatcher("/views/pages/transactions/transactions_list.jsp").forward(request, response);

        } else if ("newTransaction".equals(action)) {
            request.getRequestDispatcher("/views/pages/transactions/transaction/transaction_new.jsp").forward(request, response);
        }
    }

    // Método auxiliar para obter o saldo atual da conta bancária
    private int getAccountBalance(int bankAccountId) throws SQLException, DBException {
        List<Transaction> transactions = transactionDao.findAll();
        int balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getBankAccountId() == bankAccountId) {
                balance = transaction.getBalance(); // Obtém o saldo da última transação
            }
        }
        return balance;
    }
}
