<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transações</title>
    <!-- Inclui o header.jsp -->
    <%@ include file="../../partials/header.jsp" %>
    <style>
        .balance-container {
            background-color: #f8f9fa;
            border: 1px solid #ced4da;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
        }
        .transaction-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .transaction-header h2 {
            margin: 0;
        }
        .transaction-card {
            border: 1px solid #ced4da;
            border-radius: 8px;
            margin-bottom: 10px;
            background-color: #ffffff;
        }
        .transaction-card-body {
            padding: 15px;
        }
        .transaction-details {
            font-size: 14px;
            color: #6c757d;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidemenu (Visible on md and larger screens) -->
        <%@ include file="../../partials/menu.jsp" %>

        <!-- Main content -->
        <main class="main-content col-md-10 ms-md-auto p-3">
            <div class="container">
                <div class="transaction-header">
                    <h2>Transações</h2>
                    <a href="${pageContext.request.contextPath}/transaction?action=newTransaction" class="btn btn-sm btn-outline-primary">
                        Adicionar Transação
                    </a>
                </div>

                <!-- Saldo da Conta -->
                <div class="balance-container">
                    <h4>Saldo Atual</h4>
                    <p class="h2 text-success">R$ ${accountBalance}</p>
                </div>

                <!-- Histórico de Transações -->
                <section class="transaction-history">
                    <h5>Histórico de Transações</h5>
                    <c:forEach var="transaction" items="${transactions}">
                        <div class="transaction-card">
                            <div class="transaction-card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h6>ID da Conta: ${transaction.bankAccountId}</h6>
                                    <p class="transaction-details">Tipo: ${transaction.type}</p>
                                    <p class="transaction-details">Valor: R$ ${transaction.value}</p>
                                    <p class="transaction-details">Data da Transação: ${transaction.transactionDate}</p>
                                </div>
                                <div class="text-end">
                                    <p class="transaction-details">Criado em: ${transaction.createdAt}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </section>
            </div>
        </main>
    </div>
</div>
</body>
<%@ include file="../../partials/footer.jsp" %>
</html>
