package service.FuncionariosService;

import java.util.List;

import model.Funcionarios;
import dao.FuncionariosDAO.ConsultarPorId;

public class ListarPorId {

    private ConsultarPorId cpid;

    public ListarPorId() {
        this.cpid = new ConsultarPorId();
    }
    public List<Funcionarios> buscarPorId(int id) throws Exception {

        Funcionarios f = new Funcionarios.FuncionariosBuilder()
        .comId(id)
        .build();

        return cpid.consultarPorID(f);
    }
}
