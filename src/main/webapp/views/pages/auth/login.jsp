<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String currentPath = request.getRequestURI();
%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <%@ include file="../../partials/header.jsp" %>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary" cz-shortcut-listen="true">

<main class="form-signin w-100 m-auto">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="header-center">
            <img class="mb-4" src="${pageContext.request.contextPath}/images/logo.svg" alt="" width="72" height="57">
<%--            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>--%>
        </div>

<!-- Exibir mensagem de erro, se presente -->
<c:if test="${not empty erro}">
    <div class="alert alert-danger" role="alert">
            ${erro}
    </div>
</c:if>

        <div class="form-floating">
            <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com">
            <label for="email">Email address</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control"  name="password" id="password" placeholder="Password">
            <label for="password">Password</label>
        </div>

        <div class="form-check text-start my-3">
            <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
            <label class="form-check-label" for="flexCheckDefault">
                Remember me
            </label>
        </div>
        <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
    </form>
</main>
</body>