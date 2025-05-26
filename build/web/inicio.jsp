<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="TelaInicial.css">
    </head>
    <body>
        <script>
            const urlParams = new URLSearchParams(window.location.search);
            const erro = urlParams.get('erro');
            if (erro) {
                alert(erro);
            }
        </script>
    <body>
        <form action="FuncionariosController" method="POST">
            <div class="container">
            <div><h1>Página Inicial</h1></div>
            <div>
                <%@ page session="true" %>
                <%
                    String nome = (String) session.getAttribute("nome");
                %>
                <label><h2>Olá <%= nome != null ? nome : "Usuário" %>, o que você deseja fazer hoje?<h2></label>
            </div>
            <br>
            <input class="cadastro" type="submit" name="op" value="CADASTRAR">
            <input class="lista" type="submit" name="op" value="LISTAR">
            <input class="sair" type="submit" name="op" value="SAIR">
            </div>
        </form>
</html>
