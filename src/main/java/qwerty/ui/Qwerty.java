package ui;
import java.util.ArrayList;

import commands.BotCommand;
import exceptions.BotException;
import task.Task;

public class Qwerty {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Qwerty(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            ArrayList<Task> list = storage.readListFromFile();
            this.tasks = new TaskList(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                BotCommand c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isBye();
            } catch (BotException e) {
                System.out.println(e);
            } finally {
                if (!isExit) {
                    ui.showLine();
                }
            }
        }
        System.out.println("Bye! See you soon!");
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Qwerty("./data/Qwerty.txt").run();
    }
}
