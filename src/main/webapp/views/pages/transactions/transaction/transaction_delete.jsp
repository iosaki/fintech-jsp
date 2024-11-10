<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Deletar Transação</title>
  <%@ include file="../../../partials/header.jsp" %>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <%@ include file="../../../partials/menu.jsp" %>
    <main class="main-content col-md-10 ms-md-auto p-3">
      <div class="container">
        <h2 class="mb-4">Deletar Transação</h2>

        <form action="${pageContext.request.contextPath}/transaction?action=deleteTransaction" method="post">
          <input type="hidden" name="transactionId" value="${transaction.id}">

          <p>Tem certeza de que deseja deletar esta transação de R$ ${transaction.value}, realizada em ${transaction.transaction_date}?</p>

          <button type="submit" class="btn btn-danger">Deletar</button>
          <a href="${pageContext.request.contextPath}/transaction?action=listTransactions" class="btn btn-secondary">Cancelar</a>
        </form>
      </div>
    </main>
  </div>
</div>
</body>
</html>
