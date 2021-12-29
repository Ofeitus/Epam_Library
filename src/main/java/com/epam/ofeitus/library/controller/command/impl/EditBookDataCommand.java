package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditBookDataCommand implements Command {
    Logger logger = LogManager.getLogger(EditBookDataCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String title = request.getParameter(RequestParameter.TITLE);
        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);
        String category = request.getParameter(RequestParameter.CATEGORY);
        int publicationYear = Integer.parseInt(request.getParameter(RequestParameter.PUBLICATION_YEAR));
        String language = request.getParameter(RequestParameter.LANGUAGE);
        String keyWords = request.getParameter(RequestParameter.KEY_WORDS);
        List<String> authorNames = Arrays.asList(request.getParameterValues(RequestParameter.AUTHOR_NAME).clone());
        List<String> authorSurnames = Arrays.asList(request.getParameterValues(RequestParameter.AUTHOR_SURNAME).clone());

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-book-details-page&book-isbn=" + bookIsbn);

        try {
            List<Author> authors = new ArrayList<>();
            for (int i = 0; i < authorNames.size(); i++) {
                authors.add(new Author(0, authorNames.get(i), authorSurnames.get(i)));
            }
            bookService.updateBook(bookIsbn, title, category, publicationYear, language, keyWords, authors);
            BookDto updatedBook = bookService.getBookDtoByIsbn(bookIsbn);
            request.setAttribute(RequestAttribute.BOOK, updatedBook);
            return new CommandResult(Page.BOOK_DETAILS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get books DTO by search request.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
