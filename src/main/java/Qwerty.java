import java.util.ArrayList;
import java.util.Scanner;

import exceptions.BotException;
import exceptions.EmptyTaskNameException;
import exceptions.UnknownCommandException;

public class Qwerty {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println("" + index + ". " + list.get(i));
        }
    }

    public static void printListStatus() {
        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? " " : "s ") + "in the list.");
    }

    public static void addToDoToList(String item) throws EmptyTaskNameException {
        Task newTask = new ToDo(item);
        list.add(newTask);
        System.out.println("Got it. I've added this todo:");
        System.out.println(newTask);
        printListStatus();
    }

    public static void addDeadlineToList(String item, String by) throws EmptyTaskNameException {
        Task newTask = new Deadline(item, by);
        list.add(newTask);
        System.out.println("Got it. I've added this deadline:");
        System.out.println(newTask);
        printListStatus();
    }

    public static void addEventToList(String item, String from, String to) throws EmptyTaskNameException {
        Task newTask = new Event(item, from, to);
        list.add(newTask);
        System.out.println("Got it. I've added this event:");
        System.out.println(newTask);
        printListStatus();
    }

    public static void markAsDone(int i) {
        Task task = list.get(i);
        task.markTaskDone();
        list.set(i, task);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(i));
    }

    public static void unmarkAsDone(int i) {
        Task task = list.get(i);
        task.unmarkTaskDone();
        list.set(i, task);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(i));
    }

    public static void deleteTask(int i) {
        Task task = list.get(i);
        list.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        printListStatus();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi! I'm Qwerty!");
        System.out.println("How can I help?");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            }
            try {
                if (userInput.equals("list")) {
                    printList();
                } else if (userInput.startsWith("mark")) {
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    markAsDone(index);
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    unmarkAsDone(index);
                } else if (userInput.startsWith("delete")){
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    deleteTask(index);
                } else if (userInput.startsWith("todo")) {
                    String name = userInput.substring(5);
                    addToDoToList(name);
                } else if (userInput.startsWith("deadline")) {
                    String desc = userInput.substring(9);
                    String[] parts = desc.split(" /by ");
                    addDeadlineToList(parts[0], parts[1]);
                } else if (userInput.startsWith("event")) {
                    String desc = userInput.substring(6);
                    String[] parts = desc.split(" /from | /to ");
                    addEventToList(parts[0], parts[1], parts[2]);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (BotException b) {
                System.out.println(b);
            } catch (Exception e) {
                System.out.println("Huh?! Please make sure your command is properly formatted!");
            } 
            
        }
        System.out.println("See ya!");
        scanner.close();
    }
}
