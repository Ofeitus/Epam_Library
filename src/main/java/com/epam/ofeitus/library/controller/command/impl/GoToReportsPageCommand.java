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
import com.epam.ofeitus.library.entity.report.BooksStockReport;
import com.epam.ofeitus.library.entity.report.IssueReport;
import com.epam.ofeitus.library.entity.report.UserCompositionReport;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Command to go to reports page.
 */
public class GoToReportsPageCommand implements Command {
    private final Logger logger = LogManager.getLogger(GoToReportsPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String from = request.getParameter(RequestParameter.DATE_FROM);
        String to = request.getParameter(RequestParameter.DATE_TO);

        session.setAttribute(SessionAttribute.URL, "/controller?" +
                RequestParameter.COMMAND + "=" + CommandName.GOTO_REPORTS_PAGE_COMMAND +
                        "&" + RequestParameter.DATE_FROM + "=" + from +
                        "&" + RequestParameter.DATE_TO + "=" + to);

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.YEAR, -1);
            Date fromDate = cal.getTime();
            Date toDate = new Date();

            UserCompositionReport userCompositionReport = userService.getUserCompositionReport(fromDate, toDate);
            BooksStockReport booksStockReport = bookService.getBooksReport(fromDate, toDate);
            List<BookCategory> bookCategories = bookService.getBookCategories();
            IssueReport issueReport = bookService.getIssueReport(fromDate, toDate);

            request.setAttribute(RequestAttribute.USER_COMPOSITION_REPORT, userCompositionReport);
            request.setAttribute(RequestAttribute.BOOKS_STOCK_REPORT, booksStockReport);
            request.setAttribute(RequestAttribute.BOOK_CATEGORIES, bookCategories);
            request.setAttribute(RequestAttribute.ISSUE_REPORT, issueReport);

            return new CommandResult(Page.REPORTS_PAGE, RoutingType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Unable to get reports.");
        }
        return new CommandResult(Page.ERROR_500_PAGE, RoutingType.FORWARD);
    }
}
