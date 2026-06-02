package controller;

import dao.FuncionariosDAO.PreencherPorId;
import dao.RegistroPontoDAO.BuscaPorMes;
import model.Funcionarios;
import model.HorasDevendo;
import model.HorasExtras;
import model.ISalario;
import model.Noturno;
import model.Periculosidade;
import model.RegistroPonto;
import model.SalarioBase;
import service.RegistroPontoService.RegistraEntrada;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EntradaCommand
        implements Command {

    @Override
    public void executar(HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        RegistraEntrada rps = new RegistraEntrada();
        BuscaPorMes bpm = new BuscaPorMes();
        PreencherPorId ppid = new PreencherPorId();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id") == null) {
            response.sendRedirect("index.html?erro=Faça login novamente");
            return;
        }

        int id = (int) session.getAttribute("id");
        try {
            rps.registrarEntrada(id);
            LocalDate hoje = LocalDate.now();
            int mes = hoje.getMonthValue();
            int ano = hoje.getYear();
            List<RegistroPonto> registros = bpm.buscaporMes(id, mes, ano);
            Funcionarios banco = ppid.PreencherPorID(id);
            Funcionarios f = new Funcionarios.FuncionariosBuilder()
                    .comId(banco.getId())
                    .comNome(banco.getNome())
                    .comCpf(banco.getCpf())
                    .comEmail(banco.getEmail())
                    .comTelefone(banco.getTelefone())
                    .comGenero(banco.getGenero())
                    .comDataNasc(banco.getDataNasc())
                    .comDataAdmissao(banco.getDataAdmissao())
                    .comSetor(banco.getSetor())
                    .comCargo(banco.getCargo())
                    .comSalario(banco.getSalario())
                    .comEndereco(banco.getEndereco())
                    .comPericulosidade(banco.isPericulosidade())
                    .comNoturno(banco.isNoturno())
                    .comEndereco(banco.getEndereco())
                    .comRegistros(registros)
                    .build();

            double totalExtras = 0;
            double totalDevendo = 0;

            for (RegistroPonto r : registros) {
                totalExtras += r.calcularHorasExtras();
                totalDevendo += r.calcularHorasDevendo();
            }

            ISalario salario = new SalarioBase(f.getSalario());

            salario = new HorasExtras(salario, totalExtras);

            salario = new HorasDevendo(salario, totalDevendo);

            if (f.isPericulosidade()) {
                salario = new Periculosidade(salario);
            }

            if (f.isNoturno()) {
                salario = new Noturno(salario);
            }

            double salarioFinal = salario.calcularsalario();

            request.setAttribute("registros", registros);
            request.setAttribute("funcionario", f);
            request.setAttribute("salarioFinal", salarioFinal);
            request.getRequestDispatcher("registroponto.jsp").forward(request, response);
            return;
        } catch (Exception ex) {
            response.sendRedirect("FuncionariosController?op=REGISTRARPONTO&erro="
                    + URLEncoder.encode(ex.getMessage(), "UTF-8"));
            return;
        }
    }
}
