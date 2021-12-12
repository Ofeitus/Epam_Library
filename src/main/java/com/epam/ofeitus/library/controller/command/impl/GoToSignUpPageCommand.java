package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToSignUpPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return new CommandResult(Page.SIGN_UP_PAGE, RoutingType.FORWARD);
    }
}
