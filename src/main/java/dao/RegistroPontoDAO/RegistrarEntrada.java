package dao.RegistroPontoDAO;

import util.ConnectionFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import model.RegistroPonto;

public class RegistrarEntrada {

    public void registrarEntrada(RegistroPonto registro) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        CallableStatement comando = con.prepareCall("{CALL RegistrarEntrada(?, ?, ?)}");
        comando.setInt(1, registro.getIdFuncionario());
        comando.setDate(2, java.sql.Date.valueOf(registro.getData()));
        comando.setTime(3, java.sql.Time.valueOf(registro.getHoraEntrada()));
        comando.execute();
        comando.close();
        con.close();
    }
}
