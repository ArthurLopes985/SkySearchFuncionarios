package service.FuncionariosService;

import dao.FuncionariosDAO.Cadastrar;
import dao.FuncionariosDAO.Verificacpf;
import model.Funcionarios;

public class Cadastro {

    private Cadastrar c;
    private Verificacpf vc;


    public Cadastro() {
        this.c = new Cadastrar();
        this.vc = new Verificacpf();
    }

    public Funcionarios cadastrar(Funcionarios f, String senhaConfirmacao) throws Exception {

    if (f.getCpf() == null || f.getCpf().length() != 11) {
        throw new Exception("O CPF deve possuir 11 digitos.");
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

    if (vc.verificacpf(f)) {
        throw new Exception("Usuario já existente, CPF já cadastrado.");
    }

    f= c.cadastrar(f);
    return f;
}
}
