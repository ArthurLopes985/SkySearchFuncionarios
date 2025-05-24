package Controller;

import DAO.FuncionariosDAO;
import Model.Funcionarios;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "FuncionariosController", urlPatterns = {"/FuncionariosController"})
public class FuncionariosController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String mensagem = "";
            String op = request.getParameter("op");

            if (op != null && op.equals("Entrar")) {
                String senha = request.getParameter("senhalogin");
                String idstring = request.getParameter("idlogin");

                if (senha == null || senha.isEmpty() || idstring == null || idstring.isEmpty()) {
                    mensagem = "ID e Senha devem ser preenchidos.";
                    request.setAttribute("msg", mensagem);
                    response.sendRedirect("index.html?erro=" + URLEncoder.encode(mensagem, "UTF-8"));
                    return;
                }
                int ID = 0;
                try {
                    ID = Integer.parseInt(idstring);
                } catch (NumberFormatException e) {
                    mensagem = "ID inválido. Digite apenas números.";
                    response.sendRedirect("index.html?erro=" + URLEncoder.encode(mensagem, "UTF-8"));
                    return;
                }
                Funcionarios f = new Funcionarios();
                f.setId(ID);

                FuncionariosDAO fdao = new FuncionariosDAO();
                try {
                    fdao.login(f);
                    String senhadb = f.getSenha();
                    if (senhadb != null && senha.equals(senhadb)) {
                        String nomelogado = f.getNome();
                        HttpSession session = request.getSession();
                        session.setAttribute("nome", nomelogado);
                        request.getRequestDispatcher("inicio.jsp").forward(request, response);
                    } else {
                        mensagem = "Senha e/ou id errados, tente novamente.";
                        response.sendRedirect("index.html?erro=" + URLEncoder.encode(mensagem, "UTF-8"));
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "Desculpe-nos, houve um erro ao tentar realizar login, tente novamente depois. ";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
            if (op != null && op.equals("CADASTRAR")) {
                request.getRequestDispatcher("cadastro.jsp").forward(request, response);
            }
            if (op != null && op.equals("LISTAR")) {
                request.getRequestDispatcher("lista.jsp").forward(request, response);
            }

            if (op != null && op.equals("Filtrar Por ID")) {
                String idstring = request.getParameter("idfiltrar");
                if (idstring == null || idstring.isEmpty()) {
                    request.setAttribute("erro", "ID deve ser preenchido.");
                    request.getRequestDispatcher("lista.jsp").forward(request, response);
                    return;
                }
                int ID = 0;
                try {
                    ID = Integer.parseInt(idstring);
                } catch (NumberFormatException e) {
                    request.setAttribute("erro", "ID deve conter apenas números.");
                    request.getRequestDispatcher("lista.jsp").forward(request, response);
                    return;
                }
                Funcionarios f = new Funcionarios();
                f.setId(ID);

                FuncionariosDAO fdao = new FuncionariosDAO();

                try {
                    List<Funcionarios> listaid = fdao.consultarPorID(f);
                    request.setAttribute("funcionarios", listaid);
                    request.getRequestDispatcher("lista.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "Desculpe-nos, houve um erro ao tentar realizar listar, tente novamente depois. ";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
            if (op != null && op.equals("Listar Todos")) {
                try {
                    FuncionariosDAO dao = new FuncionariosDAO();
                    List<Funcionarios> lista = dao.consultarTodos();
                    request.setAttribute("funcionarios", lista);
                    request.getRequestDispatcher("lista.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "Desculpe-nos, houve um erro ao tentar realizar listar, tente novamente depois. ";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
            if (op != null && op.equals("SAIR")) {
                request.getRequestDispatcher("index.html").forward(request, response);
            }
            if (op != null && op.equals("SALVAR")) {
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
                String endereco = request.getParameter("endereconovo");
                String senha = request.getParameter("senhanova");
                String senhaconfirma = request.getParameter("senhaconfirma");

                if (cpf != null && cpf.length() == 11) {
                    if (telefone != null && telefone.length() == 11) {
                        if (senha.length() >= 5 && senha.length() <= 16) {
                            if (senha != null && senhaconfirma != null && senha.equals(senhaconfirma)) {
                                Funcionarios f = new Funcionarios();
                                f.setNome(nome);
                                f.setCpf(cpf);
                                f.setEmail(email);
                                f.setTelefone(telefone);
                                f.setGenero(genero);
                                f.setData_nasc(data_nasc);
                                f.setData_admissao(data_adm);
                                f.setSetor(setor);
                                f.setCargo(cargo);
                                f.setSalario(salario);
                                f.setEndereco(endereco);
                                f.setSenha(senha);

                                FuncionariosDAO fdao = new FuncionariosDAO();
                                try {
                                    boolean aux = fdao.verificacpf(f);
                                    if (aux == false) {
                                        try {
                                            fdao.cadastrar(f);
                                            mensagem = "O seguinte funcionário foi cadastrado:" + "<br><br>"
                                                    + "Nome.......: " + f.getNome() + "<br>"
                                                    + "CPF........: " + f.getCpf() + "<br>"
                                                    + "Email......: " + f.getEmail() + "<br>"
                                                    + "Telefone...: " + f.getTelefone() + "<br>"
                                                    + "Gênero.....: " + f.getGenero() + "<br>"
                                                    + "Nascimento.: " + f.getData_nasc() + "<br>"
                                                    + "Admissão...: " + f.getData_admissao() + "<br>"
                                                    + "Setor......: " + f.getSetor() + "<br>"
                                                    + "Cargo......: " + f.getCargo() + "<br>"
                                                    + "Salário....: R$ " + f.getSalario() + "<br>"
                                                    + "Endereço...: " + f.getEndereco() + "<br><br>"
                                                    + "Com o seguinte ID.: " + f.getId() + "<br><br>"
                                                    + "<b>IMPORTANTE:</b> SALVAR ID E SENHA PARA FUTUROS LOGINS.";
                                            request.setAttribute("msg", mensagem);
                                            request.getRequestDispatcher("sucesso.jsp").forward(request, response);
                                        } catch (ClassNotFoundException | SQLException ex) {
                                            mensagem = "Desculpe-nos, houve um erro ao tentar realizar cadastrar, tente novamente depois. ";
                                            request.setAttribute("msg", mensagem);
                                            request.getRequestDispatcher("erro.jsp").forward(request, response);
                                        }
                                    } else {
                                        request.setAttribute("erro", "Usuario já existente, CPF já cadastrado. Tente Novamente.");
                                        request.getRequestDispatcher("cadastro.jsp").forward(request, response);
                                        return;
                                    }
                                } catch (ClassNotFoundException | SQLException ex) {
                                    mensagem = "Desculpe-nos, houve um erro ao tentar realizar cadastrar, tente novamente depois. ";
                                    request.setAttribute("msg", mensagem);
                                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                                }
                            } else {
                                request.setAttribute("erro", "As senhas não conferem. Tente Novamente.");
                                request.getRequestDispatcher("cadastro.jsp").forward(request, response);
                                return;
                            }
                        } else {
                            request.setAttribute("erro", "A senha deve ter entre 5 e 16 caracteres. Tente Novamente.");
                            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
                            return;
                        }
                    } else {
                        request.setAttribute("erro", "O telefone deve possuir 11 digitos. Tente Novamente.");
                        request.getRequestDispatcher("cadastro.jsp").forward(request, response);
                        return;
                    }
                } else {
                    request.setAttribute("erro", "O CPF deve possuir 11 digitos. Tente Novamente.");
                    request.getRequestDispatcher("cadastro.jsp").forward(request, response);
                    return;
                }
            }
            if (op != null && op.equals("EDITAR")) {
                String senha = request.getParameter("senhalogin");
                String idstring = request.getParameter("idlogin");

                if (senha == null || senha.isEmpty() || idstring == null || idstring.isEmpty()) {
                    request.setAttribute("erro", "A Senha deve ser preenchida. Tente Novamente.");
                    request.getRequestDispatcher("editar.jsp?id=" + idstring).forward(request, response);
                    return;
                }
                int ID = 0;
                try {
                    ID = Integer.parseInt(idstring);
                } catch (NumberFormatException e) {
                    request.setAttribute("erro", "ID inválido. Digite apenas números. Tente Novamente.");
                    request.getRequestDispatcher("editar.jsp?id="+ ID).forward(request, response);
                    return;
                }
                Funcionarios f = new Funcionarios();
                f.setId(ID);

                FuncionariosDAO fdao = new FuncionariosDAO();
                try {
                    fdao.login(f);
                    String senhadb = f.getSenha();
                    if (senhadb != null && senha.equals(senhadb)) {
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
                        String endereco = request.getParameter("endereconovo");
                        String senhaeditada = request.getParameter("senhanova");
                        String senhaconfirma = request.getParameter("senhaconfirma");
                        if (telefone != null && telefone.length() == 11) {
                            if (senhaeditada.length() >= 5 && senhaeditada.length() <= 16) {
                                if (senhaeditada != null && senhaconfirma != null && senhaeditada.equals(senhaconfirma)) {
                                    f.setId(ID);
                                    f.setNome(nome);
                                    f.setCpf(cpf);
                                    f.setEmail(email);
                                    f.setTelefone(telefone);
                                    f.setGenero(genero);
                                    f.setData_nasc(data_nasc);
                                    f.setData_admissao(data_adm);
                                    f.setSetor(setor);
                                    f.setCargo(cargo);
                                    f.setSalario(salario);
                                    f.setEndereco(endereco);
                                    f.setSenha(senhaeditada);

                                    try {
                                        fdao.atualizar(f);
                                        mensagem = "O seguinte funcionário foi atualizado:" + "<br><br>"
                                                + "ID.........: " + f.getId() + "<br>"
                                                + "Nome.......: " + f.getNome() + "<br>"
                                                + "CPF........: " + f.getCpf() + "<br>"
                                                + "Email......: " + f.getEmail() + "<br>"
                                                + "Telefone...: " + f.getTelefone() + "<br>"
                                                + "Gênero.....: " + f.getGenero() + "<br>"
                                                + "Nascimento.: " + f.getData_nasc() + "<br>"
                                                + "Admissão...: " + f.getData_admissao() + "<br>"
                                                + "Setor......: " + f.getSetor() + "<br>"
                                                + "Cargo......: " + f.getCargo() + "<br>"
                                                + "Salário....: R$ " + f.getSalario() + "<br>"
                                                + "Endereço...: " + f.getEndereco() + "<br><br>"
                                                + "<b>IMPORTANTE:</b> SALVAR A NOVA SENHA PARA FUTUROS LOGINS.";
                                        request.setAttribute("msg", mensagem);
                                        request.getRequestDispatcher("sucesso.jsp").forward(request, response);
                                    } catch (ClassNotFoundException | SQLException ex) {
                                        mensagem = "Desculpe-nos, houve um erro ao tentar realizar editar, tente novamente depois. ";
                                        request.setAttribute("msg", mensagem);
                                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                                    }
                                } else {
                                    request.setAttribute("erro", "As senhas não conferem. Tente Novamente.");
                                    request.getRequestDispatcher("editar.jsp?id=" + ID).forward(request, response);
                                    return;
                                }
                            } else {
                                request.setAttribute("erro", "A senha deve ter entre 5 e 16 caracteres. Tente Novamente.");
                                request.getRequestDispatcher("editar.jsp?id=" + ID).forward(request, response);
                                return;
                            }
                        } else {
                            request.setAttribute("erro", "O telefone deve possuir 11 digitos. Tente Novamente.");
                            request.getRequestDispatcher("editar.jsp?id=" + ID).forward(request, response);
                            return;
                        }
                    } else {
                        request.setAttribute("erro", "Senha errada. Tente Novamente.");
                        request.getRequestDispatcher("editar.jsp?id=" + ID).forward(request, response);
                        return;
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "Desculpe-nos, houve um erro ao tentar realizar editar, tente novamente depois. ";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
            
            if (op != null && op.equals("APAGAR")) {
                String senha = request.getParameter("senhalogin");
                String idstring = request.getParameter("idlogin");

                if (senha == null || senha.isEmpty() || idstring == null || idstring.isEmpty()) {
                    request.setAttribute("erro", "A Senha deve ser preenchida. Tente Novamente.");
                    request.getRequestDispatcher("apagar.jsp?id=" + idstring).forward(request, response);
                    return;
                }
                int ID = 0;
                try {
                    ID = Integer.parseInt(idstring);
                } catch (NumberFormatException e) {
                    request.setAttribute("erro", "ID inválido. Digite apenas números. Tente Novamente.");
                    request.getRequestDispatcher("apagar.jsp?id=" + ID).forward(request, response);
                    return;
                }
                Funcionarios f = new Funcionarios();
                f.setId(ID);

                FuncionariosDAO fdao = new FuncionariosDAO();
                try {
                    fdao.login(f);
                    String senhadb = f.getSenha();
                    if (senhadb != null && senha.equals(senhadb)) {
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
                        String endereco = request.getParameter("endereconovo");
                        f.setId(ID);
                        f.setNome(nome);
                        f.setCpf(cpf);
                        f.setEmail(email);
                        f.setTelefone(telefone);
                        f.setGenero(genero);
                        f.setData_nasc(data_nasc);
                        f.setData_admissao(data_adm);
                        f.setSetor(setor);
                        f.setCargo(cargo);
                        f.setSalario(salario);
                        f.setEndereco(endereco);

                        try {
                            fdao.deletar(f);
                            mensagem = "O seguinte funcionário foi excluido:" + "<br><br>"
                                    + "ID.........: " + f.getId() + "<br>"
                                    + "Nome.......: " + f.getNome() + "<br>"
                                    + "CPF........: " + f.getCpf() + "<br>"
                                    + "Email......: " + f.getEmail() + "<br>"
                                    + "Telefone...: " + f.getTelefone() + "<br>"
                                    + "Gênero.....: " + f.getGenero() + "<br>"
                                    + "Nascimento.: " + f.getData_nasc() + "<br>"
                                    + "Admissão...: " + f.getData_admissao() + "<br>"
                                    + "Setor......: " + f.getSetor() + "<br>"
                                    + "Cargo......: " + f.getCargo() + "<br>"
                                    + "Salário....: R$ " + f.getSalario() + "<br>"
                                    + "Endereço...: " + f.getEndereco() + "<br><br>";
                            request.setAttribute("msg", mensagem);
                            request.getRequestDispatcher("sucesso.jsp").forward(request, response);
                        } catch (ClassNotFoundException | SQLException ex) {
                            mensagem = "Desculpe-nos, houve um erro ao tentar realizar editar, tente novamente depois. ";
                            request.setAttribute("msg", mensagem);
                            request.getRequestDispatcher("erro.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("erro", "Senha errada. Tente Novamente.");
                        request.getRequestDispatcher("apagar.jsp?id=" + ID).forward(request, response);
                        return;
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    mensagem = "Desculpe-nos, houve um erro ao tentar realizar editar, tente novamente depois. ";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
            /*
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FuncionariosController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FuncionariosController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Controlador do gerencioador de funcionarios";
    }// </editor-fold>

}
