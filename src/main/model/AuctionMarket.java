package model;

import java.util.ArrayList;

public class AuctionMarket {

    private ArrayList<Item> itemsUpForAuction;

    // EFFECTS: constructs a new auction market
    public AuctionMarket() {
        this.itemsUpForAuction = new ArrayList<>();
    }

    /* EFFECTS: Prints out auctioning items with parameters serving as variations in items printed from out from the
    customer and admin pov
     */
    public void printAuctioningItems(String emptyMessage, String bidMessage, boolean viewCustomerInfo) {
        if (this.itemsUpForAuction.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            System.out.println("\nTotal items up for auction: " + itemsUpForAuction.size());
            System.out.println("Below is a list of all items up for auction: ");
            for (Item item: this.itemsUpForAuction) {
                System.out.println("\nName: " + item.getItemName());
                System.out.println(bidMessage + item.getItemMinBiddingPrice());
                if (viewCustomerInfo) {
                    if (item.getCustomerWithTheHighestBid() != null) {
                        System.out.println("Customer with the highest bid: "
                                + item.getCustomerWithTheHighestBid().getUserName());
                    } else {
                        System.out.println("No customer has bidded on this item yet.");
                    }
                }
            }
        }
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
