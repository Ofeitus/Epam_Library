package com.epam.ofeitus.library.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Result of {@link Command#execute(HttpServletRequest, HttpServletResponse)} containing page path and routing type
 */
public class CommandResult {
    private final String resource;
    private final RoutingType type;

    public CommandResult(String resource, RoutingType resultType) {
        this.resource = resource;
        type = resultType;
    }

    public RoutingType getRoutingType() {
        return type;
    }

    public String getResource() {
        return resource;
    }
}
