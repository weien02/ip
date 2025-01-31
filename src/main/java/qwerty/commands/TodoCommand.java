package commands;

import exceptions.BotException;
import ui.Storage;
import ui.TaskList;
import ui.Ui;

public class TodoCommand extends BotCommand {
    
    private String desc;

    public TodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
     public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.addToDoToList(this.desc);
        storage.saveListToFile(tasks);
    }

}