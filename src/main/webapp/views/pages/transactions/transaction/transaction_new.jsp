<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Cadastrar Transação</title>
  <%@ include file="../../../partials/header.jsp" %>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <%@ include file="../../../partials/menu.jsp" %>
    <main class="main-content col-md-10 ms-md-auto p-3">
      <div class="container">
        <h2 class="mb-4">Cadastrar Nova Transação</h2>

        <section class="mb-4">
          <c:if test="${not empty mensagem}">
            <div class="alert alert-success ms-2 me-2 m-auto mt-2">${mensagem}</div>
          </c:if>

          <c:if test="${not empty erro}">
            <div class="alert alert-danger ms-2 me-2 m-auto mt-2">${erro}</div>
          </c:if>

          <form action="${pageContext.request.contextPath}/transaction" method="post">
            <div class="mb-3">
              <label for="bankAccounts" class="form-label">ID da Conta Bancária</label>
              <select class="form-control" id="bankAccounts" name="bankAccountId" required>
                <option value="">Selecione uma conta</option>
                <c:forEach var="account" items="${bankAccounts}">
                  <option value="${account.id}">${account.name} - Saldo Atual: ${account.balance}</option>
                </c:forEach>
              </select>
            </div>

            <div class="mb-3">
              <label for="value" class="form-label">Valor</label>
              <input type="number" class="form-control" id="value" name="value" required>
            </div>

            <div class="mb-3">
              <label for="type" class="form-label">Tipo</label>
              <select class="form-control" id="type" name="type" required>
                <option value="credit">Crédito</option>
                <option value="debit">Débito</option>
              </select>
            </div>

            <div class="mb-3">
              <label for="transaction_date" class="form-label">Data e Hora da Transação (yyyy-MM-dd HH:mm:ss)</label>
              <input type="text" class="form-control" id="transaction_date" name="transaction_date" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>

            <button type="submit" class="btn btn-primary">Cadastrar Transação</button>
          </form>
        </section>
      </div>
    </main>
  </div>
</div>
</body>
</html>
