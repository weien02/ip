package qwerty.ui;
import java.util.Scanner;

public class Ui {
    private final String breakline = "------------------------------------------------------------";
    private final String welcomeMessage = "Hi! I'm Qwerty!\nHow can I help?";
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(welcomeMessage);
    }

    public void showLine() {
        System.out.println(breakline);
    }

    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    public void closeScanner() {
        this.scanner.close();
    }
}
