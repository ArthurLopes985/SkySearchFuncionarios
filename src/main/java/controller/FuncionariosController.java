package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FuncionariosController",
            urlPatterns = {"/FuncionariosController"})
            
public class FuncionariosController extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        try {

            CommandFactory factory =
                    new CommandFactory();

            Command command =
                    factory.getCommand(op);

            if (command == null) {
                throw new Exception("Comando inválido.");
            }

            command.executar(request, response);

        } catch (Exception e) {

            request.setAttribute("erro",
                    e.getMessage());

            request.getRequestDispatcher("erro.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
}