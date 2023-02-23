package ui;

import exceptions.GoBack;
import exceptions.LogOut;
import model.AdminAccount;
import model.AuctionMarket;
import model.CustomerDatabase;

import java.util.Scanner;

public class StartupInterface {

    private static final Scanner scanner = new Scanner(System.in);
    CustomerDatabase customerDatabase = new CustomerDatabase();
    AuctionMarket auctionMarket = new AuctionMarket();
    AdminAccount adminAccount = new AdminAccount("AuctionAdmin", "DoNotLeak1010");

    // EFFECTS: Constructs a new starting interface
    public StartupInterface() {
        System.out.println("\n******* Welcome to the Mu Ye's auction bidding market! *******");
        startupGreetingMenu();
    }

    // EFFECTS: prints out the greeting menu. Also, asks the user which mode they want to use
    public void startupGreetingMenu() {
        while (true) {
            String input;
            System.out.println("\nPlease select one of the following options below:");
            System.out.println(" (1) - Administrator");
            System.out.println(" (2) - Customer");
            System.out.println(" (3) - Exit");
            input = scanner.nextLine();

            if (input.equals("1")) {
                launchAdminMode();
            } else if (input.equals("2")) {
                launchCustomerMode();
            } else if (input.equals("3")) {
                System.exit(0);
            } else {
                System.out.println("Please enter a valid option. Try again.");
            }
        }
    }

    // EFFECTS: starts the admin mode
    private void launchAdminMode() {
        try {
            new AdminMode(this.auctionMarket, this.adminAccount, this.customerDatabase);
        } catch (GoBack e) {
            System.out.println("\nBack to the starting menu");
        } catch (LogOut e) {
            System.out.println("\nLogged out. Back to the startup menu.");
        }
    }

    // EFFECTS: starts the customer mode
    private void launchCustomerMode() {
        try {
            new CustomerMode(this.customerDatabase, this.auctionMarket);
        } catch (GoBack e) {
            System.out.println("\nBack to the startup menu.");
        } catch (LogOut e) {
            System.out.println("\nLogged out. Back to the startup menu.");
        }
    }


    // EFFECTS: Starts the program
    public static void main(String[] args) {
        new StartupInterface();
    }
}

