package ui;

import model.AdminAccount;
import model.AuctionMarket;
import model.CustomerDatabase;
import persistence.JsonWriter;

import java.io.FileNotFoundException;

public class SaveData {

    // json storage addresses

    private static final String JSON_AUCTION_MARKET_STORAGE = "./data/AuctionMarketData.json";
    private static final String JSON_CUSTOMER_DATABASE_STORAGE = "./data/CustomerDatabaseData.json";
    private static final String JSON_ADMIN_ACCOUNT_STORAGE = "./data/AdminAccountData.json";

    // Json readers

    private final JsonWriter jsonWriterAuctionMarket = new JsonWriter(JSON_AUCTION_MARKET_STORAGE);
    private final JsonWriter jsonWriterCustomerDatabase = new JsonWriter(JSON_CUSTOMER_DATABASE_STORAGE);
    private final JsonWriter jsonWriterAdminAccount = new JsonWriter(JSON_ADMIN_ACCOUNT_STORAGE);

    public SaveData(AuctionMarket auctionMarket, AdminAccount adminAccount, CustomerDatabase customerDatabase) {
        saveAuctionMarket(auctionMarket);
        saveAdminAccount(adminAccount);
        saveCustomerDatabase(customerDatabase);
    }

    // EFFECTS: Saves customer database to json storage
    public void saveAuctionMarket(AuctionMarket auctionMarket) {
        try {
            jsonWriterAuctionMarket.open();
            jsonWriterAuctionMarket.write(auctionMarket);
            jsonWriterAuctionMarket.close();
            System.out.println("\nSaved Auction Market to: " + JSON_AUCTION_MARKET_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_AUCTION_MARKET_STORAGE);
        }
    }

    // EFFECTS: Saves customer database to json storage
    public void saveCustomerDatabase(CustomerDatabase customerDatabase) {
        try {
            jsonWriterCustomerDatabase.open();
            jsonWriterCustomerDatabase.write(customerDatabase);
            jsonWriterCustomerDatabase.close();
            System.out.println("Saved Customer Database to: " + JSON_CUSTOMER_DATABASE_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_CUSTOMER_DATABASE_STORAGE);
        }
    }

    // EFFECTS: saves admin account to json storage
    public void saveAdminAccount(AdminAccount adminAccount) {
        try {
            jsonWriterAdminAccount.open();
            jsonWriterAdminAccount.write(adminAccount);
            jsonWriterAdminAccount.close();
            System.out.println("Saved Admin Account to: " + JSON_ADMIN_ACCOUNT_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_ADMIN_ACCOUNT_STORAGE);
        }
    }
}
