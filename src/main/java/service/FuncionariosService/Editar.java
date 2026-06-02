package service.FuncionariosService;

import dao.FuncionariosDAO.Atualizar;
import dao.FuncionariosDAO.Login;
import model.Funcionarios;

public class Editar {

    private Login l;
    private Atualizar a;


    public Editar() {
        this.l = new Login();
        this.a = new Atualizar();
    }
    public void editar(Funcionarios f, String senhaLogin, String senhaConfirmacao) throws Exception {

    Funcionarios fBanco = new Funcionarios.FuncionariosBuilder()
    .comId(f.getId())
    .build();

    fBanco = l.login(fBanco);

    if (fBanco == null ||fBanco.getSenha() == null || !fBanco.getSenha().equals(senhaLogin)) {
        throw new Exception("Senha errada. Tente novamente.");
    }

    if (f.getTelefone() == null || f.getTelefone().length() != 11) {
        throw new Exception("O telefone deve possuir 11 digitos.");
    }

    if (f.getSenha() == null || f.getSenha().length() < 5 || f.getSenha().length() > 16) {
        throw new Exception("A senha deve ter entre 5 e 16 caracteres.");
    }

    if (!f.getSenha().equals(senhaConfirmacao)) {
        throw new Exception("As senhas não conferem.");
    }

    a.atualizar(f);
}
}
