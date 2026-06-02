<%@ page import="java.util.List" %>
<%@ page import="model.RegistroPonto" %>
<%@ page import="model.Funcionarios" %>
<%@ page import="java.time.LocalDate" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Funcionarios funcionario = (Funcionarios) request.getAttribute("funcionario");

    List<RegistroPonto> registros = null;

    if (funcionario != null) {
        registros = funcionario.getRegistros();
    }

    LocalDate hoje = LocalDate.now();
            int mes = hoje.getMonthValue();
            int ano = hoje.getYear();
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Registrar Ponto</title>

    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
            color: gainsboro;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 5px;
            text-align: left;
        }
        th {
            background-color: #eee;
            color: black;
        }
    </style>

    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/RegistroPonto.css">
</head>

<body>

<script>
    const urlParams = new URLSearchParams(window.location.search);
    const erro = urlParams.get('erro');
    if (erro) {
        alert(erro);
    }
</script>

<form action="FuncionariosController" method="POST">
    <div class="container fade-up">
        <div class="titulo">
            <h1 style="text-align: center; color: rgb(255, 255, 255);">Registrar Ponto</h1>
        </div>
        <br>

        <div class="container1">
            <input class="entrada" type="submit" name="op" value="Entrada">
            <input class="saida" type="submit" name="op" value="Saida">
            <input type="button" value="Voltar" class="botao" onclick="window.location.href='inicio.jsp'">
        </div>
    </div>
</form>

<div class="info-textos fade-up">
    <h2>Registros desse Mês</h2>
    <h2>Salário Atual de: <%=mes%>/<%=ano%> R$ <%= request.getAttribute("salarioFinal") != null ? String.format("%.2f", (Double) request.getAttribute("salarioFinal")) : "0.00" %></h2>
    <h2>Horário de Entrada: 08:00</h2>

    <p>
        As horas extras são calculadas com acréscimo de 50% sobre o valor da hora normal.
        Já os descontos por horas não trabalhadas são feitos de forma proporcional ao valor da hora padrão.
        A jornada diária considera uma carga de 8 horas, podendo haver tolerância de até 10 minutos.
    </p>
</div>

<table class="fade-up">
    <thead>
        <tr>
            <th>Data</th>
            <th>Horário de Entrada</th>
            <th>Horário de Saída</th>
            <th>Houve Atraso?</th>
            <th>Horas Trabalhadas</th>
            <th>Horas Extras</th>
        </tr>
    </thead>
    <tbody>
        <%
            if (registros != null) {
                for (RegistroPonto r : registros) {
        %>
        <tr>
            <td><%= r.getData() %></td>
            <td><%= r.getHoraEntrada() %></td>
            <td><%= r.getHoraSaida() %></td>
            <td><%= r.verificarAtraso() %></td>
            <td><%= r.calcularHorasTrabalhadas() %></td>
            <td><%= r.calcularHorasExtras() %></td>
        </tr>
        <%
                }
            }
        %>
    </tbody>
</table>
</body>
</html>