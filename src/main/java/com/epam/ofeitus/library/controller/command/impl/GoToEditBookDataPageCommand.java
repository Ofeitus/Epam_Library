package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;
import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToEditBookDataPageCommand implements Command {
    Logger logger = LogManager.getLogger(GoToEditBookDataPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        BookService bookService = ServiceFactory.getInstance().getBookService();

        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);

        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-edit-book-data-page&book-isbn=" + bookIsbn);

        try {
            BookDto book = bookService.getBookDtoByIsbn(bookIsbn);
            request.setAttribute(RequestAttribute.BOOK, book);
            List<BookCategory> bookCategories = bookService.getBookCategories();
            request.setAttribute(RequestAttribute.BOOK_CATEGORIES, bookCategories);
            return new CommandResult(Page.EDIT_BOOK_DATA_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get book DTO.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
