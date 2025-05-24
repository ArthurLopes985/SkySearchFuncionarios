<%@page import="Model.Funcionarios"%>
<%@page import="DAO.FuncionariosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% String idstring = request.getParameter("id");
    Funcionarios f = null;
    
if(idstring != null){
int id = Integer.parseInt(idstring);
FuncionariosDAO fdao = new FuncionariosDAO();
f = fdao.PreencherPorID(id);
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar</title>
        <link
href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
rel='stylesheet'>
        <link rel="stylesheet" href="Editar.css">
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
            <h1>Editar Funcionário</h1>
            <label>ID e CPF não podem ser alterados.</label>
            <br>
            
            <div>
                
                <input placeholder="ID" type="number" name="idlogin" value="<%= f.getId() %>" readonly>
            </div>
            <div>
                
                <input placeholder="CPF" type="text" name="cpfnovo"value="<%= f.getCpf()%>" readonly>
            </div>
                       <br>
                       <label>Digite a senha atual do Funcionário(a) <%= f.getNome()%> para validar edição.</label>
                       <br>
            <div>
                
                <input placeholder="Senha" type="password" name="senhalogin" minlength="5" maxlength="16" pattern="[^ _;\-]*" title="Não são permitidos os caracteres -,_,; e espaço " required>
            </div>
            <div>
                <br>
            <label>Digite os novos dados do funcionário(a) <%= f.getNome()%>.</label>
            </div>
            <div>
                
                <input placeholder="Nome" type="text" name="nomenovo" value="<%= f.getNome()%>"required>
            </div>
            <div>
                
                <input placeholder="E-mail" type="text" name="emailnovo" value="<%= f.getEmail()%>"required>
            </div>
            <div>
                
                <input placeholder="Telefone" type="tel" name="telefonenovo" pattern = "\d{10, 11}" maxlength="11" value="<%= f.getTelefone()%>"required
                       oninput="this.value = this.value.replace(/[^0-9]/g, '')">
            </div>
            <div>
                <label placeholder="Genero" for = "genero">  </label>
                <select id="genero" name="generonovo" required>
                    <option value ="" disabled <%= f.getGenero() == null ? "selected" : "" %>>Selecione</option>
                    <option value="Masculino"<%= "Masculino".equals(f.getGenero()) ? "selected" : "" %>>Masculino</option>
                    <option value="Feminino"<%= "Feminino".equals(f.getGenero()) ? "selected" : "" %>>Feminino</option>
                    <option value="Prefiro Não Dizer"<%= "Prefiro Não Dizer".equals(f.getGenero()) ? "selected" : "" %>>Prefiro Não Dizer</option>
                </select>
            </div>
            <div>
                <label>Data de Nascimento: </label>
                <input placeholder="Data de Nascimento" type="date" name="dtnascnovo" value="<%= f.getData_nasc()%>" required>
            </div>
            <div>
                <label>Data de Admissão: </label>
                <input placeholder="Data de Admissão" type="date" name="dtadmnovo" value="<%= f.getData_admissao()%>" required>
            </div>
            <div>
                
                <input placeholder="Setor" type="text" name="setornovo" value="<%= f.getSetor()%>" required>
            </div>
            <div>
                
                <input placeholder="Cargo" type="text" name="cargonovo" value="<%= f.getCargo()%>" required>
            </div>
            <div>
                
                <input placeholder="Salario" type="number" name="salarionovo" step="0.1" value="<%= f.getSalario()%>" required>
            </div>
            <div>
             
                <input placeholder="Endereço" type="text" name="endereconovo" value="<%= f.getEndereco()%>" required>
            </div>
            <br>
            <label>
                As senhas precisam ser iguais, terem entre 5 e 16 caracteres e não podem conter - _ ; e espaço.
            </label>
            <div>
                
                <input placeholder="Senha" type="password" name="senhanova" minlength="5" maxlength="16"  pattern="[^ _;\-]*" title="Não são permitidos os caracteres -,_,; e espaço " required>
            </div>
            <div>
                
                <input placeholder="Confirme a Senha" type="password" name="senhaconfirma" minlength="5" maxlength="16"  pattern="[^ _;\-]*" title="Não são permitidos os caracteres -,_,; e espaço " required>
            </div>
            <br>
            <input  type="submit" name="op" value="EDITAR">
            <button class="cancelar" type = "button" onclick="window.location.href='inicio.jsp'" >CANCELAR </button>
            </div>
        </form>
    </body>
</html> 
