package model;

import java.util.ArrayList;

public class CustomerAccount implements Account {
    private final String username;
    private String password;
    private int balance;
    private ArrayList<Item> recentlyBiddedItemsLog;

    private ArrayList<Item> itemsWon;

    private final int recentlyBiddedListMaxSize = 5;

    // EFFECTS: constructs a new customer account
    public CustomerAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.recentlyBiddedItemsLog = new ArrayList<>();
        this.itemsWon = new ArrayList<>();
    }

    // EFFECTS: returns username
    @Override
    public String getUserName() {
        return username;
    }

    // EFFECTS: returns password
    @Override
    public String getPassword() {
        return password;
    }

    // MODIFIES: this
    // EFFECTS: changes password of user
    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    // MODIFIES: this
    // EFFECTS: increases balance by depositAmt
    public void depositIntoBalance(int depositAmt) {
        this.balance += depositAmt;
    }

    // MODIFIES: this
    // EFFECTS: decreases balance by extractAmt
    public void extractFromBalance(int extractAmt) {
        this.balance -= extractAmt;
    }

    // MODIFIES: this
    /* EFFECTS: adds item to recently bidded list with 3 cases. If list is not full and does not contain the item about
    to added, adds item normally. If it does, then removes the old instance and adds the new one. If list is already
    full, removes the first item on list before adding the new item.
     */
    public void addItemToRecentlyBidded(Item newItem) {
        if (this.recentlyBiddedItemsLog.contains(newItem)) {
            this.recentlyBiddedItemsLog.remove(newItem);
            this.recentlyBiddedItemsLog.add(newItem);
        } else if (this.recentlyBiddedItemsLog.size() == this.recentlyBiddedListMaxSize) {
            this.recentlyBiddedItemsLog.remove(0);
            this.recentlyBiddedItemsLog.add(newItem);
        } else {
            this.recentlyBiddedItemsLog.add(newItem);
        }
    }

    // EFFECTS: prints out recently bidded items in reverse order (items at the end of the list get printed first)
    public void printItemsRecentlyBidded() {
        if (recentlyBiddedItemsLog.isEmpty()) {
            System.out.println("\nNo recently bidded items");
        } else {
            System.out.println("\nRecently bidded items starting with most recent: ");
            for (int i = recentlyBiddedItemsLog.size(); i-- > 0; ) {
                Item item = recentlyBiddedItemsLog.get(i);
                System.out.println("\nItem name: " + item.getItemName());
                if (item.isUpForAuction()) {
                    System.out.println("Current highest bid: $" + item.getItemMinBiddingPrice());
                    if (item.getCustomerWithTheHighestBid() == this) {
                        System.out.println("You currently have the highest bid for this item");
                    } else {
                        System.out.println("You do not have the highest bid for this item");
                    }
                } else if (this.itemsWon.contains(item)) {
                    System.out.println("You won this item!!!!!!");
                } else {
                    System.out.println("Item has been auctioned off already :(");
                }
            }
        }
    }

    // EFFECTS: prints out items won
    public void printOutItemsWon() {
        if (this.itemsWon.isEmpty()) {
            System.out.println("\nHaven't won any items as of yet.");
        } else {
            System.out.println("Total item(s) won: " + this.itemsWon.size());
            System.out.println("Below is a list of all item you have won");
            for (Item item: this.itemsWon) {
                System.out.println("\nItem name: " + item.getItemName());
                System.out.println("Final bidding price: $" + item.getItemMinBiddingPrice());
            }
        }
    }

    // EFFECTS: returns balance
    public int getBalance() {
        return this.balance;
    }

    // EFFECTS: returns recently bidded item list
    public ArrayList<Item> getRecentlyBiddedItemsLog() {
        return this.recentlyBiddedItemsLog;
    }

    // EFFECTS: returns list of items won
    public ArrayList<Item> getItemsWon() {
        return this.itemsWon;
    }
}
