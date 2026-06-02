package controller;

import model.Funcionarios;
import service.FuncionariosService.Logar;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EntrarCommand implements Command {

    @Override
    public void executar(HttpServletRequest request,
                         HttpServletResponse response)
            throws Exception {

       String senha = request.getParameter("senhalogin");
                String idstring = request.getParameter("idlogin");
                String mensagem;

                if (senha == null || senha.isEmpty() || idstring == null || idstring.isEmpty()) {
                    mensagem = "ID e Senha devem ser preenchidos.";
                    response.sendRedirect("index.html?erro=" + URLEncoder.encode(mensagem, "UTF-8"));
                    return;
                }

                int ID;
                try {
                    ID = Integer.parseInt(idstring);
                } catch (NumberFormatException e) {
                    mensagem = "ID inválido. Digite apenas números.";
                    response.sendRedirect("index.html?erro=" + URLEncoder.encode(mensagem, "UTF-8"));
                    return;
                }

                Logar fs = new Logar();

                try {
                    Funcionarios f = fs.login(ID, senha);

                    HttpSession session = request.getSession();
                    session.setAttribute("nome", f.getNome());
                    session.setAttribute("id", f.getId());

                    request.getRequestDispatcher("inicio.jsp").forward(request, response);

                } catch (Exception e) {
                    response.sendRedirect("index.html?erro=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
                }

                return;
}
}
