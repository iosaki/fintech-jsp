<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>

    <!-- Inclui o header.jsp -->
    <%@ include file="../partials/header.jsp" %>
    <!-- Inclui a biblioteca Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidemenu (Visible on md and larger screens) -->
        <%@ include file="../partials/menu.jsp" %>
        <!-- Main content -->
        <main class="main-content col-md-10 ms-md-auto p-3">
            <div class="container">
                <h2 class="mb-4">Home</h2>

            </div>
        </main>
    </div>
</div>

<%@ include file="../partials/footer.jsp" %>
</body>
</html>