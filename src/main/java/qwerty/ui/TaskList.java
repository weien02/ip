package ui;
import java.util.ArrayList;

import exceptions.BotException;
import exceptions.IncorrectFormatException;
import exceptions.InvalidIndexException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

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

    public void printListStatus() {
        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? " " : "s ") + "in the list.");
    }

    public void addToDoToList(String desc) throws BotException {
        Task newTask = new ToDo(desc);
        list.add(newTask);
        //saveListToFile();
        System.out.println("Gotcha. I've added this todo:");
        System.out.println(newTask);
        printListStatus();
    }

    public void addDeadlineToList(String desc) throws BotException {
        String[] parts = desc.split(" /by ");
        if (parts.length != 2) {
            throw new IncorrectFormatException("deadline");
        }
        Task newTask = new Deadline(parts[0], parts[1]);
        list.add(newTask);
        //saveListToFile();
        System.out.println("Gotcha. I've added this deadline:");
        System.out.println(newTask);
        printListStatus();
    }

    public void addEventToList(String desc) throws BotException {
        String[] parts = desc.split(" /from | /to ");
        if (parts.length != 3) {
            throw new IncorrectFormatException("event");
        }
        Task newTask = new Event(parts[0], parts[1], parts[2]);
        list.add(newTask);
        //saveListToFile();
        System.out.println("Gotcha. I've added this event:");
        System.out.println(newTask);
        printListStatus();
    }

    public int validateIndex(int index) throws BotException {
        if ((index < 0) || (index >= list.size())) {
            throw new InvalidIndexException();
        }
        return index;
    }

    public void markAsDone(int i) throws BotException {
        i = validateIndex(i);
        Task task = list.get(i);
        task.markTaskDone();
        list.set(i, task);
        //saveListToFile();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(i));
    }

    public void unmarkAsDone(int i) throws BotException {
        i = validateIndex(i);
        Task task = list.get(i);
        task.unmarkTaskDone();
        list.set(i, task);
        //saveListToFile();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(i));
    }

    public void deleteTask(int i) throws BotException {
        i = validateIndex(i);
        Task task = list.get(i);
        list.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        //saveListToFile();
        printListStatus();
    }
}
