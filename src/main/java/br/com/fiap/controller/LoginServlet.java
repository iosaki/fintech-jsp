package br.com.fiap.controller;

//import br.com.fiap.bo.EmailBo;
import br.com.fiap.dao.UserDao;
//import br.com.fiap.exception.EmailException;
import br.com.fiap.factory.DaoFactory;
import br.com.fiap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao dao;
//    private EmailBo bo;

    public LoginServlet() {
        dao = DaoFactory.getUserDao();
//        bo = new EmailBo();
    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Iniciando doPost LoginServlet");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(email, password );

        if (dao.validateUser(user)) {

            HttpSession session = request.getSession();
            session.setAttribute("user", email);
//            String mensagem =
//                    "Um login foi realizado na plataforma em " + LocalDate.now();
            request.getRequestDispatcher("/views/pages/index.jsp").forward(request, response);
//            try {
//                bo.enviarEmail(email, "Login Realizado", mensagem);
//            } catch (EmailException e) {
//                e.printStackTrace();
//            }

        }else {
            request.setAttribute("erro", "Usuário e/ou senha inválidos.");
            request.getRequestDispatcher("/views/pages/auth/login.jsp").forward(request, response);
        }
    }

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("/views/pages/auth/login.jsp").forward(request, response);

    }
}