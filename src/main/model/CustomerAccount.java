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
