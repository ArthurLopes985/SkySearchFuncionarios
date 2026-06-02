package service.RegistroPontoService;

import model.RegistroPonto;
import java.time.LocalDate;
import dao.RegistroPontoDAO.EntradaRegistrada;
import dao.RegistroPontoDAO.ObterRegistroPorFuncionarioEData;
import dao.RegistroPontoDAO.RegistrarSaida;
import dao.RegistroPontoDAO.SaidaRegistrada;

public class RegistraSaida {
    private EntradaRegistrada er;
    private ObterRegistroPorFuncionarioEData orpfd;
    private RegistrarSaida rs;
    private SaidaRegistrada sr;


    public RegistraSaida() {
    this.er = new EntradaRegistrada();
    this.orpfd = new ObterRegistroPorFuncionarioEData();
    this.rs = new RegistrarSaida();
    this.sr = new SaidaRegistrada();
}

    public void registrarSaida(int id_funcionario) throws Exception {
        LocalDate hoje = LocalDate.now();

        if(!er.entradaRegistrada(id_funcionario, hoje)) {
            throw new Exception("Nenhuma entrada registrada para hoje.");
        }

        if(sr.saidaRegistrada(id_funcionario, hoje)) {
            throw new Exception("Saída já registrada para hoje.");
        }

        RegistroPonto registroBanco = orpfd.obterRegistroPorFuncionarioEData(id_funcionario, hoje);
        RegistroPonto registro = new RegistroPonto.RegistroPontoBuilder()
        .comId(registroBanco.getId())
        .comFuncionario(registroBanco.getIdFuncionario())
        .comData(registroBanco.getData())
        .comHoraEntrada(registroBanco.getHoraEntrada())
        .comHoraSaida(java.time.LocalTime.now())
        .build();

        rs.registrarSaida(registro);
    }
}
