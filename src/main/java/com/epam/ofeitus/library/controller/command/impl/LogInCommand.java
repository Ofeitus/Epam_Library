package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
