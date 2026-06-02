package service.FuncionariosService;

import java.util.List;

import model.Funcionarios;
import dao.FuncionariosDAO.ListarTodos;

public class ListaTodos {

    private ListarTodos lt;

    public ListaTodos() {
        this.lt = new ListarTodos();
    }

    public List<Funcionarios> listarTodos() throws Exception {
        return lt.consultarTodos();
    }
}
