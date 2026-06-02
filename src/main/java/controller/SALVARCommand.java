package controller;

import model.Endereco;
import model.Funcionarios;
import service.FuncionariosService.Cadastro;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SALVARCommand implements Command {

    @Override
    public void executar(HttpServletRequest request,
                         HttpServletResponse response)
            throws Exception {
                String mensagem;

        try {
                String nome = request.getParameter("nomenovo");
                String cpf = request.getParameter("cpfnovo");
                String email = request.getParameter("emailnovo");
                String telefone = request.getParameter("telefonenovo");
                String genero = request.getParameter("generonovo");
                LocalDate data_nasc = LocalDate.parse(request.getParameter("dtnascnovo"));
                LocalDate data_adm = LocalDate.parse(request.getParameter("dtadmnovo"));
                String setor = request.getParameter("setornovo");
                String cargo = request.getParameter("cargonovo");
                double salario = Double.parseDouble(request.getParameter("salarionovo"));
                String cep = request.getParameter("cepnovo");
                String rua = request.getParameter("ruanovo");
                String bairro = request.getParameter("bairronovo");
                String cidade = request.getParameter("cidadenovo");
                String senha = request.getParameter("senhanova");
                String senhaconfirma = request.getParameter("senhaconfirma");
                boolean periculosidade = request.getParameter("periculosidade")!= null;
                boolean noturno = request.getParameter("noturno")!= null;

                Endereco endereco = new Endereco.EnderecoBuilder()
                        .comCep(cep)
                        .comRua(rua)
                        .comBairro(bairro)
                        .comCidade(cidade)
                        .build();

                Funcionarios f = new Funcionarios.FuncionariosBuilder()
                        .comNome(nome)
                        .comCpf(cpf)
                        .comEmail(email)
                        .comTelefone(telefone)
                        .comGenero(genero)
                        .comDataNasc(data_nasc)
                        .comDataAdmissao(data_adm)
                        .comSetor(setor)
                        .comCargo(cargo)
                        .comSalario(salario)
                        .comEndereco(endereco)
                        .comSenha(senha)
                        .comPericulosidade(periculosidade)
                        .comNoturno(noturno)
                        .build();

                Cadastro fs = new Cadastro();
                f = fs.cadastrar(f, senhaconfirma);

            mensagem = "Funcionário cadastrado com sucesso!<br><br>"
                        + "Nome: " + f.getNome() + "<br>"
                        + "ID: " + f.getId();

                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("sucesso.jsp").forward(request, response);

            } catch (Exception e) {
                request.setAttribute("erro", e.getMessage());
                request.getRequestDispatcher("cadastro.jsp").forward(request, response);
            }

            return;
        }
}
