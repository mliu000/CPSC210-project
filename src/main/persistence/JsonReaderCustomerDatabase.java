package persistence;

import model.CustomerAccount;
import model.CustomerDatabase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/* Class that reads the customer accounts stored in the customer database json file,
then adds it to the customer database
 */
public class JsonReaderCustomerDatabase extends JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderCustomerDatabase(String source) {
        this.source = source;

    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CustomerDatabase read(CustomerDatabase cd) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCustomerDatabase(jsonObject, cd);
    }

    // EFFECTS: reads source file as string and returns it

    // EFFECTS: parses customer database from JSON object and returns it
    private CustomerDatabase parseCustomerDatabase(JSONObject jsonObject, CustomerDatabase cd) {
        addItems(cd, jsonObject);
        return cd;
    }

    // MODIFIES: customerDatabase
    // EFFECTS: parses customer accounts from JSON object and adds them to storaffe
    private void addItems(CustomerDatabase cd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            this.addItem(cd, nextItem);
        }
    }

    // MODIFIES: customerDatabase
    // EFFECTS: parses customerAccount from JSON object and adds it to customerDatabase
    private void addItem(CustomerDatabase cd, JSONObject jsonObject) {
        int balance = jsonObject.getInt("balance");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        CustomerAccount account = new CustomerAccount(username, password, balance, addItemsWon(jsonObject));
        cd.getCustomerAccountDatabase().add(account);
    }

    // MODIFIES: customerAccount
    // EFFECTS: parses itemsWon array from each customerAccount
    // and adds it to the itemsWon field of the customerAccount.
    public ArrayList<String> addItemsWon(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items won");
        ArrayList<String> itemsWon = new ArrayList<>();
        for (Object json: jsonArray) {
            JSONObject nextString = (JSONObject) json;
            addItemWon(nextString, itemsWon);
        }
        return itemsWon;
    }

    // EFFECTS: Parses each item from customer account individually.
    private void addItemWon(JSONObject jsonObject, ArrayList<String> itemsWon) {
        String itemName = jsonObject.getString("name");
        itemsWon.add(itemName);
    }
}
