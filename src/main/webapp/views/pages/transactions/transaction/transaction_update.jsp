<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Atualizar Transação</title>
    <%@ include file="../../../partials/header.jsp" %>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <%@ include file="../../../partials/menu.jsp" %>
        <main class="main-content col-md-10 ms-md-auto p-3">
            <div class="container">
                <h2 class="mb-4">Atualizar Transação</h2>

                <form action="${pageContext.request.contextPath}/transaction?action=updateTransaction" method="post">
                    <input type="hidden" name="transactionId" value="${transaction.id}">

                    <div class="mb-3">
                        <label for="value" class="form-label">Valor R$</label>
                        <input type="number" class="form-control" id="value" name="value" value="${transaction.value}" required>
                    </div>

                    <div class="mb-3">
                        <label for="type" class="form-label">Tipo</label>
                        <select class="form-control" id="type" name="type" required>
                            <option value="saque" ${transaction.type == 'saque' ? 'selected' : ''}>Saque</option>
                            <option value="depósito" ${transaction.type == 'depósito' ? 'selected' : ''}>Depósito</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="transaction_date" class="form-label">Data e Hora da Transação</label>
                        <input type="datetime-local" class="form-control" id="transaction_date" name="transaction_date" value="${transaction.transaction_date}">
                    </div>

                    <button type="submit" class="btn btn-primary">Atualizar Transação</button>
                </form>
            </div>
        </main>
    </div>
</div>
</body>
</html>
