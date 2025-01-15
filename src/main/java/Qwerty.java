import java.util.Scanner;

public class Qwerty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi! I'm Qwerty");
        System.out.println("How can I help?");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(userInput);
        }
        System.out.println("See ya!");
        scanner.close();
    }
}
