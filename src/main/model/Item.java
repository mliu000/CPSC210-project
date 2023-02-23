package model;

public class Item {

    private String itemName;
    private int minBiddingPrice;
    private CustomerAccount customerWithTheHighestBid;
    private boolean upForAuction = true;

    // EFFECTS: constructs a new item without a single customer bid
    public Item(String name, int minBiddingPrice) {
        this.itemName = name;
        this.minBiddingPrice = minBiddingPrice;
        this.customerWithTheHighestBid = null;
    }

    // MODIFIES: this, customer
    // EFFECTS: changes item auctioning status to false, adds items to customer's itemsWon list.
    public void changeStatusToAuctionedOff() {
        this.customerWithTheHighestBid.getItemsWon().add(this);
        this.upForAuction = false;
    }

    // MODIFIES: this, customer
    /* EFFECTS: if no customer has previously bidded on item, adds the customer, and extracts their bid from account.
    If there was a previous bid, return the bid to the previous customer, then extract the bid from new customer's
    balance.
     */
    public void updateBidStatus(CustomerAccount customer, int newBidPrice) {
        if (this.customerWithTheHighestBid != null) {
            this.customerWithTheHighestBid.depositIntoBalance(this.minBiddingPrice);
        }
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


}
