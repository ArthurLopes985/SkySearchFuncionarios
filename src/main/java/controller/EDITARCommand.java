package controller;

import model.Endereco;
import model.Funcionarios;
import service.FuncionariosService.Editar;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EDITARCommand implements Command {

    @Override
    public void executar(HttpServletRequest request,
                         HttpServletResponse response)
            throws Exception {

                String mensagem;
                try {
                    String senhaLogin = request.getParameter("senhalogin");
                    String idstring = request.getParameter("idlogin");

                    if (senhaLogin == null || senhaLogin.isEmpty() || idstring == null || idstring.isEmpty()) {
                        request.setAttribute("erro", "A Senha deve ser preenchida.");
                        request.getRequestDispatcher("editar.jsp?id=" + idstring).forward(request, response);
                        return;
                    }

                    int ID;
                    try {
                        ID = Integer.parseInt(idstring);
                    } catch (NumberFormatException e) {
                        request.setAttribute("erro", "ID inválido.");
                        request.getRequestDispatcher("editar.jsp?id=" + idstring).forward(request, response);
                        return;
                    }

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
                    String senhaNova = request.getParameter("senhanova");
                    String senhaConfirmacao = request.getParameter("senhaconfirma");
                    boolean periculosidade = request.getParameter("periculosidade")!= null;
                    boolean noturno = request.getParameter("noturno")!= null;

                    Endereco endereco = new Endereco.EnderecoBuilder()
                            .comCep(cep)
                            .comRua(rua)
                            .comBairro(bairro)
                            .comCidade(cidade)
                            .build();

                    Funcionarios f = new Funcionarios.FuncionariosBuilder()
                            .comId(ID)
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
                            .comSenha(senhaNova)
                            .comPericulosidade(periculosidade)
                            .comNoturno(noturno)
                            .build();

                    Editar fs = new Editar();
                    fs.editar(f, senhaLogin, senhaConfirmacao);

                    mensagem = "Funcionário atualizado com sucesso!<br>ID: " + f.getId();

                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);

                } catch (Exception e) {
                    request.setAttribute("erro", e.getMessage());
                    request.getRequestDispatcher("editar.jsp?id=" + request.getParameter("idlogin")).forward(request, response);
                }

                return;
            }
}
