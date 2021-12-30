package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class SearchCopiesOfBooksCommand implements Command {
    Logger logger = LogManager.getLogger(SearchCopiesOfBooksCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);
        int inventoryId = Integer.parseInt(request.getParameter(RequestParameter.INVENTORY_ID));

        HttpSession session = request.getSession();

        session.setAttribute(SessionAttribute.URL, "/controller?command=search-copies-of-books&book-isbn=" + bookIsbn + "&inventory-id=" + inventoryId);

        try {
            List<CopyOfBook> copiesOfBooks = bookService.getCopiesOfBooksBySearchRequest(bookIsbn, inventoryId);
            request.setAttribute(RequestAttribute.COPIES_OF_BOOKS, copiesOfBooks);
            return new CommandResult(Page.INVENTORY_BOOK_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get copies of books by search request.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
