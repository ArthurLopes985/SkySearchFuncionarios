package model;
 
import java.time.LocalDate;
import java.util.List;
 
public class Funcionarios {
 
    private final int id;
    private final String nome;
    private final String senha;
    private final String cpf;
    private final String email;
    private final String telefone;
    private final String genero;
    private final LocalDate dataNasc;
    private final LocalDate dataAdmissao;
    private final String setor;
    private final String cargo;
    private final double salario;
    private final Endereco endereco;
    private final List<RegistroPonto> registros;
    private final boolean periculosidade;
    private final boolean noturno;
 
    private Funcionarios(FuncionariosBuilder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.senha = builder.senha;
        this.cpf = builder.cpf;
        this.email = builder.email;
        this.telefone = builder.telefone;
        this.genero = builder.genero;
        this.dataNasc = builder.dataNasc;
        this.dataAdmissao = builder.dataAdmissao;
        this.setor = builder.setor;
        this.cargo = builder.cargo;
        this.salario = builder.salario;
        this.endereco = builder.endereco;
        this.registros = builder.registros;
        this.periculosidade = builder.periculosidade;
        this.noturno = builder.noturno;
    }
 
    public int getId() {
        return id;
    }
 
    public String getNome() {
        return nome;
    }
 
    public String getSenha() {
        return senha;
    }
 
    public String getCpf() {
        return cpf;
    }
 
    public String getEmail() {
        return email;
    }
 
    public String getTelefone() {
        return telefone;
    }
 
    public String getGenero() {
        return genero;
    }
 
    public LocalDate getDataNasc() {
        return dataNasc;
    }
 
    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }
 
    public String getSetor() {
        return setor;
    }
 
    public String getCargo() {
        return cargo;
    }
 
    public double getSalario() {
        return salario;
    }
 
    public Endereco getEndereco() {
        return endereco;
    }
 
    public List<RegistroPonto> getRegistros() {
        return registros;
    }
 
    public boolean isPericulosidade() {
        return periculosidade;
    }
 
    public boolean isNoturno() {
        return noturno;
    }
 
    public static class FuncionariosBuilder {
 
        private int id;
        private String nome;
        private String senha;
        private String cpf;
        private String email;
        private String telefone;
        private String genero;
        private LocalDate dataNasc;
        private LocalDate dataAdmissao;
        private String setor;
        private String cargo;
        private double salario;
        private Endereco endereco;
        private List<RegistroPonto> registros;
        private boolean periculosidade;
        private boolean noturno;
 
        public FuncionariosBuilder comId(int id) {
            this.id = id;
            return this;
        }
 
        public FuncionariosBuilder comNome(String nome) {
            this.nome = nome;
            return this;
        }
 
        public FuncionariosBuilder comSenha(String senha) {
            this.senha = senha;
            return this;
        }
 
        public FuncionariosBuilder comCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }
 
        public FuncionariosBuilder comEmail(String email) {
            this.email = email;
            return this;
        }
 
        public FuncionariosBuilder comTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }
 
        public FuncionariosBuilder comGenero(String genero) {
            this.genero = genero;
            return this;
        }
 
        public FuncionariosBuilder comDataNasc(LocalDate dataNasc) {
            this.dataNasc = dataNasc;
            return this;
        }
 
        public FuncionariosBuilder comDataAdmissao(LocalDate dataAdmissao) {
            this.dataAdmissao = dataAdmissao;
            return this;
        }
 
        public FuncionariosBuilder comSetor(String setor) {
            this.setor = setor;
            return this;
        }
 
        public FuncionariosBuilder comCargo(String cargo) {
            this.cargo = cargo;
            return this;
        }
 
        public FuncionariosBuilder comSalario(double salario) {
            this.salario = salario;
            return this;
        }
 
        public FuncionariosBuilder comEndereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }
 
        public FuncionariosBuilder comRegistros(List<RegistroPonto> registros) {
            this.registros = registros;
            return this;
        }
 
        public FuncionariosBuilder comPericulosidade(boolean periculosidade) {
            this.periculosidade = periculosidade;
            return this;
        }
 
        public FuncionariosBuilder comNoturno(boolean noturno) {
            this.noturno = noturno;
            return this;
        }
 
        public Funcionarios build() {
            return new Funcionarios(this);
        }
    }
}
 
