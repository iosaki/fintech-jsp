<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Criar Conta</title>
    <%@ include file="../../partials/header.jsp" %>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="form-signin w-100 m-auto">
    <form action="${pageContext.request.contextPath}/createAccount" method="post">
        <div class="header-center">
            <img class="mb-4" src="${pageContext.request.contextPath}/images/logo.svg" alt="" width="72" height="57">
        </div>

        <div class="form-floating">
            <input type="email" class="form-control" name="email" id="email" placeholder="Digite seu email" required>
            <label for="email">Email</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" name="name" id="name" placeholder="Digite seu nome completo" required>
            <label for="name">Nome</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" name="surname" id="surname" placeholder="Digite seu sobrenome" required>
            <label for="surname">Sobrenome</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="password" id="password" placeholder="Digite sua senha" required>
            <label for="password">Senha</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="Confirme sua senha" required>
            <label for="confirmPassword">Confirmar Senha</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="Digite seu telefone (opcional)">
            <label for="phoneNumber">Telefone</label>
        </div>
        <div class="form-floating">
            <input type="url" class="form-control" name="profilePictureUrl" id="profilePictureUrl" placeholder="Digite a URL da sua foto de perfil (opcional)">
            <label for="profilePictureUrl">URL da Foto de Perfil</label>
        </div>

        <div class="text-center mt-3">
            <button class="btn btn-primary w-100 py-2" type="submit">Criar Conta</button>
        </div>

        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">Já tem uma conta? Faça login aqui</a>
        </div>

        <p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
    </form>
</main>

</body>
</html>
