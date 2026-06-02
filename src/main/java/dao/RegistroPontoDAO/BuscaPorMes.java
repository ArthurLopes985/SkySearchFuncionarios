package dao.RegistroPontoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RegistroPonto;
import util.ConnectionFactory;

public class BuscaPorMes {
    public List<RegistroPonto> buscaporMes(int id_funcionario, int mes, int ano)
            throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement comando = con.prepareStatement(
                "SELECT * FROM registro_ponto WHERE id_funcionario = ? AND MONTH(data) = ? AND YEAR(data) = ?");
        comando.setInt(1, id_funcionario);
        comando.setInt(2, mes);
        comando.setInt(3, ano);
        ResultSet rs = comando.executeQuery();

        List<RegistroPonto> registros = new ArrayList<>();
        while (rs.next()) {
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

            RegistroPonto registro = builder.build();
            registros.add(registro);
        }
        rs.close();
        comando.close();
        con.close();
        return registros;
    }
}
