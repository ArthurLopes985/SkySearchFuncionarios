package controller;

import model.Funcionarios;
import service.FuncionariosService.ListarPorId;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FiltrarPorIDCommand
        implements Command {

    @Override
    public void executar(HttpServletRequest request,
                         HttpServletResponse response)
            throws Exception {

        String idstring = request.getParameter("idfiltrar");
        String mensagem;

                if (idstring == null || idstring.isEmpty()) {
                    request.setAttribute("erro", "ID deve ser preenchido.");
                    request.getRequestDispatcher("lista.jsp").forward(request, response);
                    return;
                }

                int ID;
                try {
                    ID = Integer.parseInt(idstring);
                } catch (NumberFormatException e) {
                    request.setAttribute("erro", "ID deve conter apenas números.");
                    request.getRequestDispatcher("lista.jsp").forward(request, response);
                    return;
                }

                ListarPorId fs = new ListarPorId();

                try {
                    List<Funcionarios> listaid = fs.buscarPorId(ID);

                    request.setAttribute("funcionarios", listaid);
                    request.getRequestDispatcher("lista.jsp").forward(request, response);

                } catch (Exception ex) {
                    mensagem = "Desculpe-nos, houve um erro ao tentar listar.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }

                return;
            }
}
