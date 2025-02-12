package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Class that stores all the customer accounts.
public class CustomerDatabase implements Writable {

    private ArrayList<CustomerAccount> customerAccountDatabase;

    // EFFECTS: Constructs a new customer database
    public CustomerDatabase() {
        this.customerAccountDatabase = new ArrayList<>();
    }

    // EFFECTS: checks if account with username and password match the corresponding inputs.
    public CustomerAccount checkIfAccountExists(String usernameInput, String passwordInput) {
        for (CustomerAccount customerAccount: this.customerAccountDatabase) {
            if (customerAccount.getUserName().equals(usernameInput)
                    && customerAccount.getPassword().equals(passwordInput)) {
                return customerAccount;
            }
        }
        return null;
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

    // EFFECTS: Writes the items in the customer database to JSONArray
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Customer Database");
        jsonObject.put("accounts", accountsToJson());
        return jsonObject;
    }

    // EFFECTS: Puts each individual customer from the customer account into the JSONArray;
    public JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CustomerAccount customerAccount: this.customerAccountDatabase) {
            jsonArray.put(customerAccount.toJson());
        }

        return jsonArray;
    }
}
