<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--    <title>Home</title>--%>

<%--    <!-- Inclui o header.jsp -->--%>
<%--    <%@ include file="../partials/header.jsp" %>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container-fluid">--%>
<%--    <div class="row">--%>
<%--        <!-- Sidemenu (Visible on md and larger screens) -->--%>
<%--        <%@ include file="../partials/menu.jsp" %>--%>

<%--        <!-- Main content (Adjust margin to account for bottom navbar on small screens) -->--%>
<%--        <main class="main-content col-md-10 ms-md-auto p-3">--%>
<%--            <!-- Dashboard Section -->--%>
<%--            <div class="container mt-4">--%>
<%--                <!-- Saudações e nome do usuário -->--%>
<%--                <div class="alert alert-primary" role="alert">--%>
<%--                    <h4 class="alert-heading">Bem-vindo, <span class="font-weight-bold">${user.name}</span>!</h4>--%>
<%--                    <p>Olá, ${user.email}. Este é o seu painel de controle onde você pode gerenciar suas contas bancárias e transações.</p>--%>
<%--                </div>--%>

<%--                <!-- Gráficos e informações úteis -->--%>
<%--                <div class="row">--%>
<%--                    <!-- Resumo das contas bancárias -->--%>
<%--                    <div class="col-md-4">--%>
<%--                        <div class="card">--%>
<%--                            <div class="card-header">--%>
<%--                                Resumo da Conta--%>
<%--                            </div>--%>
<%--                            <div class="card-body">--%>
<%--                                <h5 class="card-title">Saldo Atual</h5>--%>
<%--                                <p class="card-text">R$ ${accountBalance}</p>--%>
<%--                                <a href="/bank-accounts" class="btn btn-primary">Ver Contas</a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <!-- Transações realizadas -->--%>
<%--                    <div class="col-md-4">--%>
<%--                        <div class="card">--%>
<%--                            <div class="card-header">--%>
<%--                                Transações Recentes--%>
<%--                            </div>--%>
<%--                            <div class="card-body">--%>
<%--                                <h5 class="card-title">Total de Transações</h5>--%>
<%--                                <p class="card-text">${totalTransactions} transações realizadas</p>--%>
<%--                                <a href="/transactions" class="btn btn-primary">Ver Transações</a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <!-- Gráfico de movimentação -->--%>
<%--                    <div class="col-md-4">--%>
<%--                        <div class="card">--%>
<%--                            <div class="card-header">--%>
<%--                                Gráfico de Movimentação--%>
<%--                            </div>--%>
<%--                            <div class="card-body">--%>
<%--                                <p class="card-text">Aqui você pode ver um gráfico de movimentação de sua conta.</p>--%>
<%--                                <!-- Aqui você pode adicionar um gráfico utilizando alguma biblioteca como Chart.js -->--%>
<%--                                <canvas id="movementChart"></canvas>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </main>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<!-- Inclui o footer.jsp -->--%>
<%--<%@ include file="../partials/footer.jsp" %>--%>

<%--<!-- Scripts -->--%>
<%--<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>--%>
<%--<script>--%>
<%--    // Exemplo de gráfico de movimentação--%>
<%--    var ctx = document.getElementById('movementChart').getContext('2d');--%>
<%--    var movementChart = new Chart(ctx, {--%>
<%--        type: 'line',--%>
<%--        data: {--%>
<%--            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],--%>
<%--            datasets: [{--%>
<%--                label: 'Movimentação',--%>
<%--                data: [30, 50, 70, 40, 90, 120],--%>
<%--                borderColor: 'rgb(75, 192, 192)',--%>
<%--                fill: false--%>
<%--            }]--%>
<%--        },--%>
<%--        options: {--%>
<%--            responsive: true,--%>
<%--            scales: {--%>
<%--                x: {--%>
<%--                    title: {--%>
<%--                        display: true,--%>
<%--                        text: 'Meses'--%>
<%--                    }--%>
<%--                },--%>
<%--                y: {--%>
<%--                    title: {--%>
<%--                        display: true,--%>
<%--                        text: 'Valor (R$)'--%>
<%--                    }--%>
<%--                }--%>
<%--            }--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>

<%--</body>--%>
<%--</html>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>

    <!-- Inclui o header.jsp -->
    <%@ include file="../partials/header.jsp" %>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidemenu (Visible on md and larger screens) -->
        <%@ include file="../partials/menu.jsp" %>
        <!-- Main content (Adjust margin to account for bottom navbar on small screens) -->
        <main class="main-content col-md-10 ms-md-auto p-3">
            <div class="container">
                <h2 class="mb-4">Home</h2>
            </div>
        </main>
    </div>
</div>
</body>
<%@ include file="../partials/footer.jsp" %>
</html>