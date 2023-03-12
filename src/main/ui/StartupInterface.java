package ui;

import exceptions.GoBack;
import exceptions.LogOut;
import model.AdminAccount;
import model.AuctionMarket;
import model.CustomerDatabase;
import persistence.JsonReaderAdminAccount;
import persistence.JsonReaderAuctionMarket;
import persistence.JsonReaderCustomerDatabase;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Class that starts the program
public class StartupInterface {

    private static final String JSON_AUCTION_MARKET_STORAGE = "./data/AuctionMarketData.json";
    private static final String JSON_CUSTOMER_DATABASE_STORAGE = "./data/CustomerDatabaseData.json";
    private static final String JSON_ADMIN_ACCOUNT_STORAGE = "./data/AdminAccountData.json";

    private static final Scanner scanner = new Scanner(System.in);
    CustomerDatabase customerDatabase = new CustomerDatabase();
    AuctionMarket auctionMarket = new AuctionMarket();
    AdminAccount adminAccount = new AdminAccount("AuctionAdmin", "DoNotLeak1010");
    private final JsonWriter jsonWriterAuctionMarket = new JsonWriter(JSON_AUCTION_MARKET_STORAGE);
    private final JsonWriter jsonWriterCustomerDatabase = new JsonWriter(JSON_CUSTOMER_DATABASE_STORAGE);
    private final JsonWriter jsonWriterAdminAccount = new JsonWriter(JSON_ADMIN_ACCOUNT_STORAGE);
    private final JsonReaderAuctionMarket jsonReaderAuctionMarket
            = new JsonReaderAuctionMarket(JSON_AUCTION_MARKET_STORAGE,
            this.customerDatabase.getCustomerAccountDatabase());
    private final JsonReaderCustomerDatabase jsonReaderCustomerAccount =
            new JsonReaderCustomerDatabase(JSON_CUSTOMER_DATABASE_STORAGE);

    private final JsonReaderAdminAccount jsonReaderAdminAccount =
            new JsonReaderAdminAccount(JSON_ADMIN_ACCOUNT_STORAGE);

    // EFFECTS: Constructs a new starting interface
    public StartupInterface() {
        loadData();
        System.out.println("\n******* Welcome to the Mu Ye's auction bidding market! *******");
        startupGreetingMenu();
    }

    // EFFECTS: prints out the greeting menu. Also, asks the user which mode they want to use
    public void startupGreetingMenu() {
        while (true) {
            String input;
            printOutOptions();
            input = scanner.nextLine();

            if (input.equals("1")) {
                launchAdminMode();
            } else if (input.equals("2")) {
                launchCustomerMode();
            } else if (input.equals("3")) {
                saveAuctionMarket();
                saveCustomerDatabase();
                saveAdminAccount();
                System.out.println("Exited Auction market. Bye :)");
                System.exit(0);
            } else if (input.equals("4")) {
                System.out.println("Exited Auction market. Bye :)");
                System.exit(0);
            } else {
                System.out.println("Please enter a valid option. Try again.");
            }
        }
    }

    // EFFECTS: prints out the options from the startup menu
    private void printOutOptions() {
        System.out.println("\nPlease select one of the following options below:");
        System.out.println(" (1) - Administrator");
        System.out.println(" (2) - Customer");
        System.out.println(" (3) - Save and Exit");
        System.out.println(" (4) - Exit without saving");
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

    // EFFECTS: Saves customer database to json storage
    public void saveAuctionMarket() {
        try {
            jsonWriterAuctionMarket.open();
            jsonWriterAuctionMarket.write(auctionMarket);
            jsonWriterAuctionMarket.close();
            System.out.println("\nSaved Auction Market to: " + JSON_AUCTION_MARKET_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_AUCTION_MARKET_STORAGE);
        }
    }

    // EFFECTS: Saves customer database to json storage
    public void saveCustomerDatabase() {
        try {
            jsonWriterCustomerDatabase.open();
            jsonWriterCustomerDatabase.write(customerDatabase);
            jsonWriterCustomerDatabase.close();
            System.out.println("Saved Customer Database to: " + JSON_CUSTOMER_DATABASE_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_CUSTOMER_DATABASE_STORAGE);
        }
    }

    // EFFECTS: saves admin account to json storage
    public void saveAdminAccount() {
        try {
            jsonWriterAdminAccount.open();
            jsonWriterAdminAccount.write(adminAccount);
            jsonWriterAdminAccount.close();
            System.out.println("Saved Admin Account to: " + JSON_ADMIN_ACCOUNT_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_ADMIN_ACCOUNT_STORAGE);
        }
    }

    // EFFECTS: Loads the saved data from their respective json storage files
    public void loadData() {

        try {
            this.customerDatabase = jsonReaderCustomerAccount.read(this.customerDatabase);
        } catch (IOException e) {
            System.out.println("Could not load data from: " + JSON_CUSTOMER_DATABASE_STORAGE);
        }

        try {
            auctionMarket = jsonReaderAuctionMarket.read();
        } catch (IOException e) {
            System.out.println("Could not load data from: " + JSON_AUCTION_MARKET_STORAGE);
        }

        try {
            adminAccount = jsonReaderAdminAccount.read();
        } catch (IOException e) {
            System.out.println("Could not load data from: " + JSON_ADMIN_ACCOUNT_STORAGE);
        }
    }


    // EFFECTS: Starts the program
    public static void main(String[] args) {
        new StartupInterface();
    }
}

