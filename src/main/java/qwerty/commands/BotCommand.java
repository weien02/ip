package commands;

import exceptions.BotException;
import ui.Storage;
import ui.TaskList;
import ui.Ui;

public class BotCommand {
    
    public boolean isBye() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        return;
    }

}
