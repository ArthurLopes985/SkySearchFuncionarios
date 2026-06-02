<%@page import="model.Funcionarios"%>
<%@page import="dao.FuncionariosDAO.PreencherPorId"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <% String idstring = request.getParameter("id");
        Funcionarios f = null;

        if (idstring != null && !idstring.isEmpty()) {
            int id = Integer.parseInt(idstring);
            PreencherPorId fdao = new PreencherPorId();
            f = fdao.PreencherPorID(id);
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Apagar</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/Apagar.css">
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
                <input type="date" name="dtnascnovo" value="<%= f.getDataNasc()%>" required readonly>
            </div>
            <div>
                <label>Data de Admissão: </label>
                <input type="date" name="dtadmnovo" value="<%= f.getDataAdmissao()%>" required readonly>
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
            <br>
            <label>Endereco: </label>
                <div>
                    <label>CEP: </label>
                    <input type="text" name="cepnovo" value="<%= f.getEndereco().getCep()%>" required readonly>
                </div>
            <div>
                <label>Rua: </label>
                <input type="text" name="ruanovo" value="<%= f.getEndereco().getRua()%>" required readonly>
            </div>
            <div>
                <label>Bairro: </label>
                <input type="text" name="bairronovo" value="<%= f.getEndereco().getBairro()%>" required readonly>
            </div>
            <div>
                <label>Cidade: </label>
                <input type="text" name="cidadenovo" value="<%= f.getEndereco().getCidade()%>" required readonly>
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
