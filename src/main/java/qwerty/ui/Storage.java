package qwerty.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import qwerty.exceptions.BotException;
import qwerty.task.Deadline;
import qwerty.task.Event;
import qwerty.task.Task;
import qwerty.task.ToDo;

/**
 * The Storage class is responsible for handling file operations related to task storage.
 * It reads tasks from a file and saves tasks to a file in a specific format.
 */
public class Storage {

    private String filePath; // Path to the file where tasks are stored.

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the file.
     * Each task is saved on a new line in a specific format to be read later.
     *
     * @param tasks The TaskList object containing the tasks to be saved.
     */
    public void saveListToFile(TaskList tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Creates parent directories if they don't exist.
        ArrayList<Task> list = tasks.getList(); // Retrieves the task list.

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                writer.write(task.toSaveString()); // Writes the task's string representation to the file.
                writer.newLine(); // Adds a new line after each task.
            }
        } catch (IOException e) {
            System.err.println(e.getMessage()); // Prints error message if file writing fails.
        }
    }

    /**
     * Reads the list of tasks from the file.
     * The file is expected to be in a specific format, with each task represented as a line.
     *
     * @return An ArrayList of tasks read from the file.
     */
    public ArrayList<Task> readListFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s*\\|\\s*"); // Splits the line into components.
                char type = parts[0].charAt(0); // The task type (T, D, E).
                boolean isDone = parts[1].charAt(0) == '1'; // Indicates if the task is marked as done.
                String details = parts[2]; // The task description.
                Task task = null;
                // Creates the appropriate task object based on the type.
                switch (type) {
                case 'T':
                    task = new ToDo(details);
                    break;
                case 'D':
                    task = new Deadline(details, parts[3]);
                    break;
                case 'E':
                    task = new Event(details, parts[3], parts[4]);
                    break;
                default:
                    break;
                }
                if (isDone) {
                    task.markTaskDone(); // Marks the task as done if it was indicated in the file.
                }
                tasks.add(task); // Adds the task to the list.
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()); // Prints error message if file reading fails.
            tasks = new ArrayList<>(); // Returns an empty task list on error.
        } catch (BotException b) {
            System.out.println(b); // Prints any BotException that occurs during task creation.
            tasks = new ArrayList<>(); // Returns an empty task list on error.
        }
        return tasks; // Returns the list of tasks read from the file.
    }
}
