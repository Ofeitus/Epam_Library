package com.epam.ofeitus.library.controller.command;

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
