<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home - JSPs</title>

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
                <h2 class="mb-4">Carteira</h2>

                <!-- Seção do formulário -->
                <section class="mb-4">
                    <h5>
                        Criar nova conta
                    </h5>
                    <form>
                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label">Email address</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1">
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Check me out</label>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </section>


            </div>
        </main>
    </div>
</div>
</body>
<%@ include file="../partials/footer.jsp" %>
</html>