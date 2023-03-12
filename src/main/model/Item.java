package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Item implements Writable {

    private String itemName;
    private int minBiddingPrice;
    private CustomerAccount customerWithTheHighestBid;
    private boolean upForAuction = true;

    // EFFECTS: constructs a new item with a dummy customer account object that no one can access
    public Item(String name, int minBiddingPrice, CustomerAccount customerAccount) {
        this.itemName = name;
        this.minBiddingPrice = minBiddingPrice;
        this.customerWithTheHighestBid = customerAccount;
    }

    // MODIFIES: this, customer
    // EFFECTS: changes item auctioning status to false, adds items to customer's itemsWon list.
    public void changeStatusToAuctionedOff() {
        this.customerWithTheHighestBid.getItemsWon().add(this.itemName);
        this.upForAuction = false;
    }

    // MODIFIES: this, customer
    /* EFFECTS: if no customer has previously bidded on item, adds the customer, and extracts their bid from account.
    If there was a previous bid, return the bid to the previous customer, then extract the bid from new customer's
    balance.
     */
    public void updateBidStatus(CustomerAccount customer, int newBidPrice) {
        this.customerWithTheHighestBid.depositIntoBalance(this.minBiddingPrice);
        this.minBiddingPrice = newBidPrice;
        this.customerWithTheHighestBid = customer;
        this.customerWithTheHighestBid.extractFromBalance(newBidPrice);
    }

    // EFFECTS: returns minBiddingPrice
    public int getItemMinBiddingPrice() {
        return this.minBiddingPrice;
    }

    // EFFECTS: returns upForAuction
    public boolean isUpForAuction() {
        return this.upForAuction;
    }

    // EFFECTS: returns itemName
    public String getItemName() {
        return this.itemName;
    }

    // EFFECTS: returns customerWithTheHighestBid
    public CustomerAccount getCustomerWithTheHighestBid() {
        return this.customerWithTheHighestBid;
    }


    // EFFECTS: adds the fields of the item into JSONArray to be able to be retrieved later.
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.itemName);
        jsonObject.put("min bidding price", this.minBiddingPrice);
        jsonObject.put("customer with the highest bid", this.customerWithTheHighestBid.getUserName());
        jsonObject.put("currently up for auction", this.upForAuction);
        return jsonObject;
    }
}
