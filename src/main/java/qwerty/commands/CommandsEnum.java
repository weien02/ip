package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.exceptions.UnknownCommandException;

public enum CommandsEnum {
    bye,
    list,
    mark,
    unmark,
    delete,
    todo,
    deadline,
    event,
    find;

    public static void contains(String command) throws BotException {
        try {
            valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
    }

}
