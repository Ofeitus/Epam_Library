package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-home-page");
        session.removeAttribute(SessionAttribute.USER_ID);
        session.removeAttribute(SessionAttribute.USER_NAME);
        session.removeAttribute(SessionAttribute.USER_SURNAME);
        session.removeAttribute(SessionAttribute.USER_EMAIL);
        session.removeAttribute(SessionAttribute.USER_ROLE);
        return new CommandResult(Page.HOME_PAGE, RoutingType.FORWARD);
    }
}
