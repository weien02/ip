package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

public class DeleteCommand extends BotCommand {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
     public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.deleteTask(this.index);
        storage.saveListToFile(tasks);
    }
}
