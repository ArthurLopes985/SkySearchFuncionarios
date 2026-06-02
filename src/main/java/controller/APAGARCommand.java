package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.FuncionariosService.Apagar;

public class APAGARCommand implements Command {

    @Override
    public void executar(HttpServletRequest request,
                         HttpServletResponse response)
            throws Exception {

            String idString = request.getParameter("idlogin");
            String senha = request.getParameter("senhalogin");

            if (idString == null || idString.isEmpty() || senha == null || senha.isEmpty()) {
                request.setAttribute("erro", "ID e senha devem ser preenchidos.");
                request.getRequestDispatcher("apagar.jsp?id=" + idString).forward(request, response);
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idString);
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "ID inválido.");
                request.getRequestDispatcher("apagar.jsp?id=0").forward(request, response);
                return;
            }

            Apagar service = new Apagar();

            try {
                boolean apagado = service.apagarFuncionario(id, senha);

                if (apagado) {
                    request.setAttribute("msg", "Funcionário excluído com sucesso.");
                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);
                } else {
                    request.setAttribute("erro", "Senha incorreta.");
                    request.getRequestDispatcher("apagar.jsp?id=" + id).forward(request, response);
                }

            } catch (Exception e) {
                request.setAttribute("msg", "Erro ao excluir funcionário.");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }
}
