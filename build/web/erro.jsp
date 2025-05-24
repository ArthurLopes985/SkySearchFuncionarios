<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>erro</title>
    </head>
    <body>
        <div>
            <h1><%String msg = (String) request.getAttribute("msg");%></h1>
            <h1>ERRO</h1>
            <label><%out.println(msg);%></label>
        </div>
    </body>
</html>
