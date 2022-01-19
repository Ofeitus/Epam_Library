package com.epam.ofeitus.library.controller.command.factory;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();

    private static final Map<String, Command> commands = new HashMap<>() {{
        put(CommandName.CANCEL_RESERVATION_COMMAND, new CancelReservationCommand());
        put(CommandName.CONFIRM_RESERVATION_COMMAND, new ConfirmReservationCommand());
        put(CommandName.DELETE_BOOK_COMMAND, new DeleteBookCommand());
        put(CommandName.DELETE_COPY_OF_BOOK_COMMAND, new DeleteCopyOfBookCommand());
        put(CommandName.DELETE_USER_COMMAND, new DeleteUserCommand());
        put(CommandName.EDIT_BOOK_DATA_COMMAND, new EditBookDataCommand());
        put(CommandName.EDIT_PERSONAL_DATA_COMMAND, new EditPersonalDataCommand());
        put(CommandName.GET_PERIOD_REPORTS_COMMAND, new GetPeriodReportsCommand());
        put(CommandName.GOTO_403_PAGE_COMMAND, new GoTo403PageCommand());
        put(CommandName.GOTO_404_PAGE_COMMAND, new GoTo404PageCommand());
        put(CommandName.GOTO_500_PAGE_COMMAND, new GoTo500PageCommand());
        put(CommandName.GOTO_ADD_NEW_BOOK_PAGE_COMMAND, new GoToAddNewBookPageCommand());
        put(CommandName.GOTO_BOOK_DETAILS_COMMAND, new GoToBookDetailsPageCommand());
        put(CommandName.GOTO_CATALOG_PAGE_COMMAND, new GoToCatalogPageCommand());
        put(CommandName.GOTO_CONTACTS_PAGE_COMMAND, new GoToContactsPageCommand());
        put(CommandName.GOTO_EDIT_BOOK_DATA_PAGE_COMMAND, new GoToEditBookDataPageCommand());
        put(CommandName.GOTO_EDIT_PERSONAL_DATA_PAGE_COMMAND, new GoToEditPersonalDataPageCommand());
        put(CommandName.GOTO_HOME_PAGE_COMMAND, new GoToHomePageCommand());
        put(CommandName.GOTO_INVENTORY_BOOK_PAGE_COMMAND, new GoToInventoryBookPageCommand());
        put(CommandName.GOTO_LOG_IN_PAGE_COMMAND, new GoToLogInPageCommand());
        put(CommandName.GOTO_MANAGE_MEMBERS_PAGE_COMMAND, new GoToManageMembersPageCommand());
        put(CommandName.GOTO_MANAGE_RESERVATIONS_PAGE_COMMAND, new GoToManageReservationsPageCommand());
        put(CommandName.GOTO_MANAGE_USERS_PAGE_COMMAND, new GoToManageUsersPageCommand());
        put(CommandName.GOTO_PROFILE_PAGE_COMMAND, new GoToProfilePageCommand());
        put(CommandName.GOTO_REPORTS_PAGE_COMMAND, new GoToReportsPageCommand());
        put(CommandName.GOTO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
        put(CommandName.GOTO_USER_FINES_PAGE_COMMAND, new GoToUserFinesPageCommand());
        put(CommandName.GOTO_USER_LOANS_PAGE_COMMAND, new GoToUserLoansPageCommand());
        put(CommandName.GOTO_USER_RESERVATIONS_PAGE_COMMAND, new GoToUserReservationsPageCommand());
        put(CommandName.ISSUE_BOOK_COMMAND, new IssueBookCommand());
        put(CommandName.ISSUE_BY_INVENTORY_ID_COMMAND, new IssueByInventoryIdCommand());
        put(CommandName.ISSUE_RESERVED_BOOK_COMMAND, new IssueReservedBookCommand());
        put(CommandName.LOG_IN_COMMAND, new LogInCommand());
        put(CommandName.LOG_OUT_COMMAND, new LogOutCommand());
        put(CommandName.PAY_FINE_COMMAND, new PayFineCommand());
        put(CommandName.RESERVE_BOOK_COMMAND, new ReserveBookCommand());
        put(CommandName.RESTORE_USER_COMMAND, new RestoreUserCommand());
        put(CommandName.RETURN_LOANED_BOOK_COMMAND, new ReturnLoanedBookCommand());
        put(CommandName.SEARCH_BOOKS_COMMAND, new SearchBooksCommand());
        put(CommandName.SEARCH_COPIES_OF_BOOKS_COMMAND, new SearchCopiesOfBooksCommand());
        put(CommandName.SEARCH_MEMBERS_COMMAND, new SearchMembersCommand());
        put(CommandName.SEARCH_USERS_COMMAND, new SearchUsersCommand());
        put(CommandName.SET_LOCALE_COMMAND, new SetLocaleCommand());
        put(CommandName.SET_ROLE_COMMAND, new SetRoleCommand());
        put(CommandName.SIGN_UP_COMMAND, new SignUpCommand());
        put(CommandName.WRITE_IN_COPIES_OF_BOOK_COMMAND, new WriteInCopiesOfBookCommand());
        put(CommandName.WRITE_IN_COPIES_OF_NEW_BOOK_COMMAND, new WriteInCopiesOfNewBookCommand());
        put(CommandName.WRITE_OFF_COPIES_OF_BOOKS_COMMAND, new WriteOffCopiesOfBooksCommand());
    }};

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}