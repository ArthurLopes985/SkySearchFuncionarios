package dao.RegistroPontoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.RegistroPonto;
import util.ConnectionFactory;

public class ObterRegistroPorFuncionarioEData {
    public model.RegistroPonto obterRegistroPorFuncionarioEData(int id_funcionario, LocalDate data)
            throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement comando = con
                .prepareStatement("SELECT * FROM registro_ponto WHERE id_funcionario = ? AND data = ?");
        comando.setInt(1, id_funcionario);
        comando.setDate(2, java.sql.Date.valueOf(data));
        ResultSet rs = comando.executeQuery();

        model.RegistroPonto registro = null;
        if (rs.next()) {
            RegistroPonto.RegistroPontoBuilder builder = new RegistroPonto.RegistroPontoBuilder()
                    .comId(rs.getInt("id"))
                    .comFuncionario(rs.getInt("id_funcionario"))
                    .comData(rs.getDate("data").toLocalDate());

            java.sql.Time horaEntrada = rs.getTime("hora_entrada");
            if (horaEntrada != null) {
                builder.comHoraEntrada(horaEntrada.toLocalTime());
            }

            java.sql.Time horaSaida = rs.getTime("hora_saida");
            if (horaSaida != null) {
                builder.comHoraSaida(horaSaida.toLocalTime());
            }

            registro = builder.build();
        }
        rs.close();
        comando.close();
        con.close();
        return registro;
    }
}
