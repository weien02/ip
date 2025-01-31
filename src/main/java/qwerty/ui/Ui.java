package ui;
import java.util.Scanner;

public class Ui {
    private final String BREAKLINE = "------------------------------------------------------------";
    private final String WELCOME_MESSAGE = "Hi! I'm Qwerty!\nHow can I help?";
    private final Scanner SCANNER = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void showLine() {
        System.out.println(BREAKLINE);
    }

    public String readCommand() {
        String command = SCANNER.nextLine();
        return command;
    }

    public void closeScanner() {
        this.SCANNER.close();
    }
}
