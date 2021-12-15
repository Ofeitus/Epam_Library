package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToCatalogPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.getInstance().getBookService();
        try {
            List<BookDto> books = bookService.getAllBooksDto();
            request.setAttribute(RequestAttribute.BOOKS, books);
            return new CommandResult(Page.CATALOG_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            // TODO logger.error("Unable to fetch all users' DTOs. {}", e.getMessage());
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.REDIRECT);
        }
    }
}
