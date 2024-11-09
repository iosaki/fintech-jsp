<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Criar Conta</title>
</head>
<body>
    <h1>Criar Nova Conta</h1>

    <form action="createAccount" method="POST">
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required placeholder="Digite seu email">
        </div>
        <div>
            <label for="name">Nome:</label>
            <input type="text" id="name" name="name" required placeholder="Digite seu nome completo">
        </div>
        <div>
            <label for="surname">Sobrenome:</label>
            <input type="text" id="surname" name="surname" required placeholder="Digite seu sobrenome">
        </div>
        <div>
            <label for="password">Senha:</label>
            <input type="password" id="password" name="password" required placeholder="Digite sua senha">
        </div>
        <div>
            <label for="confirmPassword">Confirmar Senha:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required placeholder="Confirme sua senha">
        </div>
        <div>
            <label for="phoneNumber">Telefone:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Digite seu telefone (opcional)">
        </div>
        <div>
            <label for="profilePictureUrl">URL da Foto de Perfil:</label>
            <input type="url" id="profilePictureUrl" name="profilePictureUrl" placeholder="Digite a URL da sua foto de perfil (opcional)">
        </div>
        <div>
            <button type="submit">Criar Conta</button>
        </div>
    </form>

    <p>Já tem uma conta? <a href="login.jsp">Faça login aqui</a>.</p>
</body>
</html>
