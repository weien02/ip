package commands;

import exceptions.BotException;
import exceptions.UnknownCommandException;

public enum Commands {
    bye,
    list,
    mark,
    unmark,
    delete,
    todo,
    deadline,
    event;

    public static void contains(String command) throws BotException {
        try {
            valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
    }

}
