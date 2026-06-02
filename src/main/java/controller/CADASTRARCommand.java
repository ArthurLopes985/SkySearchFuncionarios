package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CADASTRARCommand implements Command {

    @Override
    public void executar(HttpServletRequest request,
                         HttpServletResponse response)
            throws Exception {

        request.getRequestDispatcher("cadastro.jsp")
                .forward(request, response);
    }
}
