package model;

public class Item {

    private String itemName;
    private int minBiddingPrice;
    private CustomerAccount customerWithTheHighestBid;
    private boolean upForAuction = true;

    public Item(String name, int minBiddingPrice) {
        this.itemName = name;
        this.minBiddingPrice = minBiddingPrice;
        this.customerWithTheHighestBid = null;
    }

    public void changeStatusToAuctionedOff() {
        this.upForAuction = false;
    }

    public void updateCustomerWithHighestBid(CustomerAccount customer) {
        this.customerWithTheHighestBid = customer;
    }

    public int getItemMinBiddingPrice() {
        return minBiddingPrice;
    }

    public boolean isUpForAuction() {
        return upForAuction;
    }

    public String getItemName() {
        return itemName;
    }

    public CustomerAccount getCustomerWithTheHighestBid() {
        return customerWithTheHighestBid;
    }


}
