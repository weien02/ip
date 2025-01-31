import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import enums.Commands;
import exceptions.BotException;
import exceptions.IncorrectFormatException;
import exceptions.InvalidIndexException;

public class Qwerty {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void printList() {
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

    public static void printListStatus() {
        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? " " : "s ") + "in the list.");
    }

    public static void addToDoToList(String desc) throws BotException {
        Task newTask = new ToDo(desc);
        list.add(newTask);
        saveListToFile();
        System.out.println("Got it. I've added this todo:");
        System.out.println(newTask);
        printListStatus();
    }

    public static void addDeadlineToList(String desc) throws BotException {
        String[] parts = desc.split(" /by ");
        if (parts.length != 2) {
            throw new IncorrectFormatException("deadline");
        }
        Task newTask = new Deadline(parts[0], parts[1]);
        list.add(newTask);
        saveListToFile();
        System.out.println("Got it. I've added this deadline:");
        System.out.println(newTask);
        printListStatus();
    }

    public static void addEventToList(String desc) throws BotException {
        String[] parts = desc.split(" /from | /to ");
        if (parts.length != 3) {
            throw new IncorrectFormatException("event");
        }
        Task newTask = new Event(parts[0], parts[1], parts[2]);
        list.add(newTask);
        saveListToFile();
        System.out.println("Got it. I've added this event:");
        System.out.println(newTask);
        printListStatus();
    }

    public static int validateIndex(String desc) throws BotException{
        int index;
        try {
            index = Integer.parseInt(desc) - 1;
        } catch (NumberFormatException n){
            throw new InvalidIndexException();
        }
        if ((index < 0) || (index >= list.size())) {
            throw new InvalidIndexException();
        }
        return index;
    }

    public static void markAsDone(int i) {
        Task task = list.get(i);
        task.markTaskDone();
        list.set(i, task);
        saveListToFile();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(i));
    }

    public static void unmarkAsDone(int i) {
        Task task = list.get(i);
        task.unmarkTaskDone();
        list.set(i, task);
        saveListToFile();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(i));
    }

    public static void deleteTask(int i) {
        Task task = list.get(i);
        list.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        saveListToFile();
        printListStatus();
    }

    public static void saveListToFile() {
        File file = new File("./data/Qwerty.txt");
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                writer.write(task.toSaveString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void readListFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("./data/Qwerty.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s*\\|\\s*");
                char type = parts[0].charAt(0);
                boolean isDone = parts[1].charAt(0) == '1';
                String details = parts[2];
                
                switch (type) {
                    case 'T':
                        list.add(new ToDo(details));
                        break;
                    case 'D':
                        list.add(new Deadline(details, parts[3]));
                        break;
                    case 'E':
                        list.add(new Event(details, parts[3], parts[4]));
                        break;
                }
                if (isDone) {
                    Task task = list.get(list.size() - 1);
                    task.markTaskDone();
                    list.set(list.size() - 1, task);
                    saveListToFile();
                }
            }
        } catch (IOException e) {

        } catch (BotException b) {
            System.out.println(b);
        }
    }
        

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        readListFromFile();
        System.out.println("Hi! I'm Qwerty!");
        System.out.println("How can I help?");

        while (true) {
            String userInput = scanner.nextLine();

            int spaceIndex = userInput.indexOf(' ');

            String command, desc;
            if (spaceIndex != -1) {
                command = userInput.substring(0, spaceIndex);
                desc = userInput.substring(spaceIndex + 1);
            } else {
                command = userInput;
                desc = "";
            }

            try {
                Commands.contains(command);
                if (command.equals("bye")) {
                    break;
                }
                
                if (command.equals("list")) {
                    printList();
                } else if (command.equals("mark")) {
                    int index = validateIndex(desc);
                    markAsDone(index);
                } else if (command.equals("unmark")) {
                    int index = validateIndex(desc);
                    unmarkAsDone(index);
                } else if (command.equals("delete")){
                    int index = validateIndex(desc);
                    deleteTask(index);
                } else if (command.equals("todo")) {
                    addToDoToList(desc);
                } else if (command.equals("deadline")) {
                    addDeadlineToList(desc);
                } else if (command.equals("event")) {
                    addEventToList(desc);
                }

            } catch (BotException b) {
                System.out.println(b);
            } catch (Exception e) {
                System.out.println(e);
            } 
            
        }
        System.out.println("See ya!");
        scanner.close();
    }
}
