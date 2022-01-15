package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class DeleteCopyOfBookCommand implements Command {
    Logger logger = LogManager.getLogger(DeleteCopyOfBookCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        BookService bookService = ServiceFactory.getInstance().getBookService();
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));

        String command = "?command=goto-inventory-book-page";
        session.setAttribute(SessionAttribute.URL, "/controller" + command + "&page=" + page);
        session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);

        int inventoryId = Integer.parseInt(request.getParameter(RequestParameter.INVENTORY_ID));
        try {
            bookService.deleteCopyOfBook(inventoryId);
            return new CommandResult("/controller" + command + "&page=" + page, RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Unable to delete copy of book.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
