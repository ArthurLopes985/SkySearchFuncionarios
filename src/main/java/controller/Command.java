package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    void executar(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
