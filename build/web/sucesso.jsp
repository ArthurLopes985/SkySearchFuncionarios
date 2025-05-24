<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sucesso</title>
        <link rel="stylesheet" href="Sucesso.css">
    </head>
    <body>
        <div class="container">
        <div>
            <h1><%String msg = (String) request.getAttribute("msg");%></h1>
                <label> <%out.println(msg);%></label>
        </div>
        <br>
        <button class="sucesso" type = "button" onclick="window.location.href = 'inicio.jsp'" >VOLTAR </button>
        <div>
    </body>
</html>
