<%@page import="java.util.List"%>
<%@page import="model.Funcionarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista</title>
    <style class="tabela">
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
    <link
href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
rel='stylesheet'>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Lista.css">
</head>
<body>
    <% String erro = (String) request.getAttribute("erro");
       if (erro != null) { %>
        <script>alert("<%= erro %>");</script>
    <% } %>

    <form action="FuncionariosController" method="POST">
        <br><br><br>
        
        <input placeholder="Digite um ID" type="number" name="idfiltrar"
               onkeydown="return event.key !== '+' && event.key !== ',' && event.key !== 'e' && event.key !== '-' && event.key !== ' ' && event.key !== ';' && event.key !== '_';">
        <br><br>
        <button type="submit" name="op" value="FiltrarPorID" class="botao"> Filtrar por ID</button>
<button type="submit" name="op" value="ListarTodos" class="botao"> Listar Todos</button>
<input type="button" value="Voltar" class="botao" onclick="window.location.href='inicio.jsp'">


    </div>
    </form>

    <h2  style="text-align:  center; color: gainsboro;">Listar Funcionários </h2>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Email</th>
                <th>Telefone</th>
                <th>Genero</th>
                <th>Data de Nascimento</th>
                <th>Data de Admissão</th>
                <th>Setor</th><th>Cargo</th>
                <th>Salário</th>
                <th>Endereco</th>
                <th>Periculosidade</th>
                <th>Noturno</th>
                <th>Editar</th>
                <th>Apagar</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Funcionarios> funcionarios = (List<Funcionarios>) request.getAttribute("funcionarios");
                if (funcionarios != null) {
                    for (Funcionarios f : funcionarios) {
            %>
            <tr>
                <td><%= f.getId() %></td>
                <td><%= f.getNome() %></td>
                <td><%= f.getCpf() %></td>
                <td><%= f.getEmail() %></td>
                <td><%= f.getTelefone() %></td>
                <td><%= f.getGenero() %></td>
                <td><%= f.getDataNasc() %></td>
                <td><%= f.getDataAdmissao() %></td>
                <td><%= f.getSetor() %></td>
                <td><%= f.getCargo() %></td>
                <td><%= f.getSalario() %></td>
                <td> Rua: <%= f.getEndereco().getRua() %>, Bairro: <%= f.getEndereco().getBairro() %>, CEP: <%= f.getEndereco().getCep() %>, Cidade: <%= f.getEndereco().getCidade() %></td>
                <td><%= f.isPericulosidade()? "Sim" : "Não" %></td>
                <td><%= f.isNoturno()? "Sim" : "Não" %></td>
                <td>
                    <form action="editar.jsp" method="get">
                        <input type="hidden" name="id" value="<%= f.getId() %>">
                        <button type="submit"><i class="bx bx-clipboard" style="font-size: 24px; background-color: black; color: white; padding: 8px; border-radius: 4px;"></i>


</button>
                    </form>
                </td>
                <td >
                    <form action="apagar.jsp" method="get">
                        <input type="hidden" name="id" value="<%= f.getId() %>">
                        <button type="submit"><i class="bx bxs-trash" style="font-size: 24px; background-color: black; color: white; padding: 8px; border-radius: 4px;"></i>


</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="16"></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    
</body>
</html>