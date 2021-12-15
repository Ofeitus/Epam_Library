package com.epam.ofeitus.library.controller.command.factory;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();

    private static final Map<String, Command> commands = new HashMap<>() {{
        put(CommandName.GOTO_HOME_PAGE_COMMAND, new GoToHomePageCommand());
        put(CommandName.GOTO_CATALOG_PAGE_COMMAND, new GoToCatalogPageCommand());
        put(CommandName.GOTO_BOOK_DETAILS_COMMAND, new GoToBookDetailsPageCommand());
        put(CommandName.GOTO_LOG_IN_PAGE_COMMAND, new GoToLogInPageCommand());
        put(CommandName.GOTO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
        put(CommandName.GOTO_MANAGE_USERS_PAGE_COMMAND, new GoToManageUsersPageCommand());
        put(CommandName.LOG_IN_COMMAND, new LogInCommand());
        put(CommandName.LOG_OUT_COMMAND, new LogOutCommand());
        put(CommandName.SIGN_UP_COMMAND, new SignUpCommand());
        put(CommandName.SEARCH_BOOKS_COMMAND, new SearchBooksCommand());
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