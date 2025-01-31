package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

public class ListCommand extends BotCommand {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.printList();
    }
    
}
