<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
  String user = (String) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Editar Conta Bancária</title>
  <!-- Inclui o header.jsp -->
  <%@ include file="../../../partials/header.jsp" %>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!-- Sidemenu -->
    <%@ include file="../../../partials/menu.jsp" %>
    <!-- Main content -->
    <main class="main-content col-md-10 ms-md-auto p-3">
      <div class="container">
        <h2 class="mb-4">Editar Conta Bancária</h2>

        <c:if test="${not empty mensagem}">
          <div class="alert alert-success ms-2 me-2 m-auto mt-2">${mensagem}</div>
        </c:if>

        <c:if test="${not empty erro}">
          <div class="alert alert-danger ms-2 me-2 m-auto mt-2">${erro}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/bankaccount" method="post">
          <!-- Campo oculto para armazenar o ID da conta -->
          <input type="hidden" id="id" name="id" value="${bankAccount.id}">
          <!-- Campo de e-mail oculto que pega o valor da sessão -->
          <input type="hidden" id="userEmail" name="userEmail" value="<%= user %>">

          <div class="mb-3">
            <label for="bankId" class="form-label">Banco</label>
            <select class="form-control" id="bankId" name="bankId" required>
              <c:forEach var="bank" items="${banks}">
                <option value="${bank.id}" ${bank.id == bankAccount.bankId ? 'selected' : ''}>${bank.name}</option>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3">
            <label for="name" class="form-label">Nome</label>
            <input type="text" class="form-control" id="name" name="name" value="${bankAccount.name}" required>
          </div>

          <button type="submit" class="btn btn-primary">Salvar Alterações</button>
        </form>
      </div>
    </main>
  </div>
</div>
</body>
<%@ include file="../../../partials/footer.jsp" %>
</html>