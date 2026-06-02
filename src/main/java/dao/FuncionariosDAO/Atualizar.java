package dao.FuncionariosDAO;

import java.sql.Connection;
import java.sql.SQLException;
import model.Funcionarios;
import util.ConnectionFactory;
import java.sql.CallableStatement;

public class Atualizar {
    public void atualizar(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        CallableStatement comando = con.prepareCall("{CALL Editar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        comando.setInt(1, f.getId());
        comando.setString(2, f.getNome());
        comando.setString(3, f.getSenha());
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
        con.close();
    }
}
