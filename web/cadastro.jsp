<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Novo</title>
        <link
href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
rel='stylesheet'>
        <link rel="stylesheet" href="Cadastrar.css">
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
    <body>
        <form action="FuncionariosController" method="POST">
            <div class="container">
            <div><h1>Cadastro</h1></div>
            <div>
            <label>Digite os dados abaixo do novo funcionário a ser cadastrado. </label>
            </div>
            <div class="input-box">
                
                <input placeholder="Nome" type="text" name="nomenovo" value="<%= request.getParameter("nomenovo") != null ? request.getParameter("nomenovo") : "" %>" required>
            </div>
            <div class="input-box">
                
                <input placeholder="CPF" type="text" name="cpfnovo" minlength="11" maxlength="11" value="<%= request.getParameter("cpfnovo") != null ? request.getParameter("cpfnovo") : "" %>" 
                       required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
            </div>
            <div class="input-box">
                
                <input placeholder="E-Mail" type="text" name="emailnovo" value="<%= request.getParameter("emailnovo") != null ? request.getParameter("emailnovo") : "" %>"required>
            </div>
            <div class="input-box">
                
                <input placeholder="Telefone" type="tel" name="telefonenovo" pattern = "\d{10, 11}" maxlength="11" value="<%= request.getParameter("telefonenovo") != null ? request.getParameter("telefonenovo") : "" %>"required
                       oninput="this.value = this.value.replace(/[^0-9]/g, '')">
            </div>
            <div class="combo-box">
                <label for = "genero">  </label>
                <select id="genero" name="generonovo" required>
                    <option value ="" disabled <%= request.getParameter("generonovo") == null ? "selected" : "" %>>Selecione o Genero</option>
                    <option value="Masculino"<%= "Masculino".equals(request.getParameter("generonovo")) ? "selected" : "" %>>Masculino</option>
                    <option value="Feminino"<%= "Feminino".equals(request.getParameter("generonovo")) ? "selected" : "" %>>Feminino</option>
                    <option value="Prefiro Não Dizer"<%= "Prefiro Não Dizer".equals(request.getParameter("generonovo")) ? "selected" : "" %>>Prefiro Não Dizer</option>
                </select>
            </div>
            <div class="date-box">
                <label>Data de Nascimento:</label>
                <input placeholder="Data de Nascimento" type="date" name="dtnascnovo" value="<%= request.getParameter("dtnascnovo") != null ? request.getParameter("dtnascnovo") : "" %>" required>
            </div>
            <div class="date-box">
                <label>Data de Admissão:</label>
                <input placeholder="Data de Admissão" type="date" name="dtadmnovo" value="<%= request.getParameter("dtadmnovo") != null ? request.getParameter("dtadmnovo") : "" %>" required>
            </div>
            <div class="input-box">
                
                <input placeholder="Setor" type="text" name="setornovo" value="<%= request.getParameter("setornovo") != null ? request.getParameter("setornovo") : "" %>" required>
            </div>
            <div class="input-box">
                
                <input placeholder="Cargo" type="text" name="cargonovo" value="<%= request.getParameter("cargonovo") != null ? request.getParameter("cargonovo") : "" %>" required>
            </div>
            <div class="input-box">
                
                <input placeholder="Salario" type="number" name="salarionovo" step="0.1" value="<%= request.getParameter("salarionovo") != null ? request.getParameter("salarionovo") : "" %>" required>
            </div>
            <div class="input-box">
                
                <input placeholder="Endereco" type="text" name="endereconovo" value="<%= request.getParameter("endereconovo") != null ? request.getParameter("endereconovo") : "" %>" required>
            </div>
            <br>
            <label>
                As senhas precisam ser iguais, terem entre 5 e 16 caracteres e não podem conter - _ ; e espaço.
            </label >
            <div class="input-box">
                
                <input placeholder="Senha" type="password" name="senhanova" minlength="5" maxlength="16"  pattern="[^ _;\-]*" title="Não são permitidos os caracteres -,_,; e espaço " value="<%= request.getParameter("senhanova") != null ? request.getParameter("senhanova") : "" %>" required>
                <i class= "bx bxs-lock-alt"></i>
            </div>
            <div class="input-box">
                
                <input placeholder="Confirme a Senha" type="password" name="senhaconfirma" minlength="5" maxlength="16"  pattern="[^ _;\-]*" title="Não são permitidos os caracteres -,_,; e espaço " value="<%= request.getParameter("senhaconfirma") != null ? request.getParameter("senhaconfirma") : "" %>" required>
                <i class= "bx bxs-lock-alt"></i>
            </div>
            <br>
            <input type="submit" name="op" value="SALVAR">
            <button class="voltar" type = "button" onclick="window.location.href='inicio.jsp'" >VOLTAR </button>
            <div>
        </form>
    </body>
</html>
