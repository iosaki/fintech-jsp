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

                <!-- Seção de contas bancárias -->
                <section class="mb-4">
                    <h5>
                        Contas Bancária
                        <a
                                href="${pageContext.request.contextPath}/views/bankAccount/newBankAccount.jsp"
                                class="btn btn-sm btn-outline-primary ms-2 btn-custom"
                        >
                            Cadastrar Conta
                        </a>
                    </h5>
                    <div class="card mb-3 border-primary rounded-3">
                        <div
                                class="card-body d-flex justify-content-between align-items-center"
                        >
                            <div class="d-flex align-items-center">
                                <img
                                        src="${pageContext.request.contextPath}/images/itau-logo.svg"
                                        alt="Visa"
                                        width="50"
                                        class="me-4"
                                />
                                <div>
                                    <h6 class="mb-0">Conta Corrente</h6>
                                    <p class="mb-0">Agência: 1234 / Conta: 56789-0</p>
                                </div>
                            </div>
                            <div class="text-end">
                                <p class="text-muted mb-0">R$ 10.000,00</p>
                            </div>
                        </div>
                    </div>
                    <div class="card mb-3 border-primary rounded-3">
                        <div
                                class="card-body d-flex justify-content-between align-items-center"
                        >
                            <div class="d-flex align-items-center">
                                <img
                                        src="${pageContext.request.contextPath}/images/nubank-logo.svg"
                                        alt="Visa"
                                        width="50"
                                        class="me-4"
                                />
                                <div>
                                    <h6 class="mb-0">Conta Corrente</h6>
                                    <p class="mb-0">Agência: 9876 / Conta: 54321-1</p>
                                </div>
                            </div>
                            <div class="text-end">
                                <p class="text-muted mb-0">R$ 5.000,00</p>
                            </div>
                        </div>
                    </div>
                </section>

                <!-- Seção de cartões de crédito -->
                <section class="mb-4">
                    <h5>
                        Cartões de Crédito
                        <a
                                href="#"
                                class="btn btn-sm btn-outline-primary ms-2 btn-custom"
                        >Cadastrar Cartão</a
                        >
                    </h5>
                    <div class="card mb-3 border-primary rounded-3">
                        <div
                                class="card-body d-flex justify-content-between align-items-center"
                        >
                            <div class="d-flex align-items-center">
                                <img
                                        src="${pageContext.request.contextPath}/images/visa-logo.svg"
                                        alt="Visa"
                                        width="50"
                                        class="me-4"
                                />
                                <div>
                                    <h6 class="mb-0">Cartão Nubank</h6>
                                    <p class="mb-0">**** **** **** 1234</p>
                                </div>
                            </div>
                            <div class="text-end">
                                <p class="text-muted mb-0">R$ 1.234,56</p>
                            </div>
                        </div>
                    </div>
                    <div class="card mb-3 border-primary rounded-3">
                        <div
                                class="card-body d-flex justify-content-between align-items-center"
                        >
                            <div class="d-flex align-items-center">
                                <img
                                        src="${pageContext.request.contextPath}/images/mastercard-logo.svg"
                                        alt="MasterCard"
                                        width="50"
                                        class="me-4"
                                />
                                <div>
                                    <h6 class="mb-0">Cartão Santander</h6>
                                    <p class="mb-0">**** **** **** 5678</p>
                                </div>
                            </div>
                            <div class="text-end">
                                <p class="text-muted mb-0">R$ 2.345,67</p>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </main>
    </div>
</div>
</body>
<%@ include file="../partials/footer.jsp" %>
</html>