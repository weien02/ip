package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.exceptions.UnknownCommandException;

/**
 * Enum representing the various commands available to the bot.
 * Each constant in the enum corresponds to a specific command type.
 */
public enum CommandsEnum {
    bye, // Command to terminate the bot's operation.
    list, // Command to list all tasks.
    mark, // Command to mark a task as done.
    unmark, // Command to unmark a task.
    delete, // Command to delete a task.
    todo, // Command to create a ToDo task.
    deadline, // Command to create a Deadline task.
    event, // Command to create an Event task.
    find; // Command to create a Find task.

    /**
     * Checks if the provided command string corresponds to a valid command in the enum.
     *
     * @param command The command string to check.
     * @throws BotException If the command is not recognized.
     */
    public static void contains(String command) throws BotException {
        try {
            valueOf(command); // Tries to map the string to an enum value.
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(); // Throws exception if the command is invalid.
        }
    }
}
