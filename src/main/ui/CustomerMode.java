package ui;

import exceptions.*;
import model.AuctionMarket;
import model.CustomerAccount;
import model.CustomerDatabase;
import model.Item;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Class that launches and contains the features and operations available of the customer mode
public class CustomerMode extends CommonOperations {
    private static final Scanner scanner = new Scanner(System.in);
    private CustomerAccount loggedInCustomer;
    private CustomerDatabase customerDatabase;
    private AuctionMarket auctionMarket;

    // EFFECTS: constructs a new customer mode
    public CustomerMode(CustomerDatabase database, AuctionMarket auctionMarket) throws GoBack, LogOut {
        this.auctionMarket = auctionMarket;
        this.customerDatabase = database;
        System.out.println("\nYou selected: Customer Mode");
        customerGreetingMenu();
    }

    // EFFECTS: prompts the user to either login of create account
    private void customerGreetingMenu() throws GoBack, LogOut {
        while (true) {
            System.out.println("\nPlease select one of the following options: \n (1) - Log In \n (2) - Create Account");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                login();
                break;
            } else if (input.equals("2")) {
                createAccount();
                break;
            }
            System.out.println("Please enter a valid option. Try again.");
        }
    }

    /* EFFECTS: prompts the user to enter username and password to login. Logs in if account credentials match an
    existing account.
     */
    private void login() throws GoBack, LogOut {
        CustomerAccount customerFound;

        while (true) {
            System.out.println("\nPlease enter your username:");
            String usernameInput = scanner.nextLine();

            System.out.println("please enter your password ");
            String passwordInput = scanner.nextLine();

            customerFound = this.customerDatabase.checkIfAccountExists(usernameInput, passwordInput);

            if (customerFound != null) {
                this.loggedInCustomer = customerFound;
                break;
            }

            System.out.println("Account not found/ username and/or password inputs are incorrect. Please try again.");

            goBack();
        }

        customerMainMenu();
    }

    // EFFECTS: prompts the customer to create account to by choosing a username and password
    private void createAccount() throws GoBack, LogOut {
        String username = createUsername();
        String password = createPassword();

        CustomerAccount customer = new CustomerAccount(username, password, 0, new ArrayList<>());
        this.loggedInCustomer = customer;
        customerDatabase.getCustomerAccountDatabase().add(customer);

        customerMainMenu();
    }

    // EFFECTS: prompts the user to create a username. Does not accept usernames under 4 chars.
    private String createUsername() throws GoBack {
        String username;

        while (true) {
            System.out.println("\nPlease enter a username at between 4 to 10 characters long without spaces:");
            String usernameInput = scanner.nextLine();
            username = usernameInput;

            if (usernameInput.length() < 4 || usernameInput.length() > 10 || usernameInput.contains(" ")) {
                System.out.println("Username not between 4 to 10 characters long without spaces. Try again.");
            } else if (customerDatabase.checkUsernameDuplicate(usernameInput)) {
                System.out.println("Account with username already exists. Please try again.");
            } else {
                break;
            }

            goBack();
        }

        return username;
    }

    // EFFECTS: prompts the user to create a password. Does not accept passwords with spaces or under 4 chars.
    private String createPassword() {
        String password;

        while (true) {
            System.out.println("\nPlease create a password at least 4 characters long without spaces:");
            String passwordInput = scanner.nextLine();
            password = passwordInput;
            if (passwordInput.length() >= 4 && !passwordInput.contains(" ")) {
                break;
            }
            System.out.println("Password must be at least 4 characters long and without spaces. Try again");
        }

        return password;
    }

    // EFFECTS: Gets the customer to choose an option from the main menu
    private void customerMainMenu() throws LogOut {
        System.out.println("\nLogged in as: " + this.loggedInCustomer.getUserName());
        System.out.println("\n***** You are officially in the auction market! Welcome! *****");
        while (true) {
            customerMainMenuOptions();
            String input = scanner.nextLine();
            if (input.equals("1")) {
                choice1();
            } else if (input.equals("2")) {
                choice2itemSearch();
            } else if (input.equals("3")) {
                choice3();
            } else if (input.equals("4")) {
                choice4();
            } else if (input.equals("5")) {
                choice5();
            } else if (input.equals("6")) {
                logout();
            } else {
                System.out.println("Please enter a valid option. Try again.");
            }
        }
    }

    // USER CHOICES BELOW:

    /* EFFECTS: Prints out auctioning items with parameters serving as variations in items printed from out from the
    customer and admin pov
     */
    private void choice1() {
        if (this.auctionMarket.getItemsUpForAuction().isEmpty()) {
            System.out.println("\nNo options up for auction currently. Please come back later");
        } else {
            System.out.println("\nTotal items up for auction: " + this.auctionMarket.getItemsUpForAuction().size());
            System.out.println("Below is a list of all items up for auction: ");
            for (Item item: this.auctionMarket.getItemsUpForAuction()) {
                System.out.println("\nName: " + item.getItemName());
                System.out.println("Minimum bid required: $" + item.getItemMinBiddingPrice());
            }
        }
    }

    // EFFECTS: prompts the user to enter an item name, and tells the user if item is found.
    private void choice2itemSearch() {
        System.out.println("\nPlease enter the name you want to bid on.");
        String itemSearch = scanner.nextLine();
        Item itemToBidOn = auctionMarket.searchForItem(itemSearch);
        if (itemToBidOn != null) {
            choice2bidOnItem(itemToBidOn);
        } else {
            System.out.println("Item not found.");
        }
    }

    // EFFECTS: prompts the user to enter a bid for their chosen item. If an ill input is received, it throws exceptions
    private void choice2bidOnItem(Item item) {
        try {
            System.out.println("Enter the $ amount you want to bid for: ");
            int bidAmount = scanner.nextInt();
            if (bidAmount < 0) {
                throw new NegativeNumberInput();
            } else if (bidAmount > this.loggedInCustomer.getBalance()) {
                System.out.println("Cannot bid for more than your current bank account balance");
                System.out.println("Your current balance is $" + this.loggedInCustomer.getBalance()
                        + " , you bidded for $" + bidAmount);
            } else if (bidAmount <= item.getItemMinBiddingPrice()) {
                System.out.println("\nYou must bid for more than the current minimum bidding price.");
            } else {
                System.out.println("Successfully bidded");
                item.updateBidStatus(this.loggedInCustomer, bidAmount);
            }
        } catch (NegativeNumberInput e) {
            System.out.println("\nCan not enter a negative bid");
        } catch (InputMismatchException e) {
            System.out.println("\nDid not enter number amount. Transaction cancelled");
        }
        scanner.nextLine();
    }

    // EFFECTS: prompts the user choose between depositing or extracting money
    private void choice3() {
        System.out.println("\nYour current balance for bidding is: $" + this.loggedInCustomer.getBalance());
        System.out.println("\nDo you to deposit or extract money? \n (1) - Deposit \n (2) - Extract");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            choice3subchoice1();
        } else if (choice.equals("2")) {
            if (this.loggedInCustomer.getBalance() > 0) {
                choice3subchoice2();
            } else {
                System.out.println("\nCannot proceed with transaction. No money in account.");
            }
        } else {
            System.out.println("Option invalid");
        }
    }

    // EFFECTS: prompts the user to enter deposit amount into account. Throws exception if bad input received
    private void choice3subchoice1() {
        System.out.println("\nEnter amount to deposit in $:");
        try {
            int depositAmount = scanner.nextInt();
            if (depositAmount < 0) {
                throw new NegativeNumberInput();
            }
            this.loggedInCustomer.depositIntoBalance(depositAmount);
            System.out.println("Successfully deposited! Your new balance is: $" + this.loggedInCustomer.getBalance());
        } catch (InputMismatchException e) {
            System.out.println("\nTransaction failed, did not enter an integer");
        } catch (NegativeNumberInput e) {
            System.out.println("\nCan not enter a negative number. Transaction terminated");
        }
        scanner.nextLine();
    }

    // EFFECTS: prompts user to enter extract amount. Throws exception if bad input received.
    private void choice3subchoice2() {
        try {
            System.out.println("\nEnter amount to extract in $:");
            int extractAmount = scanner.nextInt();
            if (extractAmount > this.loggedInCustomer.getBalance()) {
                throw new ExtractTooMuchMoney();
            }
            if (extractAmount < 0) {
                throw new NegativeNumberInput();
            }
            this.loggedInCustomer.extractFromBalance(extractAmount);
            System.out.println("Successfully extracted! Your new balance is: $" + this.loggedInCustomer.getBalance());
        } catch (InputMismatchException e) {
            System.out.println("\nTransaction failed, did not enter an integer");
        } catch (ExtractTooMuchMoney e) {
            System.out.println("\nTransaction failed, more money entered than present in bank account");
        } catch (NegativeNumberInput e) {
            System.out.println("\nCannot enter a negative number. Transaction terminated");
        }
        scanner.nextLine();
    }

    // EFFECTS: prompts the user to enter new password. Does not accept passwords < 4 chars
    private void choice4() {
        System.out.println("Please enter new password at least 4 characters long without spaces: ");
        String newPassword = scanner.nextLine();
        try {
            if (newPassword.length() < 4 || newPassword.contains(" ")) {
                throw new PasswordDoesNotMeetRequirement();
            }
            System.out.println("Please confirm password: ");
            String newPasswordConfirm = scanner.nextLine();
            if (newPassword.equals(newPasswordConfirm)) {
                this.loggedInCustomer.changePassword(newPasswordConfirm);
                System.out.println("Password has been changed.");
            } else {
                System.out.println("Passwords do not match. Password not changed.");
            }
        } catch (PasswordDoesNotMeetRequirement e) {
            System.out.println("Password must be at least 4 characters long without spaces.");
        }
    }

    private void choice5() {
        if (this.loggedInCustomer.getItemsWon().isEmpty()) {
            System.out.println("\nHaven't won any items as of yet.");
        } else {
            System.out.println("Total item(s) won: " + this.loggedInCustomer.getItemsWon().size());
            System.out.println("Below is a list of all item you have won\n");
            for (String name: this.loggedInCustomer.getItemsWon()) {
                System.out.println("Item name: " + name);
            }
        }
    }

    // EFFECTS: prints out the main menu options
    private void customerMainMenuOptions() {
        System.out.println("\n*** Customer Main Menu ***");
        System.out.println("Please choose an option:");
        System.out.println(" (1) - View available items to bid and the bid required at the moment.");
        System.out.println(" (2) - Bid on an item.");
        System.out.println(" (3) - Deposit or extract from balance.");
        System.out.println(" (4) - Change Password.");
        System.out.println(" (5) - View items won");
        System.out.println(" (6) - Log out.");
    }
}
