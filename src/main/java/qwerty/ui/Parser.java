package qwerty.ui;

import qwerty.commands.BotCommand;
import qwerty.commands.ByeCommand;
import qwerty.commands.CommandsEnum;
import qwerty.commands.DeadlineCommand;
import qwerty.commands.DeleteCommand;
import qwerty.commands.EventCommand;
import qwerty.commands.FindCommand;
import qwerty.commands.ListCommand;
import qwerty.commands.MarkCommand;
import qwerty.commands.TodoCommand;
import qwerty.commands.UnmarkCommand;
import qwerty.exceptions.BotException;
import qwerty.exceptions.InvalidIndexException;
import qwerty.exceptions.UnknownCommandException;

/**
 * The Parser class is responsible for parsing user commands and creating appropriate BotCommand objects.
 * It validates and processes the user input to trigger specific actions for tasks.
 */
public class Parser {

    /**
     * Validates the index provided by the user.
     *
     * @param desc The string representation of the index.
     * @return The index as an integer, adjusted for zero-based indexing.
     * @throws BotException If the index is not a valid integer or is out of bounds.
     */
    public static int validateIndex(String desc) throws BotException {
        int index;
        try {
            index = Integer.parseInt(desc) - 1; // Adjusts for zero-based indexing.
        } catch (NumberFormatException n) {
            throw new InvalidIndexException(); // Throws exception if index is invalid.
        }
        return index;
    }

    /**
     * Parses a user command and returns the corresponding BotCommand object.
     *
     * @param fullCommand The full user input command.
     * @return A BotCommand object corresponding to the user command.
     * @throws BotException If the command is unknown or incorrectly formatted.
     */
    public static BotCommand parse(String fullCommand) throws BotException {
        try {
            int spaceIndex = fullCommand.indexOf(' '); // Finds the space separating the command and description.

            String command;
            String desc;
            if (spaceIndex != -1) {
                command = fullCommand.substring(0, spaceIndex);
                desc = fullCommand.substring(spaceIndex + 1);
            } else {
                command = fullCommand;
                desc = "";
            }

            CommandsEnum.contains(command); // Validates the command.

            // Returns the appropriate BotCommand based on the command.
            switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(validateIndex(desc));
            case "unmark":
                return new UnmarkCommand(validateIndex(desc));
            case "delete":
                return new DeleteCommand(validateIndex(desc));
            case "todo":
                return new TodoCommand(desc);
            case "deadline":
                return new DeadlineCommand(desc);
            case "event":
                return new EventCommand(desc);
            case "find":
                return new FindCommand(desc);
            default:
                throw new UnknownCommandException(); // Throws exception if the command is unrecognized.
            }
        } catch (BotException e) {
            throw e; // Re-throws BotException.
        } catch (Exception e) {
            throw new UnknownCommandException(); // Throws UnknownCommandException for any other exceptions.
        }
    }
}
