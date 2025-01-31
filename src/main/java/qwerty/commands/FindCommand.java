package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.task.Task;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command to find tasks that contain a specific keyword.
 */
public class FindCommand extends BotCommand {

    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks containing the keyword.
     * The method filters the tasks and displays the results using the provided UI.
     * The task list is not mutated during the execution.
     *
     * @param tasks   the TaskList containing all the tasks
     * @param ui      the UI instance for displaying results
     * @param storage the Storage instance for interacting with storage (not used in this command)
     * @throws BotException if an error occurs during the execution of the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        // Get the list of tasks from TaskList (without mutating it)
        List<Task> list = tasks.getList();

        // Filter the tasks where the toString() representation contains the keyword
        List<Task> filteredTasks = list.stream()
                .filter(task -> task.toString().contains(this.keyword))
                .collect(Collectors.toList());

        // Print the results
        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found containing the keyword: " + this.keyword + "!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < filteredTasks.size(); i++) {
                Task task = filteredTasks.get(i);
                int index = i + 1;
                System.out.println(index + ". " + task.toString());
            }
        }
    }
}
