<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Esqueci a Senha</title>
</head>
<body>
    <h1>Esqueci a Senha</h1>

    <form action="forgotPassword" method="POST">
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required placeholder="Digite seu email">
        </div>
        <div>
            <button type="submit">Enviar Link de Redefinição</button>
        </div>
    </form>

    <p>Já lembrou da senha? <a href="login.jsp">Faça login aqui</a>.</p>
</body>
</html>
