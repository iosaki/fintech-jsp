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
import java.text.SimpleDateFormat;
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
            String action = request.getParameter("action");

            if ("newTransaction".equals(action)) {
                // Lógica de criação de nova transação
                int bankAccountId = Integer.parseInt(request.getParameter("bankAccountId"));
                int value = Integer.parseInt(request.getParameter("value"));
                String type = request.getParameter("type");

                Timestamp createdAt = new Timestamp(System.currentTimeMillis());
                Timestamp transactionDate = null;
                String transactionDateParam = request.getParameter("transaction_date");

                if (transactionDateParam != null && !transactionDateParam.isEmpty()) {
                    transactionDate = Timestamp.valueOf(transactionDateParam.replace('T', ' ') + ":00");
                }

                int transactionBalance = type.equalsIgnoreCase("deposit") ? value : -value;

                Transaction transaction = new Transaction(0, bankAccountId, value, type, transactionDate, createdAt, transactionBalance);

                transactionDao.add(transaction);
                request.setAttribute("mensagem", "Transação cadastrada com sucesso!");
            }
            else if ("updateTransaction".equals(action)) {
                // Lógica de atualização de transação
                int transactionId = Integer.parseInt(request.getParameter("transactionId"));
                int value = Integer.parseInt(request.getParameter("value"));
                String type = request.getParameter("type");
                String transactionDateParam = request.getParameter("transaction_date");

                Timestamp transactionDate = null;
                if (transactionDateParam != null && !transactionDateParam.isEmpty()) {
                    transactionDate = Timestamp.valueOf(transactionDateParam.replace('T', ' ') + ":00");
                }

                Transaction transaction = new Transaction(transactionId, 0, value, type, transactionDate, null, 0);
                transactionDao.update(transaction);
                request.setAttribute("mensagem", "Transação atualizada com sucesso!");
            }
            else if ("deleteTransaction".equals(action)) {
                // Lógica de exclusão de transação
                int transactionId = Integer.parseInt(request.getParameter("transactionId"));
                transactionDao.delete(transactionId);
                request.setAttribute("mensagem", "Transação deletada com sucesso!");
            }

        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao processar transação.");
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
                String user = (String) request.getSession().getAttribute("user");
                List<Transaction> transactions = transactionDao.findAllByUserEmail(user);
                request.setAttribute("transactions", transactions);
            } catch (DBException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao listar transações.");
            }

            request.getRequestDispatcher("/views/pages/transactions/transactions_list.jsp").forward(request, response);

        } else if ("newTransaction".equals(action)) {
            try {
                String user = (String) request.getSession().getAttribute("user");
                List<BankAccount> bankAccounts = bankAccountDao.findAllByUserEmail(user);
                request.setAttribute("bankAccounts", bankAccounts);
            } catch (DBException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao listar contas bancárias.");
            }
            request.getRequestDispatcher("/views/pages/transactions/transaction/transaction_new.jsp").forward(request, response);
        }
        // Exibir formulário de atualização
        else if ("showUpdateForm".equals(action)) {
            int transactionId = Integer.parseInt(request.getParameter("id"));
            try {
                Transaction transaction = transactionDao.findById(transactionId);
                request.setAttribute("transaction", transaction);

                // Formatar a data de transação para o formato 'yyyy-MM-dd'T'HH:mm'
                if (transaction.getTransactionDate() != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                    String formattedDate = dateFormat.format(transaction.getTransactionDate());
                    request.setAttribute("formattedTransactionDate", formattedDate);
                }

                request.getRequestDispatcher("/views/pages/transactions/transaction/transaction_update.jsp").forward(request, response);
            } catch (DBException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao carregar transação.");
            }

        }
        // Exibir formulário de exclusão
        else if ("deleteTransaction".equals(action)) {
            int transactionId = Integer.parseInt(request.getParameter("id"));
            try {
                Transaction transaction = transactionDao.findById(transactionId);
                request.setAttribute("transaction", transaction);
                request.getRequestDispatcher("/views/pages/transactions/transaction/transaction_delete.jsp").forward(request, response);
            } catch (DBException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao carregar transação.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
