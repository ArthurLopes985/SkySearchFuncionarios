<%@page import="Model.Funcionarios"%>
<%@page import="DAO.FuncionariosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <% String idstring = request.getParameter("id");
        Funcionarios f = null;

        if (idstring != null) {
            int id = Integer.parseInt(idstring);
            FuncionariosDAO fdao = new FuncionariosDAO();
            f = fdao.PreencherPorID(id);
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Apagar</title>
        <link rel="stylesheet" href="Apagar.css">
    </head>
    <body>
        <%
            String erro = (String) request.getAttribute("erro");
            if (erro != null) {
        %>
        <script>
            alert("<%= erro%>");
        </script>
        <%
            }
        %>
    </body>

    <body>
        <form action="FuncionariosController" method="POST">
            <div class="container">
            <h1>Apagar Funcionário</h1>
            <label>Confirme os dados para ter certeza se deseja apagá-lo.</label>
            <br>
            <div>
                <label>ID: </label>
                <input type="number" name="idlogin" value="<%= f.getId()%>" readonly>
            </div>
            <div>
                <label>Nome: </label>
                <input type="text" name="nomenovo" value="<%= f.getNome()%>"required readonly>
            </div>
            <div>
                <label>CPF: </label>
                <input type="text" name="cpfnovo"value="<%= f.getCpf()%>" readonly>
            </div>
            <div>
                <label>E-mail: </label>
                <input type="text" name="emailnovo" value="<%= f.getEmail()%>"required readonly>
            </div>
            <div>
                <label>Telefone: </label>
                <input type="tel" name="telefonenovo" pattern = "\d{10, 11}" maxlength="11" value="<%= f.getTelefone()%>"required
                       oninput="this.value = this.value.replace(/[^0-9]/g, '')" readonly>
            </div>
            <div>
                <label for = "genero"> Genero: </label>
                <input type="text" name="generonovo" value="<%= f.getGenero()%>"required readonly>
            </div>
            <div>
                <label>Data de Nascimento: </label>
                <input type="date" name="dtnascnovo" value="<%= f.getData_nasc()%>" required readonly>
            </div>
            <div>
                <label>Data de Admissão: </label>
                <input type="date" name="dtadmnovo" value="<%= f.getData_admissao()%>" required readonly>
            </div>
            <div>
                <label>Setor: </label>
                <input type="text" name="setornovo" value="<%= f.getSetor()%>" required readonly>
            </div>
            <div>
                <label>Cargo: </label>
                <input type="text" name="cargonovo" value="<%= f.getCargo()%>" required readonly>
            </div>
            <div>
                <label>Salario: </label>
                <input type="number" name="salarionovo" step="0.1" value="<%= f.getSalario()%>" required readonly>
            </div>
            <div>
                <label>Endereco: </label>
                <input type="text" name="endereconovo" value="<%= f.getEndereco()%>" required readonly>
            </div>
            <br>
            <label>Digite a senha atual do Funcionário(a) <%= f.getNome()%> para validar a exclusão.</label>
            <br>
            <div>
                <label>Senha: </label>
                <input type="password" name="senhalogin" minlength="5" maxlength="16" pattern="[^ _;\-]*" title="Não são permitidos os caracteres -,_,; e espaço " required>
            </div>
            <br>
            <input class="botao" type="submit" name="op" value="APAGAR">
            <div>
            <input class="botao" type = "button" onclick="window.location.href = 'inicio.jsp'" value="CANCELAR" </input>
        </form>
    </body>
</html>
