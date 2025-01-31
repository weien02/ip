package commands;

import exceptions.BotException;
import ui.Storage;
import ui.TaskList;
import ui.Ui;

public class ListCommand extends BotCommand {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.printList();
    }
    
}
