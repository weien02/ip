package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

public class BotCommand {

    public boolean isBye() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        return;
    }
}
