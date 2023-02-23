package ui;

import exceptions.GoBack;
import exceptions.LogOut;

import java.util.Scanner;

public class CommonOperations {

    private final Scanner scanner = new Scanner(System.in);

    // EFFECTS: asks the user if they want to return to the previous prompt
    public void goBack() throws GoBack {
        while (true) {
            String input;
            System.out.println("\nDo you want to return to the startup menu?:");
            System.out.println(" (y) - Yes");
            System.out.println(" (n) - No");
            input = scanner.nextLine();
            if (input.equals("y")) {
                throw new GoBack();
            } else if (input.equals("n")) {
                break;
            }
            System.out.println("Please enter a valid option. Try again.");

        }

    }

    // EFFECTS: prompts the user to log out of their account
    public void logout() throws LogOut {
        while (true) {
            String input;
            System.out.println("\nAre your sure your want to logout and return to the startup menu?:");
            System.out.println(" (y) - Yes");
            System.out.println(" (n) - No");
            input = scanner.nextLine();
            if (input.equals("y")) {
                throw new LogOut();
            } else if (input.equals("n")) {
                break;
            }
            System.out.println("Please enter a valid option. Try again.");

        }
    }

}
