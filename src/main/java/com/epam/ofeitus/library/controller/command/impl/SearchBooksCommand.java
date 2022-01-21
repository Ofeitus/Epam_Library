package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

public class SearchBooksCommand implements Command {
    private final Logger logger = LogManager.getLogger(SearchBooksCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String searchRequest = request.getParameter(RequestParameter.SEARCH_REQUEST);
        String category = request.getParameter(RequestParameter.CATEGORY);
        String authorName = request.getParameter(RequestParameter.AUTHOR_NAME);
        String authorSurname = request.getParameter(RequestParameter.AUTHOR_SURNAME);

        try {
            int yearFrom = Integer.parseInt(request.getParameter(RequestParameter.PUBLICATION_YEAR_FROM));
            int yearTo = Integer.parseInt(request.getParameter(RequestParameter.PUBLICATION_YEAR_TO));
            int page = Integer.parseInt(Optional.ofNullable(request.getParameter(RequestParameter.PAGE)).orElse("1"));
            int itemsOnPage = 8;

            String command = "?" + RequestParameter.COMMAND + "=" + CommandName.SEARCH_BOOKS_COMMAND +
                             "&" + RequestParameter.SEARCH_REQUEST + "=" + URLEncoder.encode(searchRequest, "UTF-8") +
                             "&" + RequestParameter.CATEGORY + "=" + URLEncoder.encode(category, "UTF-8") +
                             "&" + RequestParameter.AUTHOR_NAME + "=" + URLEncoder.encode(authorName, "UTF-8") +
                             "&" + RequestParameter.AUTHOR_SURNAME + "=" + URLEncoder.encode(authorSurname, "UTF-8") +
                             "&" + RequestParameter.PUBLICATION_YEAR_FROM + "=" + yearFrom +
                             "&" + RequestParameter.PUBLICATION_YEAR_TO + "=" + yearTo;
            session.setAttribute(SessionAttribute.URL, ("/controller" + command +
                    "&" + RequestParameter.PAGE + "=" + page));
            session.setAttribute(SessionAttribute.URL_WITHOUT_PAGE, command);

            List<BookDto> books = bookService.getBooksDtoBySearchRequest(searchRequest, category, authorName, authorSurname, yearFrom, yearTo, page, itemsOnPage);
            List<BookCategory> bookCategories = bookService.getBookCategories();

            int itemsCount = bookService.countBooksBySearchRequest(searchRequest, category, authorName, authorSurname, yearFrom, yearTo);
            int pagesCount = itemsCount / itemsOnPage;
            if (itemsCount % itemsOnPage != 0) {
                pagesCount++;
            }

            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.PAGES_COUNT, pagesCount);
            request.setAttribute(RequestAttribute.BOOKS, books);
            request.setAttribute(RequestAttribute.BOOK_CATEGORIES, bookCategories);

            return new CommandResult(Page.CATALOG_PAGE, RoutingType.FORWARD);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to get books DTO by search request.", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("Unable to convert request to UTF-8.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
