package ui;

import model.AdminAccount;
import model.AuctionMarket;
import model.CustomerDatabase;
import persistence.JsonReaderAdminAccount;
import persistence.JsonReaderAuctionMarket;
import persistence.JsonReaderCustomerDatabase;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Launcher extends JFrame implements ActionListener {

    // Databases with saved memory

    CustomerDatabase customerDatabase = new CustomerDatabase();
    AuctionMarket auctionMarket = new AuctionMarket();
    AdminAccount adminAccount = new AdminAccount("AuctionAdmin", "DoNotLeak1010");

    // JSON Storage Addresses

    private static final String JSON_AUCTION_MARKET_STORAGE = "./data/AuctionMarketData.json";
    private static final String JSON_CUSTOMER_DATABASE_STORAGE = "./data/CustomerDatabaseData.json";
    private static final String JSON_ADMIN_ACCOUNT_STORAGE = "./data/AdminAccountData.json";

    // JSON writers

    private final JsonReaderAuctionMarket jsonReaderAuctionMarket
            = new JsonReaderAuctionMarket(JSON_AUCTION_MARKET_STORAGE,
            this.customerDatabase.getCustomerAccountDatabase());
    private final JsonReaderCustomerDatabase jsonReaderCustomerAccount =
            new JsonReaderCustomerDatabase(JSON_CUSTOMER_DATABASE_STORAGE);

    private final JsonReaderAdminAccount jsonReaderAdminAccount =
            new JsonReaderAdminAccount(JSON_ADMIN_ACCOUNT_STORAGE);

    // Gui fields

    private ConstructGui guiMaker;
    private JPanel panel = new JPanel();
    private ImageIcon backgroundImageIcon = new ImageIcon("./images/Screen Shot 2023-03-27 at 11.17.58 AM.png");
    private JLabel background = new JLabel(this.backgroundImageIcon);

    public Launcher() {
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        this.guiMaker.constructLabel(this.background, 100, 80, 900, 60, "LOAD SAVED DATA OR START FROM SCRATCH",
                "Verdana", true, Font.BOLD, 30);

        constructButton(new JButton("Load saved data"), 200);
        constructButton(new JButton("Start from scratch"), 400);

    }

    private void constructButton(JButton button, int y) {
        button.setFont(new Font("Verdana", Font.BOLD, 25));
        button.setBounds(300, y, 400, 100);
        button.setVisible(true);
        button.setBackground(new Color(164, 63, 252));
        button.setOpaque(true);
        button.addActionListener(this);
        this.background.add(button);
    }

    // EFFECTS: Loads the saved data from their respective json storage files
    public void loadData() {

        try {
            this.customerDatabase = jsonReaderCustomerAccount.read(this.customerDatabase);
        } catch (IOException e) {
            // Unable to load
        }

        try {
            this.auctionMarket = jsonReaderAuctionMarket.read();
        } catch (IOException e) {
            // Unable to load
        }

        try {
            this.adminAccount = jsonReaderAdminAccount.read();
        } catch (IOException e) {
            // Unable to load
        }
    }

    public static void main(String[] args) {
        new Launcher();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.background.getComponent(1)) {
            loadData();
            dispose();
            new StartupInterfaceGui(this.customerDatabase, this.auctionMarket, this.adminAccount, true, false);
        } else {
            dispose();
            new StartupInterfaceGui(this.customerDatabase, this.auctionMarket, this.adminAccount, false, true);
        }
    }
}
