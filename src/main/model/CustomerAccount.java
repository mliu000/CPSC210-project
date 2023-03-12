package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.lang.reflect.Array;
import java.nio.file.Watchable;
import java.util.ArrayList;

/* Class that constructs the customer account, which includes username, password, balance and names of items won.
Also allows the user to change password and deposit/extract balance.
 */
public class CustomerAccount implements Account, Writable {
    private final String username;
    private String password;
    private int balance;

    private ArrayList<String> itemsWon;


    // EFFECTS: constructs a new customer account
    public CustomerAccount(String username, String password, int balance, ArrayList<String> itemsWon) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.itemsWon = itemsWon;
    }

    // EFFECTS: returns username
    @Override
    public String getUserName() {
        return username;
    }

    // EFFECTS: returns password
    @Override
    public String getPassword() {
        return password;
    }

    // MODIFIES: this
    // EFFECTS: changes password of user
    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    // MODIFIES: this
    // EFFECTS: increases balance by depositAmt
    public void depositIntoBalance(int depositAmt) {
        this.balance += depositAmt;
    }

    // MODIFIES: this
    // EFFECTS: decreases balance by extractAmt
    public void extractFromBalance(int extractAmt) {
        this.balance -= extractAmt;
    }




    // EFFECTS: returns balance
    public int getBalance() {
        return this.balance;
    }


    // EFFECTS: returns list of items won
    public ArrayList<String> getItemsWon() {
        return this.itemsWon;
    }

    // EFFECTS: adds the fields of the customer account into JSONArray to be able to be retrieved later.
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", this.username);
        jsonObject.put("password", this.password);
        jsonObject.put("balance", this.balance);
        jsonObject.put("items won", itemsWonLogToJson());
        return jsonObject;
    }

    // EFFECTS: adds the names of the items won to JSONArray to be able to be retrieved later
    // (under the scope of each item)
    public JSONArray itemsWonLogToJson() {
        JSONArray jsonArray = new JSONArray();
        for (String item: this.itemsWon) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", item);
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

}
