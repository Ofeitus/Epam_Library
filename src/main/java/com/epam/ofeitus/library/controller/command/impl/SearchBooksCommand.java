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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class SearchBooksCommand implements Command {
    Logger logger = LogManager.getLogger(SearchBooksCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String searchRequest = request.getParameter(RequestParameter.SEARCH_REQUEST);
        String category = request.getParameter(RequestParameter.CATEGORY);
        String authorName = request.getParameter(RequestParameter.AUTHOR_NAME);
        String authorSurname = request.getParameter(RequestParameter.AUTHOR_SURNAME);
        int yearFrom = Integer.parseInt(request.getParameter(RequestParameter.YEAR_FROM));
        int yearTo = Integer.parseInt(request.getParameter(RequestParameter.YEAR_TO));

        HttpSession session = request.getSession();

        try {
            session.setAttribute(SessionAttribute.URL, (
                "/controller?command=search-books" +
                "&search-request="+URLEncoder.encode(searchRequest, "UTF-8")+
                "&category="+URLEncoder.encode(category, "UTF-8")+
                "&author-name="+URLEncoder.encode(authorName, "UTF-8")+
                "&author-surname="+URLEncoder.encode(authorSurname, "UTF-8")+
                "&year-from="+yearFrom+
                "&year-to="+yearTo));

            List<BookDto> books = bookService.getBooksDtoBySearchRequest(
                    searchRequest,
                    category,
                    authorName,
                    authorSurname,
                    yearFrom,
                    yearTo
            );
            request.setAttribute(RequestAttribute.BOOKS, books);
            List<BookCategory> bookCategories = bookService.getBookCategories();
            request.setAttribute(RequestAttribute.BOOK_CATEGORIES, bookCategories);
            return new CommandResult(Page.CATALOG_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get books DTO by search request.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        } catch (UnsupportedEncodingException e) {
            logger.error("Unable to convert request to UTF-8.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
