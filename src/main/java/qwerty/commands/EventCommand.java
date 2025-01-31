package commands;

import exceptions.BotException;
import ui.Storage;
import ui.TaskList;
import ui.Ui;

public class EventCommand extends BotCommand {
    
    private String desc;

    public EventCommand(String desc) {
        this.desc = desc;
    }

    @Override
     public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.addEventToList(this.desc);
        storage.saveListToFile(tasks);
    }

}