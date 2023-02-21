package model;

import java.util.ArrayList;

public class CustomerDatabase {

    private ArrayList<CustomerAccount> customerAccountDatabase;

    public CustomerDatabase() {
        this.customerAccountDatabase = new ArrayList<>();
    }

    public ArrayList<CustomerAccount> getCustomerAccountDatabase() {
        return customerAccountDatabase;
    }

    public CustomerAccount checkIfAccountExists(String usernameInput, String passwordInput) {
        for (CustomerAccount customerAccount: customerAccountDatabase) {
            if (customerAccount.getUserName().equals(usernameInput)
                    && customerAccount.getPassword().equals(passwordInput)) {
                return customerAccount;
            }
        }
        return null;
    }

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
                System.out.println("# of items bidded: " + customerAccount.getNumberOfItemsBidded());
            }
        }
    }

    public boolean checkUsernameDuplicate(String username) {
        for (CustomerAccount customerAccount: this.customerAccountDatabase) {
            if (customerAccount.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
