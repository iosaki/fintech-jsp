<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contas Bancárias</title>
</head>
<body>
<h1>Lista de Contas Bancárias</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Bank ID</th>
        <th>Email do Usuário</th>
        <th>Data de Criação</th>
        <th>Banks ID</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="bankAccount" items="${bankAccounts}">
        <tr>
            <td>${bankAccount.id}</td>
            <td>${bankAccount.name}</td>
            <td>${bankAccount.bankId}</td>
            <td>${bankAccount.userEmail}</td>
            <td>${bankAccount.createdAt}</td>
            <td>${bankAccount.banksId}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="../../index.jsp">Voltar para a Home</a>
</body>
</html>