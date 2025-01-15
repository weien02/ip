import java.util.ArrayList;
import java.util.Scanner;

public class Qwerty {

    public static ArrayList<String> list = new ArrayList<>();

    public static void printList() {
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println("" + index + ". " + list.get(i));
        }
    }

    public static void addToList(String item) {
        list.add(item);
        System.out.println("added: " + item);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi! I'm Qwerty");
        System.out.println("How can I help?");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                printList();
            } else {
                addToList(userInput);
            }
        }
        System.out.println("See ya!");
        scanner.close();
    }
}
