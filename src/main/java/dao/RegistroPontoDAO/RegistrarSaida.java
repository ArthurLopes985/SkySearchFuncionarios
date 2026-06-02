package dao.RegistroPontoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import model.RegistroPonto;
import util.ConnectionFactory;
import java.sql.CallableStatement;

public class RegistrarSaida {
        public void registrarSaida(RegistroPonto registro) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        CallableStatement comando = con.prepareCall("{CALL RegistrarSaida(?, ?, ?)}");
        comando.setInt(1, registro.getIdFuncionario());
        comando.setDate(2, java.sql.Date.valueOf(registro.getData()));
        comando.setTime(3, java.sql.Time.valueOf(registro.getHoraSaida()));
        comando.execute();
        comando.close();
        con.close();
    }
}
