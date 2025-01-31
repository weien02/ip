package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

public class DeadlineCommand extends BotCommand {

    private String desc;

    public DeadlineCommand(String desc) {
        this.desc = desc;
    }

    @Override
     public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.addDeadlineToList(this.desc);
        storage.saveListToFile(tasks);
    }
}
