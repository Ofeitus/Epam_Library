package com.epam.ofeitus.library.controller.command.factory;

import com.epam.ofeitus.library.controller.command.Command;
import com.epam.ofeitus.library.controller.command.CommandName;
import com.epam.ofeitus.library.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that provides command with given name.
 */
public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();

    private static final Map<String, Command> commands = new HashMap<>() {{
        put(CommandName.DELETE_SUBJECT_COMMAND, new DeleteSubjectCommand());
        put(CommandName.DELETE_USER_COMMAND, new DeleteUserCommand());
        put(CommandName.EDIT_SUBJECT_COMMAND, new EditSubjectCommand());
        put(CommandName.GOTO_403_PAGE_COMMAND, new GoTo403PageCommand());
        put(CommandName.GOTO_404_PAGE_COMMAND, new GoTo404PageCommand());
        put(CommandName.GOTO_500_PAGE_COMMAND, new GoTo500PageCommand());
        put(CommandName.GOTO_ADD_NEW_SUBJECT_PAGE_COMMAND, new GoToAddNewSubjectPageCommand());
        put(CommandName.GOTO_SUBJECT_DETAILS_PAGE_COMMAND, new GoToSubjectDetailsPageCommand());
        put(CommandName.GOTO_SUBJECTS_PAGE_COMMAND, new GoToSubjectsPageCommand());
        put(CommandName.GOTO_EDIT_SUBJECT_PAGE_COMMAND, new GoToEditSubjectPageCommand());
        put(CommandName.GOTO_LOG_IN_PAGE_COMMAND, new GoToLogInPageCommand());
        put(CommandName.GOTO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
        put(CommandName.LOG_IN_COMMAND, new LogInCommand());
        put(CommandName.LOG_OUT_COMMAND, new LogOutCommand());
        put(CommandName.RESTORE_USER_COMMAND, new RestoreUserCommand());
        put(CommandName.SET_LOCALE_COMMAND, new SetLocaleCommand());
        put(CommandName.SET_ROLE_COMMAND, new SetRoleCommand());
        put(CommandName.SIGN_UP_COMMAND, new SignUpCommand());
    }};

    private CommandFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance of <code>CommandFactory</code>
     */
    public static CommandFactory getInstance() {
        return instance;
    }

    /**
     * Gets command.
     *
     * @param commandName the command name
     * @return the command with corresponding name
     */
    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}