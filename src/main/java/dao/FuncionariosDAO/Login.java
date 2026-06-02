package dao.FuncionariosDAO;

import model.Funcionarios;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public Funcionarios login(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement comando = con.prepareStatement("select * FROM funcionario WHERE Id = ?");
        comando.setInt(1, f.getId());
        ResultSet rs = comando.executeQuery();

        Funcionarios resultado = null;

        if (rs.next()) {
            resultado = new Funcionarios.FuncionariosBuilder()
            .comId(rs.getInt("Id"))
            .comSenha(rs.getString("Senha"))
            .comNome(rs.getString("nome"))
            .build();
        }
        rs.close();
        comando.close();
        con.close();

        return resultado;
    }
}
