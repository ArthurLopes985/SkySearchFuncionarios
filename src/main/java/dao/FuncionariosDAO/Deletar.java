package dao.FuncionariosDAO;

import java.sql.Connection;
import java.sql.SQLException;
import model.Funcionarios;
import util.ConnectionFactory;
import java.sql.CallableStatement;

public class Deletar {
        public void deletar(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        CallableStatement comando = con.prepareCall("{CALL Apagar(?)}");
        comando.setInt(1, f.getId());
        comando.execute();
        con.close();
    }
}
