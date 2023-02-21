package model;

import java.util.ArrayList;

public class AuctionMarket {

    private ArrayList<Item> itemsUpForAuction;

    public AuctionMarket() {
        this.itemsUpForAuction = new ArrayList<Item>();
    }

    public void printAuctioningItems(String emptyMessage, boolean viewCustomer) {
        if (this.itemsUpForAuction.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            System.out.println("\nTotal items up for auction: " + itemsUpForAuction.size());
            System.out.println("Below is a list of all items up for auction: ");
            for (Item item: this.itemsUpForAuction) {
                System.out.println("\nName: " + item.getItemName());
                System.out.println("Current minimum bidding price: $" + item.getItemMinBiddingPrice());
                if (viewCustomer) {
                    if (item.getCustomerWithTheHighestBid() != null) {
                        System.out.println("Customer with the highest bid: " + item.getCustomerWithTheHighestBid());
                    } else {
                        System.out.println("No customer has bidded on this item yet.");
                    }
                }
            }
        }
    }

    public ArrayList<Item> getItemsUpForAuction() {
        return this.itemsUpForAuction;
    }
}
