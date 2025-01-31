package qwerty.ui;

import java.util.ArrayList;

import qwerty.exceptions.BotException;
import qwerty.exceptions.IncorrectFormatException;
import qwerty.exceptions.InvalidIndexException;
import qwerty.task.Deadline;
import qwerty.task.Event;
import qwerty.task.Task;
import qwerty.task.ToDo;

/**
 * The TaskList class manages a list of tasks.
 * It allows for adding, removing, marking, and unmarking tasks.
 * It also prints the current task list and provides status updates.
 */
public class TaskList {

    private ArrayList<Task> list; // List to hold tasks.

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param list The initial list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Prints the tasks in the list. If the list is empty, informs the user.
     */
    public void printList() {
        if (list.size() == 0) {
            System.out.println("There is nothing in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                System.out.println("" + index + ". " + list.get(i));
            }
        }
    }

    /**
     * Prints the status of the task list, showing the current number of tasks.
     */
    public void printListStatus() {
        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? " " : "s ") + "in the list.");
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param desc The description of the task.
     * @throws BotException If the task description is invalid.
     */
    public void addToDoToList(String desc) throws BotException {
        Task newTask = new ToDo(desc);
        list.add(newTask);
        System.out.println("Gotcha. I've added this todo:");
        System.out.println(newTask);
        printListStatus();
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param desc The description of the task, including the deadline.
     * @throws BotException If the task description is incorrectly formatted.
     */
    public void addDeadlineToList(String desc) throws BotException {
        String[] parts = desc.split(" /by ");
        if (parts.length != 2) {
            throw new IncorrectFormatException("deadline");
        }
        Task newTask = new Deadline(parts[0], parts[1]);
        list.add(newTask);
        System.out.println("Gotcha. I've added this deadline:");
        System.out.println(newTask);
        printListStatus();
    }

    /**
     * Adds an Event task to the list.
     *
     * @param desc The description of the task, including the event's start and end time.
     * @throws BotException If the task description is incorrectly formatted.
     */
    public void addEventToList(String desc) throws BotException {
        String[] parts = desc.split(" /from | /to ");
        if (parts.length != 3) {
            throw new IncorrectFormatException("event");
        }
        Task newTask = new Event(parts[0], parts[1], parts[2]);
        list.add(newTask);
        System.out.println("Gotcha. I've added this event:");
        System.out.println(newTask);
        printListStatus();
    }

    /**
     * Validates the given index, ensuring it is within the bounds of the task list.
     *
     * @param index The index to validate.
     * @return The validated index.
     * @throws BotException If the index is invalid.
     */
    public int validateIndex(int index) throws BotException {
        if ((index < 0) || (index >= list.size())) {
            throw new InvalidIndexException();
        }
        return index;
    }

    /**
     * Marks a task as done by its index.
     *
     * @param i The index of the task to mark as done.
     * @throws BotException If the index is invalid.
     */
    public void markAsDone(int i) throws BotException {
        i = validateIndex(i);
        Task task = list.get(i);
        task.markTaskDone();
        list.set(i, task);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(i));
    }

    /**
     * Unmarks a task as done by its index.
     *
     * @param i The index of the task to unmark.
     * @throws BotException If the index is invalid.
     */
    public void unmarkAsDone(int i) throws BotException {
        i = validateIndex(i);
        Task task = list.get(i);
        task.unmarkTaskDone();
        list.set(i, task);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(i));
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param i The index of the task to delete.
     * @throws BotException If the index is invalid.
     */
    public void deleteTask(int i) throws BotException {
        i = validateIndex(i);
        Task task = list.get(i);
        list.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        printListStatus();
    }
}
