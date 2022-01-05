package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandResult;
import com.epam.ofeitus.library.controller.command.RoutingType;
import com.epam.ofeitus.library.controller.constant.Page;
import com.epam.ofeitus.library.controller.constant.RequestParameter;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteOffCopiesOfBooksCommand implements Command {
    Logger logger = LogManager.getLogger(WriteOffCopiesOfBooksCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.getInstance().getBookService();

        int fromInventoryId = Integer.parseInt(request.getParameter(RequestParameter.FROM_INVENTORY_ID));
        int toInventoryId = Integer.parseInt(request.getParameter(RequestParameter.TO_INVENTORY_ID));

        try {
            bookService.writeOffCopiesOfBooks(fromInventoryId, toInventoryId);
            return new CommandResult("/controller?command=goto-inventory-book-page", RoutingType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Unable to write off copies of books.", e);
            return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
        }
    }
}