<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String currentPath = request.getRequestURI();
%>
<div class="sidemenu col-md-2 d-none d-md-flex flex-column flex-shrink-0 p-3 bg-light">
  <a href="${pageContext.request.contextPath}/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
    <img src="${pageContext.request.contextPath}/images/logo.svg" alt="logo" width="40" height="32" class="bi me-2" />
    <span class="fs-4">PRISMA</span>
  </a>
  <hr />
  <ul class="nav nav-pills flex-column mb-auto">
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/views/pages/index.jsp"
         class="nav-link <%= currentPath.endsWith("/index.jsp") || currentPath.endsWith("/") ? "active" : "link-dark" %>">
        <i class="bi bi-house-door p-1"></i>
        home
      </a>
    </li>
    <li>
      <a href="${pageContext.request.contextPath}/views/pages/wallet/list.jsp"
         class="nav-link <%= currentPath.endsWith("/bankaccount") ? "active" : "link-dark" %>">
        <i class="bi bi-wallet-fill p-1"></i>
        carteira
      </a>
    </li>
    <li>
      <a href="${pageContext.request.contextPath}/transaction?action=listTransactions"
         class="nav-link <%= currentPath.endsWith("/transaction") ? "active" : "link-dark" %>">
        <i class="bi bi-plus-circle-fill p-1"></i>
        transações
      </a>
    </li>
  </ul>
  <hr />
  <div class="dropdown">
    <a href="#" class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle" id="dropdownUser2" data-bs-toggle="dropdown" aria-expanded="false">
      <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2" />
      <strong>${user}</strong>
    </a>
    <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser2">
      <li><a class="dropdown-item" href="#">Configurações</a></li>
      <li><a class="dropdown-item" href="#">Perfil</a></li>
      <li><hr class="dropdown-divider" /></li>
      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Sair</a></li>
    </ul>
  </div>
</div>
