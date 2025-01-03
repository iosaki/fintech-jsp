package br.com.fiap.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
//public class LoginFilter implements Filter {
//
//    @Override
//    public void doFilter(
//            ServletRequest request,
//            ServletResponse resp,
//            FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpSession session = req.getSession();
//        String url = req.getRequestURI();
//
//        if (session.getAttribute("user") == null && !url.endsWith("login") && !url.contains("resources") && !url.contains("images")) {
//            request.setAttribute("erro", "Entre com o usuário e senha!");
//            request.getRequestDispatcher("/views/pages/auth/login.jsp").forward(request, resp);
//        }else {
//            chain.doFilter(request, resp);
//        }
//
//    }
//}

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {

        // Permite o acesso a todas as páginas, sem verificação de login
        chain.doFilter(request, resp);
    }
}
