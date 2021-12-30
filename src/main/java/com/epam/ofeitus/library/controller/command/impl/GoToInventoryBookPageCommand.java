package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestAttribute;
import com.epam.ofeitus.library.controller.constant.SessionAttribute;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToInventoryBookPageCommand implements Command {
    Logger logger = LogManager.getLogger(GoToInventoryBookPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-inventory-book-page");

        BookService bookService = ServiceFactory.getInstance().getBookService();
        try {
            List<CopyOfBook> copiesOfBooksDto = bookService.getAllCopiesOfBooks();
            request.setAttribute(RequestAttribute.COPIES_OF_BOOKS, copiesOfBooksDto);
            return new CommandResult(Page.INVENTORY_BOOK_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get book categories.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}