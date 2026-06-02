package service.RegistroPontoService;

import java.time.LocalDate;

import model.RegistroPonto;
import dao.RegistroPontoDAO.EntradaRegistrada;
import dao.RegistroPontoDAO.RegistrarEntrada;

public class RegistraEntrada {

    private EntradaRegistrada er;
    private RegistrarEntrada re;

    public RegistraEntrada() {
        this.er = new EntradaRegistrada();
        this.re = new RegistrarEntrada();
    }
    public void registrarEntrada(int id_funcionario) throws Exception {
        LocalDate hoje = LocalDate.now();

        if(er.entradaRegistrada(id_funcionario, hoje)) {
            throw new Exception("Entrada já registrada para hoje.");
        }

        RegistroPonto registro = new RegistroPonto.RegistroPontoBuilder()
            .comFuncionario(id_funcionario)
            .comData(hoje)
            .comHoraEntrada(java.time.LocalTime.now())
            .build();

        re.registrarEntrada(registro);
    }
}
