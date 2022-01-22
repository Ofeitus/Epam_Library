package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
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
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class WriteInCopiesOfNewBookCommand implements Command {
    private final Logger logger = LogManager.getLogger(WriteInCopiesOfNewBookCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String title = request.getParameter(RequestParameter.TITLE);
        String bookIsbn = request.getParameter(RequestParameter.BOOK_ISBN);
        String category = request.getParameter(RequestParameter.CATEGORY);
        String language = request.getParameter(RequestParameter.LANGUAGE);
        String keyWords = request.getParameter(RequestParameter.KEY_WORDS);
        List<String> authorNames = Arrays.asList(request.getParameterValues(RequestParameter.AUTHOR_NAME).clone());
        List<String> authorSurnames = Arrays.asList(request.getParameterValues(RequestParameter.AUTHOR_SURNAME).clone());


        try {
            int publicationYear = Integer.parseInt(request.getParameter(RequestParameter.PUBLICATION_YEAR));
            int copiesCount = Integer.parseInt(request.getParameter(RequestParameter.COPIES_COUNT));
            BigDecimal price = new BigDecimal(request.getParameter(RequestParameter.PRICE));

            if (price.signum() < 1 || !bookService.saveBook(bookIsbn, title, category, publicationYear, language, keyWords, authorNames, authorSurnames)) {
                session.setAttribute(SessionAttribute.ERROR, "Invalid data");
                return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
            }

            bookService.addCopiesOfBook(bookIsbn, price, copiesCount);

            String url = "/controller?" +
                    RequestParameter.COMMAND + "=" + CommandName.GOTO_INVENTORY_BOOK_PAGE_COMMAND;
            session.setAttribute(SessionAttribute.URL, url);

            return new CommandResult(url, RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to add copies of new book.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
