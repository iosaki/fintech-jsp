<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String user = (String) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home - JSPs</title>
    <!-- Inclui o header.jsp -->
    <%@ include file="../../../partials/header.jsp" %>
    <script>
        function copyBankId() {
            document.getElementById("banksId").value = document.getElementById("bankId").value;
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidemenu (Visible on md and larger screens) -->
        <%@ include file="../../../partials/menu.jsp" %>
        <!-- Main content (Adjust margin to account for bottom navbar on small screens) -->
        <main class="main-content col-md-10 ms-md-auto p-3">
            <div class="container">
                <h2 class="mb-4">Criar nova conta</h2>

                <!-- Seção do formulário -->
                <section class="mb-4">
                    <h5>
                        Criar nova conta
                    </h5>
                    <c:if test="${not empty mensagem}">
                        <div class="alert alert-success ms-2 me-2 m-auto mt-2">${mensagem}</div>
                    </c:if>

                    <c:if test="${not empty erro}">
                        <div class="alert alert-danger ms-2 me-2 m-auto mt-2">${erro}</div>
                    </c:if>
                    <form action="${pageContext.request.contextPath}/bankaccount" method="post">
                        <div class="mb-3">
                            <label for="bankId" class="form-label">Banco</label>
                            <select class="form-control" id="bankId" name="bankId" onchange="copyBankId()" required>
                                <option value="">Selecione um banco</option>
                                <c:forEach var="bank" items="${banks}">
                                    <option value="${bank.id}">${bank.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="name" class="form-label">Nome</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <!-- Campo de e-mail oculto que pega o valor da sessão -->
                        <input type="hidden" id="userEmail" name="userEmail" value="<%= user %>">
                        <button type="submit" class="btn btn-primary">Criar Conta</button>
                    </form>
                </section>

            </div>
        </main>
    </div>
</div>
</body>
<%@ include file="../../../partials/footer.jsp" %>
</html>