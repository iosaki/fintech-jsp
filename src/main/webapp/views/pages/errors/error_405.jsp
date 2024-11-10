<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Erro 405 - Método Não Permitido</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container text-center mt-5">
  <h1 class="display-4">Erro 405</h1>
  <p>O método HTTP utilizado não é permitido para esta URL.</p>
  <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Voltar para a página inicial</a>
</div>
</body>
</html>
