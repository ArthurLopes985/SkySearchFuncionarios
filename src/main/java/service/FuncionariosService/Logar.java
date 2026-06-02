package service.FuncionariosService;

import dao.FuncionariosDAO.Login;
import model.Funcionarios;

public class Logar {

    private Login l;

    public Logar() {
        this.l = new Login();
    }

    public Funcionarios login(int id, String senha) throws Exception {
        Funcionarios f = new Funcionarios.FuncionariosBuilder()
                .comId(id)
                .build();

        f = l.login(f);

        if (f == null) {
            throw new Exception("Funcionário não encontrado.");
        }

        if (f.getSenha() != null && f.getSenha().equals(senha)) {
            return f;
        } else {
            throw new Exception("Senha e/ou id errados, tente novamente.");
        }
    }
}
