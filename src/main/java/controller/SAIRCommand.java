package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SAIRCommand implements Command {

    @Override
    public void executar(HttpServletRequest request,
                         HttpServletResponse response)
            throws Exception {

        request.getRequestDispatcher("index.html")
                .forward(request, response);
    }
}