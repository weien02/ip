package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command to add a new Event task.
 * Inherits from {@link BotCommand} and implements the execution logic
 * for adding an Event task to the task list and saving it to storage.
 */
public class EventCommand extends BotCommand {

    private String desc;  // Description of the Event task.

    /**
     * Constructs an EventCommand with the specified description.
     *
     * @param desc The description of the Event task to be added.
     */
    public EventCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the command by adding the Event task to the TaskList and saving the updated list to storage.
     *
     * @param tasks The TaskList to interact with and add the new Event task.
     * @param ui The Ui instance for displaying messages (currently unused in this method).
     * @param storage The Storage instance for saving the updated task list.
     * @throws BotException If an error occurs during the execution (e.g., during task addition or saving).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.addEventToList(this.desc);  // Adds the Event task to the list.
        storage.saveListToFile(tasks);     // Saves the updated task list to storage.
    }
}
