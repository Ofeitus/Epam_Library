package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriteInCopiesOfNewBookCommand implements Command {
    Logger logger = LogManager.getLogger(WriteInCopiesOfNewBookCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String title = request.getParameter(RequestParameter.TITLE);
        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);
        String category = request.getParameter(RequestParameter.CATEGORY);
        String language = request.getParameter(RequestParameter.LANGUAGE);
        String keyWords = request.getParameter(RequestParameter.KEY_WORDS);
        List<String> authorNames = Arrays.asList(request.getParameterValues(RequestParameter.AUTHOR_NAME).clone());
        List<String> authorSurnames = Arrays.asList(request.getParameterValues(RequestParameter.AUTHOR_SURNAME).clone());

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-inventory-book-page");

        try {
            List<Author> authors = new ArrayList<>();
            for (int i = 0; i < authorNames.size(); i++) {
                authors.add(new Author(0, authorNames.get(i), authorSurnames.get(i)));
            }
            int publicationYear = Integer.parseInt(request.getParameter(RequestParameter.PUBLICATION_YEAR));
            int copiesCount = Integer.parseInt(request.getParameter(RequestParameter.COPIES_COUNT));
            bookService.saveBook(bookIsbn, title, category, publicationYear, language, keyWords, authors);
            BigDecimal price = new BigDecimal(request.getParameter(RequestParameter.PRICE));
            bookService.addCopiesOfBook(bookIsbn, price, copiesCount);
            return new CommandResult("/controller?command=goto-inventory-book-page", RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to add copies of new book.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}
