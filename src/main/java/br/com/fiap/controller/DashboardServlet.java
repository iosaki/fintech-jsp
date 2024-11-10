//package br.com.fiap.controller;
//
//import br.com.fiap.model.User;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet("/dashboard")
//public class DashboardServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        User user = getUserFromSession(request); // Função para obter o usuário logado
//        double accountBalance = getAccountBalance(user); // Função para obter o saldo
//        int totalTransactions = getTotalTransactions(user); // Função para obter o número de transações
//
//        // Passa as informações para o JSP
//        request.setAttribute("user", user);
//        request.setAttribute("accountBalance", accountBalance);
//        request.setAttribute("totalTransactions", totalTransactions);
//
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//    }
//
//    private User getUserFromSession(HttpServletRequest request) {
//        // Lógica para obter o usuário logado
//    }
//
//    private double getAccountBalance(User user) {
//        // Lógica para calcular o saldo da conta
//    }
//
//    private int getTotalTransactions(User user) {
//        // Lógica para calcular o total de transações realizadas
//    }
//}
//
