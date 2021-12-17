package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToBookDetailsPageCommand implements Command {
    //Logger logger = LogManager.getLogger(GoToBookDetailsPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);

        try {
            BookDto book = bookService.getBookDtoByIsbn(bookIsbn);
            request.setAttribute(RequestAttribute.BOOK, book);
            return new CommandResult(Page.BOOK_DETAILS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            // TODO logger.error("Unable to fetch all users' DTOs. {}", e.getMessage());
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
