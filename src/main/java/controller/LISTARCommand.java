package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LISTARCommand implements Command {

    @Override
    public void executar(HttpServletRequest request,
                         HttpServletResponse response)
            throws Exception {

        request.getRequestDispatcher("lista.jsp")
                .forward(request, response);
    }
}