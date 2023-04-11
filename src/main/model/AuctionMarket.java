package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/*
Class that includes the list of items up for auction
 */
public class AuctionMarket implements Writable {

    private ArrayList<Item> itemsUpForAuction;

    // EFFECTS: constructs a new auction market
    public AuctionMarket() {
        this.itemsUpForAuction = new ArrayList<>();
    }

    // MODIFIES: this, item
    // EFFECTS: removes item from auctioning list, changes auction status of item to false
    public void auctionOffItem(Item item) {
        this.itemsUpForAuction.remove(item);
        item.changeStatusToAuctionedOff();
        EventLog.getInstance().logEvent(
                new Event("Auctioned Off (removed item from auction market): \"" + item.getItemName() + "\""));
    }

    // EFFECTS: returns true if item with the given input name is found, false otherwise.
    public Item searchForItem(String itemName) {
        for (Item item: this.itemsUpForAuction) {
            if (item.getItemName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    // EFFECTS: returns the list of items for auction
    public ArrayList<Item> getItemsUpForAuction() {
        return this.itemsUpForAuction;
    }

    // EFFECTS: Writes the items in the auction market to JSONArray
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Auction Market");
        jsonObject.put("items", itemsToJson());
        return jsonObject;
    }

    // EFFECTS: Puts each individual item from the auction market into the JSONArray;
    public JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item item: this.itemsUpForAuction) {
            jsonArray.put(item.toJson());
        }

        return jsonArray;
    }
}
