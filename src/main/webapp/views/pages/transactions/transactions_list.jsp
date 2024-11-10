<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Histórico de Transações</title>
    <%@ include file="../../../partials/header.jsp" %>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <%@ include file="../../../partials/menu.jsp" %>
        <main class="main-content col-md-10 ms-md-auto p-3">
            <div class="container">
                <h2 class="mb-4">Histórico de Transações</h2>

                <section class="mb-4">
                    <c:if test="${not empty mensagem}">
                        <div class="alert alert-success ms-2 me-2 m-auto mt-2">${mensagem}</div>
                    </c:if>

                    <c:if test="${not empty erro}">
                        <div class="alert alert-danger ms-2 me-2 m-auto mt-2">${erro}</div>
                    </c:if>

                    <h4>Saldo Atual: ${accountBalance}</h4>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Conta Bancária</th>
                            <th>Valor</th>
                            <th>Tipo</th>
                            <th>Data da Transação</th>
                            <th>Saldo</th>
                            <th>Data de Criação</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="transaction" items="${transactions}">
                            <tr>
                                <td>${transaction.id}</td>
                                <td>${transaction.bankAccountId}</td>
                                <td>${transaction.value}</td>
                                <td>${transaction.type}</td>
                                <td>${transaction.transactionDate}</td>
                                <td>${transaction.balance}</td>
                                <td>${transaction.createdAt}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <a href="${pageContext.request.contextPath}/transaction?action=newTransaction" class="btn btn-primary mt-3">Nova Transação</a>
                </section>
            </div>
        </main>
    </div>
</div>
</body>
</html>
