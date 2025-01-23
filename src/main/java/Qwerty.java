import java.util.ArrayList;
import java.util.Scanner;

public class Qwerty {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println("" + index + ". " + list.get(i));
        }
    }

    public static void addToList(String item) {
        Task newTask = new Task(item);
        list.add(newTask);
        System.out.println("added: " + item);
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi! I'm Qwerty!");
        System.out.println("How can I help?");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }

            if (userInput.equals("list")) {
                printList();
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                markAsDone(index);
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                unmarkAsDone(index);
            } else {
                addToList(userInput);
            }
        }
        System.out.println("See ya!");
        scanner.close();
    }
}
