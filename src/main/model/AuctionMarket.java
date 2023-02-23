package model;

import java.util.ArrayList;

public class AuctionMarket {

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
}
