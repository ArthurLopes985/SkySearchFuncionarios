package service.FuncionariosService;

import java.sql.SQLException;
import dao.FuncionariosDAO.Deletar;
import dao.FuncionariosDAO.Login;
import model.Funcionarios;

public class Apagar {

    private Deletar d;


    public Apagar() {
        this.d = new Deletar();
    }
    public boolean apagarFuncionario(int id, String senha) throws ClassNotFoundException, SQLException {

    Login fdao = new Login();

    Funcionarios f = new Funcionarios.FuncionariosBuilder()
    .comId(id)
    .build();

    f = fdao.login(f);

    if (f == null || f.getSenha() == null || !f.getSenha().equals(senha)) {
        return false;
    }
    
    d.deletar(f);

    return true;
}
}
