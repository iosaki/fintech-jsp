<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Lista de Transações</title>
  <!-- Inclui o header.jsp -->
  <%@ include file="../../../partials/header.jsp" %>
  <!-- Inclui o FontAwesome para ícones -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<h1>Lista de Transações</h1>
<table border="1">
  <thead>
  <tr>
    <th>ID</th>
    <th>BankAccount ID</th>
    <th>Valor</th>
    <th>Tipo</th>
    <th>Data da Transação</th>
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
      <td>${transaction.createdAt}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="../../index.jsp">Voltar para a Home</a>
</body>
</html>
