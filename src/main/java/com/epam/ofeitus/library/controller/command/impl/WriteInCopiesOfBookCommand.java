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

public class WriteInCopiesOfBookCommand implements Command {
    Logger logger = LogManager.getLogger(WriteInCopiesOfBookCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-inventory-book-page");

        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);
        int copiesCount = Integer.parseInt(request.getParameter(RequestParameter.COPIES_COUNT));

        BookService bookService = ServiceFactory.getInstance().getBookService();
        try {
            bookService.addCopiesOfBook(bookIsbn, copiesCount);
            return new CommandResult("/controller?command=goto-inventory-book-page", RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Unable to add copies of book.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
