package com.epam.ofeitus.library.controller.filter;

import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserSessionFilter implements Filter {
    private final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            Object userId = session.getAttribute(SessionAttribute.USER_ID);

            if (userId == null) {
                session.setAttribute(SessionAttribute.USER_ROLE, UserRole.GUEST);
            } else {
                UserRole role = (UserRole) session.getAttribute(SessionAttribute.USER_ROLE);

                User user = userService.getByUserId((int) userId);
                // User deleted or role changed case
                if (user == null || role != user.getUserRole() || user.isDeleted()) {
                    session.removeAttribute(SessionAttribute.USER_ID);
                    session.removeAttribute(SessionAttribute.USER_NAME);
                    session.removeAttribute(SessionAttribute.USER_SURNAME);
                    session.removeAttribute(SessionAttribute.USER_EMAIL);
                    session.setAttribute(SessionAttribute.USER_ROLE, UserRole.GUEST);
                    String url = "/controller?" +
                            RequestParameter.COMMAND + "=" + CommandName.GOTO_LOG_IN_PAGE_COMMAND;
                    session.setAttribute(SessionAttribute.URL, url);
                    response.sendRedirect(request.getContextPath() + url);
                    return;
                }
            }
        } catch (ServiceException | ClassCastException e) {
            logger.error("Unable to get user.", e);
            String url = "/controller?" +
                    RequestParameter.COMMAND + "=" + CommandName.GOTO_500_PAGE_COMMAND;
            session.setAttribute(SessionAttribute.URL, url);
            response.sendRedirect(request.getContextPath() + url);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
