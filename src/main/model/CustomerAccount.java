package model;

import java.util.ArrayList;

public class CustomerAccount implements Account {
    private final String username;
    private String password;
    private int balance;
    private int numberOfItemsBidded;
    private ArrayList<Item> recentlyBiddedItemsLog;

    private final int recentlyBiddedListMaxSize = 5;

    public CustomerAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.numberOfItemsBidded = 0;
        this.recentlyBiddedItemsLog = new ArrayList<>();
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void depositIntoBalance(int depositAmt) {
        this.balance += depositAmt;
    }

    public void extractFromBalance(int extractAmt) {
        this.balance -= extractAmt;
    }

    public void addItemToRecentlyBidded(Item item) {
        if (this.recentlyBiddedItemsLog.size() == recentlyBiddedListMaxSize) {
            this.recentlyBiddedItemsLog.remove(0);
            this.recentlyBiddedItemsLog.add(item);
        }
    }

    public void printItemsRecentlyBidded() {
        if (recentlyBiddedItemsLog.isEmpty()) {
            System.out.println("No recently bidded items");
        } else {
            for (int i = recentlyBiddedItemsLog.size(); i-- > 0; ) {
                Item item = recentlyBiddedItemsLog.get(i);
                if (item.isUpForAuction()) {
                    System.out.println("\nItem name: " + item.getItemName());
                    System.out.println("Current highest bid: " + item.getItemMinBiddingPrice());
                    if (item.getCustomerWithTheHighestBid() == this) {
                        System.out.println("You currently have the highest bid for this item");
                    } else {
                        System.out.println("You do not have the highest bid for this item");
                    }
                } else {
                    System.out.println("Item has been auctioned off already :(");
                }
            }
        }
    }


    public int getBalance() {
        return balance;
    }

    public int getNumberOfItemsBidded() {
        return numberOfItemsBidded;
    }

    public ArrayList<Item> getRecentlyBiddedItemsLog() {
        return recentlyBiddedItemsLog;
    }
}
