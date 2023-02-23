package model;

import java.util.ArrayList;

public class CustomerDatabase {

    private ArrayList<CustomerAccount> customerAccountDatabase;

    // EFFECTS: Constructs a new customer database
    public CustomerDatabase() {
        this.customerAccountDatabase = new ArrayList<>();
    }

    // EFFECTS: checks if account with username and password match the curresponding inputs.
    public CustomerAccount checkIfAccountExists(String usernameInput, String passwordInput) {
        for (CustomerAccount customerAccount: this.customerAccountDatabase) {
            if (customerAccount.getUserName().equals(usernameInput)
                    && customerAccount.getPassword().equals(passwordInput)) {
                return customerAccount;
            }
        }
        return null;
    }

    // EFFECTS: prints out the customer database
    public void printOutCustomerDatabase() {
        if (this.customerAccountDatabase.isEmpty()) {
            System.out.println("\nNo customers are currently registered at the moment");
        } else {
            System.out.println("\nThere are " + this.customerAccountDatabase.size() + " customer(s) registered.");
            System.out.println("Below is a list of every customer's information.");
            for (CustomerAccount customerAccount : this.customerAccountDatabase) {
                System.out.println("\nUsername: " + customerAccount.getUserName());
                System.out.println("Password: " + customerAccount.getPassword());
                System.out.println("Balance: $" + customerAccount.getBalance());
            }
        }
    }

    // EFFECTS: returns true if username of existing account matches with the input, false otherwise
    public boolean checkUsernameDuplicate(String username) {
        for (CustomerAccount customerAccount: this.customerAccountDatabase) {
            if (customerAccount.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns customer database
    public ArrayList<CustomerAccount> getCustomerAccountDatabase() {
        return this.customerAccountDatabase;
    }

}
