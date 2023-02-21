package ui;

import exceptions.GoBack;
import exceptions.LogOut;
import exceptions.NameDoesNotMeetRequirement;
import exceptions.PasswordDoesNotMeetRequirement;
import model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMode extends CommonOperations {

    private static final Scanner scanner = new Scanner(System.in);
    private AdminAccount adminAccount;
    private AuctionMarket auctionMarket;
    private CustomerDatabase customerDatabase;

    public AdminMode(AuctionMarket auctionMarket, AdminAccount adminAccount,
                     CustomerDatabase customerDatabase) throws GoBack, LogOut {
        this.adminAccount = adminAccount;
        this.auctionMarket = auctionMarket;
        this.customerDatabase = customerDatabase;
        System.out.println("You selected: Admin mode");
        login();
    }

    private void login() throws GoBack, LogOut {
        boolean loggedIn = false;

        while (true) {
            System.out.println("\nPlease enter the admin master username: ");
            String usernameInput = scanner.nextLine();

            System.out.println("Please enter the admin master password: ");
            String passwordInput = scanner.nextLine();

            if (adminAccount.getUserName().equals(usernameInput)
                    && adminAccount.getPassword().equals(passwordInput)) {
                loggedIn = true;
            }

            if (loggedIn) {
                break;
            }

            System.out.println("Credentials are incorrect. Please try again.");

            goBack();
        }

        adminMainMenu();
    }



    private void adminMainMenu() throws LogOut {
        System.out.println("\nYou are now logged in as admin. This is the control center to the auction market");
        while (true) {
            System.out.println("\n*** Admin Main Menu ***");
            System.out.println("Please select an option: ");
            adminMainMenuOptions();
            String input = scanner.nextLine();
            if (input.equals("1")) {
                choice1();
            } else if (input.equals("2")) {
                choice2();
            } else if (input.equals("3")) {
                System.out.println("3");
            } else if (input.equals("4")) {
                this.customerDatabase.printOutCustomerDatabase();
            } else if (input.equals("5")) {
                choice5();
            } else if (input.equals("6")) {
                logout();
            } else {
                System.out.println("Please enter a valid option. Try again.");
            }
        }
    }

    private void choice1() {
        String emptyMessage = "\nNo options up for auction currently, start a bid soon!";
        auctionMarket.printAuctioningItems(emptyMessage, true);
    }

    private void choice2() {
        try {
            System.out.println("\nPlease enter the name of item at least 3 characters long: ");
            String itemName = scanner.nextLine();
            if (itemName.length() < 3) {
                throw new NameDoesNotMeetRequirement();
            }
            System.out.println("Please set a minimum bidding price for item in $: ");
            int minimumBiddingPrice = scanner.nextInt();
            Item newItem = new Item(itemName, minimumBiddingPrice);
            auctionMarket.getItemsUpForAuction().add(newItem);
            System.out.println("Item added to market. Success");
        } catch (InputMismatchException e) {
            System.out.println("Did not enter a number, item not added to market");
        } catch (NameDoesNotMeetRequirement e) {
            System.out.println("Name not at least 3 characters long, operation terminated");
        }
    }

    private void choice5() {
        CheckString passwordVerifier = new CheckString();
        System.out.println("Please enter password at least 10 characters long, contains at least one uppercase ");
        System.out.println("character, one lowercase character, one digit, and no spaces: ");
        String newPassword = scanner.nextLine();
        try {
            if (!passwordVerifier.containsUpperCaseLetters(newPassword)
                    || !passwordVerifier.containsLowerCaseLetters(newPassword)
                    || !passwordVerifier.containsDigits(newPassword)
                    || (newPassword.length() < 10) || newPassword.contains(" ")) {
                throw new PasswordDoesNotMeetRequirement();
            }
            System.out.println("Please confirm password: ");
            String newPasswordConfirm = scanner.nextLine();
            if (newPasswordConfirm.equals(newPassword)) {
                this.adminAccount.changePassword(newPasswordConfirm);
                System.out.println("Password has been changed");
            } else {
                System.out.println("Passwords do not match. Password not changed.");
            }
        } catch (PasswordDoesNotMeetRequirement e) {
            System.out.println("Password does not requirement");
        }
    }

    private void adminMainMenuOptions() {
        System.out.println(" (1) - View items currently on market.");
        System.out.println(" (2) - Start a bid.");
        System.out.println(" (3) - End a bid");
        System.out.println(" (4) - View Customer database");
        System.out.println(" (5) - Change password");
        System.out.println(" (6) - Logout");
    }


}
