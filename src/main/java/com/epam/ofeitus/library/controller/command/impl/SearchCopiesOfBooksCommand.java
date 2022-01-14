package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.dto.CopyOfBookDto;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class SearchCopiesOfBooksCommand implements Command {
    Logger logger = LogManager.getLogger(SearchCopiesOfBooksCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);
        int inventoryId = 0;
        try {
            inventoryId = Integer.parseInt(request.getParameter(RequestParameter.INVENTORY_ID));
        } catch (NumberFormatException e) {
            logger.warn("Wrong search parameters.", e);
        }
        String status = request.getParameter(RequestParameter.STATUS);
        int statusId;
        switch (status) {
            case "only-existing":
                statusId = -5;
                break;
            case "only-written-off":
                statusId = 5;
                break;
            default:
                statusId = 0;
        }

        BookService bookService = ServiceFactory.getInstance().getBookService();

        int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));
        int itemsOnPage = 10;

        String command = "?command=search-copies-of-books" +
                         "&book-isbn=" + bookIsbn +
                         "&inventory-id=" + inventoryId +
                         "&status=" + status;
        session.setAttribute(SessionAttribute.URL, "/controller" + command + "&page=" + page);
        session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);

        try {
            List<CopyOfBookDto> copiesOfBooks = bookService.getCopiesOfBooksBySearchRequest(bookIsbn, inventoryId, statusId, page, itemsOnPage);
            int itemsCount = bookService.countCopiesOfBooksBySearchRequest(bookIsbn, inventoryId, statusId);
            int pagesCount = itemsCount / itemsOnPage;
            if (itemsCount % itemsOnPage != 0) {
                pagesCount++;
            }
            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.PAGES_COUNT, pagesCount);
            request.setAttribute(RequestAttribute.COPIES_OF_BOOKS, copiesOfBooks);
            return new CommandResult(Page.INVENTORY_BOOK_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get copies of books by search request.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
