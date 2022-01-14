package com.epam.ofeitus.library.controller;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.factory.CommandFactory;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.LOCALE) == null) {
            session.setAttribute(SessionAttribute.LOCALE, "en");
        }

        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandFactory.getInstance().getCommand(commandName);

        if (commandName == null || command == null) {
            session.setAttribute(SessionAttribute.URL, "/controller?command=goto-404-page");
            request.getRequestDispatcher(Page.ERROR_404_PAGE).forward(request, response);
            return;
        }

        CommandResult result = command.execute(request, response);
        if (result.getResource().equals(Page.ERROR_500_PAGE)) {
            session.setAttribute(SessionAttribute.URL, "/controller?command=goto-500-page");
        }
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
