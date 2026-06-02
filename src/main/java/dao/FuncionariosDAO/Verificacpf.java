package dao.FuncionariosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Funcionarios;
import util.ConnectionFactory;

public class Verificacpf {
    public boolean verificacpf(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement comando = con.prepareStatement("select * FROM funcionario WHERE cpf = ?");
        comando.setString(1, f.getCpf());
        ResultSet rs = comando.executeQuery();
        boolean aux = rs.next();
        rs.close();
        con.close();
        comando.close();
        return aux;
    }
}
