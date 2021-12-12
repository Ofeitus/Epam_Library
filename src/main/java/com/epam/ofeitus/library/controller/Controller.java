package com.epam.ofeitus.library.controller;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.factory.CommandFactory;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        if (commandName == null) {
            request.getRequestDispatcher(Page.ERROR_404_PAGE).forward(request, response);
        }

        Command command = CommandFactory.getInstance().getCommand(commandName);
        CommandResult result = command.execute(request, response);

        String resource = result.getResource();
        switch (result.getRoutingType()) {
            case FORWARD:
                request.getRequestDispatcher(resource).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + resource);
                break;
            default:
                request.getRequestDispatcher(Page.ERROR_404_PAGE).forward(request, response);
        }
    }
}
