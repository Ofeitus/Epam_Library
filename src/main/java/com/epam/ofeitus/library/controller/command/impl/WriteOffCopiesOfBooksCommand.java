package com.epam.ofeitus.library.controller.command.impl;

import com.epam.ofeitus.library.controller.command.Command;
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

/**
 * Command to write off copies of books.
 */
public class WriteOffCopiesOfBooksCommand implements Command {
    private final Logger logger = LogManager.getLogger(WriteOffCopiesOfBooksCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        BookService bookService = ServiceFactory.getInstance().getBookService();

        try {
            int fromInventoryId = Integer.parseInt(request.getParameter(RequestParameter.INVENTORY_ID_FROM));
            int toInventoryId = Integer.parseInt(request.getParameter(RequestParameter.INVENTORY_ID_TO));

            bookService.writeOffCopiesOfBooks(fromInventoryId, toInventoryId);

            return new CommandResult((String) session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Unable to write off copies of books.", e);
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
