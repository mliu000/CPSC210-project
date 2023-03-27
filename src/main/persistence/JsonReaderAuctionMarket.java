package persistence;

import model.AuctionMarket;
import model.CustomerAccount;
import model.CustomerDatabase;
import model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

// Class that reads the items stored in the json auction market file and adds them to the auction market
public class JsonReaderAuctionMarket extends JsonReader {
    private String source;
    private ArrayList<CustomerAccount> customerDatabase;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderAuctionMarket(String source, ArrayList<CustomerAccount> customerDatabase) {
        this.customerDatabase = customerDatabase;
        this.source = source;
    }

    // EFFECTS: reads auction market from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AuctionMarket read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAuctionMarket(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // EFFECTS: parses auction market from JSON object and returns it
    private AuctionMarket parseAuctionMarket(JSONObject jsonObject) {
        AuctionMarket am = new AuctionMarket();
        addItems(am, jsonObject);
        return am;
    }

    // MODIFIES: auction market
    // EFFECTS: parses items from JSON object and adds them to auction market
    private void addItems(AuctionMarket am, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            this.addItem(am, nextThingy);
        }
    }

    // MODIFIES: auction market
    // EFFECTS: parses item from JSON object and adds it to auction market
    private void addItem(AuctionMarket am, JSONObject jsonObject) {
        int minBid = jsonObject.getInt("min bidding price");
        String name = jsonObject.getString("name");
        Item item = new Item(name, minBid, addCustomer(jsonObject));
        am.getItemsUpForAuction().add(item);
    }

    // MODIFIES: customerAccount
    // EFFECTS: attempts to find a customer account with same username as the one stored.
    //          if found, assigns the account to the field
    //             assigns a new placeholder customer if not match found.
    private CustomerAccount addCustomer(JSONObject jsonObject) {
        String username = jsonObject.getString("customer with the highest bid");
        for (CustomerAccount customerAccount: customerDatabase) {
            if (customerAccount.getUserName().equals(username)) {
                return customerAccount;
            }
        }
        return new CustomerAccount("Dummy account", "Notinuse", 0, new ArrayList<>());
    }
}