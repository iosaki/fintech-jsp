<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Carteira</title>

    <!-- Inclui o header.jsp -->
    <%@ include file="../../partials/header.jsp" %>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidemenu (Visible on md and larger screens) -->
        <%@ include file="../../partials/menu.jsp" %>
        <!-- Main content -->
        <main class="main-content col-md-10 ms-md-auto p-3">
            <div class="container">
                <h2 class="mb-4">Carteira</h2>

                <!-- Seção de contas bancárias -->
                <section class="mb-4">
                    <h5>
                        Contas Bancárias
                        <a href="${pageContext.request.contextPath}/bank" class="btn btn-sm btn-outline-primary ms-2 btn-custom">
                            Cadastrar Conta
                        </a>
                    </h5>

                    <c:forEach var="account" items="${bankAccounts}">
                        <div class="card mb-3 border-primary rounded-3">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div class="d-flex align-items-center">
                                    <!-- Exemplo de exibição da logo do banco. Certifique-se de ter o campo no banco de dados e no objeto model -->
                                    <img src="${account.logoUrl}" alt="${account.name}" width="50" class="me-4"/>
                                    <div>
                                        <h6 class="mb-0">${account.name}</h6>
                                        <p class="mb-0">Agência: ${account.bankName}</p>
                                    </div>
                                </div>
                                <div class="text-end">
                                    <p class="text-muted mb-0">Saldo: R$ X</p> <!-- Valor do saldo se disponível -->
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