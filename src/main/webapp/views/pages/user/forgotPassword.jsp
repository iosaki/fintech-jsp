<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Esqueci a Senha</title>
    <%@ include file="../../partials/header.jsp" %>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="form-signin w-100 m-auto">
    <form action="${pageContext.request.contextPath}/forgotPassword" method="post">
        <div class="header-center">
            <img class="mb-4" src="${pageContext.request.contextPath}/images/logo.svg" alt="" width="72" height="57">
        </div>

        <div class="form-floating">
            <input type="email" class="form-control" name="email" id="email" placeholder="Digite seu email" required>
            <label for="email">Email</label>
        </div>

        <div class="text-center mt-3">
            <button class="btn btn-primary w-100 py-2" type="submit">Enviar Link de Redefinição</button>
        </div>

        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">Já lembrou da senha? Faça login aqui</a>
        </div>

        <p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
    </form>
</main>

</body>
</html>
