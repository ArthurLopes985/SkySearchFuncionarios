package dao.FuncionariosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Endereco;
import model.Funcionarios;
import util.ConnectionFactory;

public class ListarTodos {
    public List<Funcionarios> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement comando = con.prepareStatement(" SELECT f.*, e.cep, e.rua, e.bairro, e.cidade FROM Funcionario f JOIN Endereco e ON f.endereco_id = e.id");
        ResultSet rs = comando.executeQuery();

        List<Funcionarios> lista = new ArrayList<Funcionarios>();
        while (rs.next()) {
            Endereco endereco = new Endereco.EnderecoBuilder()
                        .comCep(rs.getString("cep"))
                        .comRua(rs.getString("rua"))
                        .comBairro(rs.getString("bairro"))
                        .comCidade(rs.getString("cidade"))
                        .build();

            Funcionarios fun = new Funcionarios.FuncionariosBuilder()
                        .comId(rs.getInt("id"))
                        .comNome(rs.getString("nome"))
                        .comCpf(rs.getString("cpf"))
                        .comEmail(rs.getString("email"))
                        .comTelefone(rs.getString("telefone"))
                        .comGenero(rs.getString("genero"))
                        .comDataNasc(rs.getObject("data_nasc", LocalDate.class))
                        .comDataAdmissao(rs.getObject("data_admissao", LocalDate.class))
                        .comSetor(rs.getString("setor"))
                        .comCargo(rs.getString("cargo"))
                        .comSalario(rs.getDouble("salario"))
                        .comEndereco(endereco)
                        .comPericulosidade(rs.getBoolean("periculosidade"))
                        .comNoturno(rs.getBoolean("noturno"))
                        .build();
            lista.add(fun);
        }
        rs.close();
        comando.close();
        con.close();
        return lista;
    }
}
