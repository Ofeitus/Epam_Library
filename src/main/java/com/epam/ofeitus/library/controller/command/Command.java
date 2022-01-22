package com.epam.ofeitus.library.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet command interface.
 */
public interface Command {
    /**
     * Method is called by servlet when processing request.
     *
     * @param request servlet request
     * @param response servlet response
     * @return the command result, that contains page path and routing type
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response);
}
