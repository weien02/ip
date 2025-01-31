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

public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    public void saveListToFile(TaskList tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        ArrayList<Task> list = tasks.getList();

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

    public ArrayList<Task> readListFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s*\\|\\s*");
                char type = parts[0].charAt(0);
                boolean isDone = parts[1].charAt(0) == '1';
                String details = parts[2];
                Task task = null;
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
                }
                if (isDone) {
                    task.markTaskDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            tasks = new ArrayList<>();
        } catch (BotException b) {
            System.out.println(b);
            tasks = new ArrayList<>();
        }
        return tasks;
    }

}
