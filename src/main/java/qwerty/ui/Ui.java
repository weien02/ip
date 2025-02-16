package qwerty.ui;

import java.util.Scanner;

/**
 * The Ui class handles user interactions, including displaying messages and reading input from the user.
 * It shows a welcome message, a separator line, and reads user commands from the console.
 */
public class Ui {

    private final String breakline = "------------------------------------------------------------"; // Separator line.
    private final String welcomeMessage = "Hi! I'm Qwerty!\nHow can I help?"; // Welcome message to display.
    private final String byeMessage = "Bye! See you soon!";
    private final Scanner scanner = new Scanner(System.in); // Scanner to read user input.

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(welcomeMessage);
    }

    /**
     * Displays a separator line to visually separate outputs.
     */
    public void showLine() {
        System.out.println(breakline);
    }

    public void showBye() {
        System.out.println(byeMessage);
    }

    /**
     * Reads the next line of user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        String command = scanner.nextLine();
        assert command.length() > 0 : "Command should not be an empty string!";
        return command;
    }

    /**
     * Closes the scanner to release the resources associated with it.
     */
    public void closeScanner() {
        this.scanner.close();
    }
}
