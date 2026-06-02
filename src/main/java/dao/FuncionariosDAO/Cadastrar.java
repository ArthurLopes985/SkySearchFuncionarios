package dao.FuncionariosDAO;

import model.Funcionarios;
import util.ConnectionFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cadastrar {
    public Funcionarios cadastrar(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        CallableStatement comando = con.prepareCall("{CALL Cadastrar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        comando.setString(1, f.getNome());
        comando.setString(2, f.getSenha());
        comando.setString(3, f.getCpf());
        comando.setString(4, f.getEmail());
        comando.setString(5, f.getTelefone());
        comando.setString(6, f.getGenero());
        comando.setDate(7, java.sql.Date.valueOf(f.getDataNasc()));
        comando.setDate(8, java.sql.Date.valueOf(f.getDataAdmissao()));
        comando.setString(9, f.getSetor());
        comando.setString(10, f.getCargo());
        comando.setDouble(11, f.getSalario());
        comando.setBoolean(12, f.isPericulosidade());
        comando.setBoolean(13, f.isNoturno());
        comando.setString(14, f.getEndereco().getCep());
        comando.setString(15, f.getEndereco().getRua());
        comando.setString(16, f.getEndereco().getBairro());
        comando.setString(17, f.getEndereco().getCidade());
        comando.execute();
        PreparedStatement comando2 = con.prepareStatement("select Id from Funcionario where cpf = ?");
        comando2.setString(1, f.getCpf());
        ResultSet rs = comando2.executeQuery();
        if (rs.next()) {
            f = new Funcionarios.FuncionariosBuilder()
            .comId(rs.getInt("Id"))
            .comNome(f.getNome())
            .comSenha(f.getSenha())
            .comCpf(f.getCpf())
            .comEmail(f.getEmail())
            .comTelefone(f.getTelefone())
            .comGenero(f.getGenero())
            .comDataNasc(f.getDataNasc())
            .comDataAdmissao(f.getDataAdmissao())
            .comSetor(f.getSetor())
            .comCargo(f.getCargo())
            .comSalario(f.getSalario())
            .comPericulosidade(f.isPericulosidade())
            .comNoturno(f.isNoturno())
            .comEndereco(f.getEndereco())
            .build();
        }
        rs.close();
        con.close();
        comando.close();
        comando2.close();
        return f;
    }
}
