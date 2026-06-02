package dao.RegistroPontoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import util.ConnectionFactory;

public class EntradaRegistrada {
        public boolean entradaRegistrada(int id_funcionario, LocalDate data) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement comando = con.prepareStatement("SELECT * FROM registro_ponto WHERE id_funcionario = ? AND data = ?");
        comando.setInt(1, id_funcionario);
        comando.setDate(2, java.sql.Date.valueOf(data));
        ResultSet rs = comando.executeQuery();
        boolean aux = rs.next();
        rs.close();
        comando.close();
        con.close();
        return aux;
    }
}
