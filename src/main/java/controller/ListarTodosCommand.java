package controller;

import model.Funcionarios;
import service.FuncionariosService.ListaTodos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarTodosCommand
        implements Command {

    @Override
    public void executar(HttpServletRequest request,
                         HttpServletResponse response)
            throws Exception {

        ListaTodos fs = new ListaTodos();
        String mensagem;

                try {
                    List<Funcionarios> lista = fs.listarTodos();

                    request.setAttribute("funcionarios", lista);
                    request.getRequestDispatcher("lista.jsp").forward(request, response);

                } catch (Exception ex) {
                    mensagem = "Desculpe-nos, houve um erro ao tentar listar.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }

                return;
            }
}

