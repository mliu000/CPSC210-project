package ui;

import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class AdminManageAuctionMarketGui extends JFrame implements ActionListener {

    // Databases with saved memory
    private AdminAccount adminAccount;
    private AuctionMarket auctionMarket;
    private CustomerDatabase customerDatabase;

    // Fields needed for GUI render
    private ConstructGui guiMaker;
    private JPanel panel = new JPanel();
    private final ImageIcon backgroundImageIcon = new ImageIcon("./images/Screen Shot 2023-03-27 at 11.17.58 AM.png");
    private JLabel background = new JLabel(this.backgroundImageIcon);
    private JTextField startBidItemName = new JTextField();
    private JTextField startBidMinBidAmt = new JTextField();
    private JTextField endBidItemName = new JTextField();

    private JScrollPane display;


    public AdminManageAuctionMarketGui(AdminAccount adminAccount, AuctionMarket auctionMarket,
                                       CustomerDatabase customerDatabase) {
        super("Auction Market");
        this.adminAccount = adminAccount;
        this.auctionMarket = auctionMarket;
        this.customerDatabase = customerDatabase;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructManageAuctionMarketGui();
        constructButtons();
        constructStartNewBidInterface();
        constructEndBidInterface();
        constructItemListing();
    }

    private void constructManageAuctionMarketGui() {

        this.guiMaker.constructLabel(this.background, 230, 50, 800, 60, "MANAGE MARKET",
                "Verdana", true, Font.BOLD, 50);

        this.guiMaker.constructLabel(this.background, 100, 150, 500, 60, "ITEMS ON MARKET",
                "Verdana", true, Font.BOLD, 30);

        this.guiMaker.constructLabel(this.background, 650, 150, 500, 60, "MANAGE ITEM",
                "Verdana", true, Font.BOLD, 30);


    }

    private void constructButtons() {
        constructButton(new JButton("Go Back"), 50, 600, 200, 40, Font.BOLD, 20, true); //3
        constructButton(new JButton("Return"), 600, 550, 200, 40, Font.BOLD, 20, false);
        constructButton(new JButton("Start a bid"), 600, 250, 350, 70, Font.BOLD, 20, true);
        constructButton(new JButton("Auction off item"), 600, 350, 350, 70, Font.BOLD, 20, true);
    }

    private void constructStartNewBidInterface() {
        this.guiMaker.constructLabel(this.background, 600, 220, 400, 30, "Item Name (Min 4 chars)", //7
                "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 600, 320, 200, 30, "Min bid amount",
                "Verdana", false, Font.ITALIC, 13);

        this.guiMaker.constructTextField(this.startBidItemName, this.background, 600, 250, 350, 60,
                "Verdana", Font.PLAIN, 30, false);

        this.guiMaker.constructTextField(this.startBidMinBidAmt, this.background, 600, 350, 350, 60,
                "Verdana", Font.PLAIN, 30, false);

        constructButton(new JButton("Add"), 650, 450, 250, 50, Font.BOLD,
                25, false);

        this.guiMaker.constructLabel(this.background, 600, 410, 400, 30,
                "Please enter an integer > 0", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 600, 410, 400, 30,
                "Name Not long enough", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 600, 410, 400, 30,
                "Successfully added", "Verdana", false, Font.ITALIC, 13);
    }

    private void constructEndBidInterface() {
        this.guiMaker.constructLabel(this.background, 600, 220, 400, 30, "Search by item name", //15
                "Verdana", false, Font.ITALIC, 13);

        this.guiMaker.constructTextField(this.endBidItemName, this.background, 600, 250, 350, 60,
                "Verdana", Font.PLAIN, 30, false);

        constructButton(new JButton("Auction off item"), 650, 350, 250, 50, Font.BOLD,
                25, false);

        this.guiMaker.constructLabel(this.background, 600, 310, 400, 30,
                "Item not found", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 600, 310, 400, 30,
                "Successfully auctioned off item", "Verdana", false, Font.ITALIC, 13);
    }

    private void constructItemListing() {

        // Code borrowed from Stack overflow


        constructHeaders();

        JPanel displayPanel = new JPanel(new GridLayout(1, 3));
        this.background.add(displayPanel);
        displayPanel.setVisible(true);
        displayPanel.setBounds(50, 250, 400, 280);

        ArrayList<String> itemName = new ArrayList<>();
        ArrayList<String> itemPrices = new ArrayList<>();
        ArrayList<String> customerWithHighestBid = new ArrayList<>();

        addItemsToList(itemName, itemPrices, customerWithHighestBid);

        JList<String> itemNameJ = new JList<>(itemName.toArray(new String[0]));
        JList<String> itemPricesJ = new JList<>(itemPrices.toArray(new String[0]));
        JList<String> customerWithHighestBidJ = new JList<>(customerWithHighestBid.toArray(new String[0]));

        itemNameJ.setFont(new Font("Verdana", Font.PLAIN, 12));
        itemPricesJ.setFont(new Font("Verdana", Font.PLAIN, 12));
        customerWithHighestBidJ.setFont(new Font("Verdana", Font.PLAIN, 12));

        formatScrollPane(itemNameJ, itemPricesJ, customerWithHighestBidJ, displayPanel);
    }

    private void constructHeaders() {
        this.guiMaker.constructLabel(this.background, 50, 230, 500, 20, "Item name",
                "Verdana", true, Font.BOLD, 10);
        this.guiMaker.constructLabel(this.background, 183, 230, 500, 20, "Min Bid price",
                "Verdana", true, Font.BOLD, 10);
        this.guiMaker.constructLabel(this.background, 317, 230, 500, 20, "Customer With Highest Bid",
                "Verdana", true, Font.BOLD, 10);

    }

    private void addItemsToList(ArrayList<String> itemName, ArrayList<String> itemPrices,
                                ArrayList<String> customerWithHighestBid) {

        ArrayList<Item> items = this.auctionMarket.getItemsUpForAuction();

        for (Item item: items) {
            itemName.add(item.getItemName());
            itemPrices.add(Integer.toString(item.getItemMinBiddingPrice()));
            customerWithHighestBid.add(item.getCustomerWithTheHighestBid().getUserName());
        }
    }

    private void formatScrollPane(JList<String> itemNameJ, JList<String> itemPricesJ,
                                  JList<String> customerWithHighestBidJ, JPanel displayPanel) {
        // Parts of code borrowed from stack overflow and chatGPT

        JScrollPane itemNameScroll = new JScrollPane();
        itemNameScroll.setViewportView(itemNameJ);
        JScrollPane itemPriceScroll = new JScrollPane();
        itemPriceScroll.setViewportView(itemPricesJ);
        JScrollPane customerWithHighestBidScroll = new JScrollPane();
        customerWithHighestBidScroll.setViewportView(customerWithHighestBidJ);

        displayPanel.add(new JScrollPane(itemNameJ));
        displayPanel.add(new JScrollPane(itemPricesJ));
        displayPanel.add(new JScrollPane(customerWithHighestBidJ));
    }

    private void constructButton(JButton button, int x, int y, int w, int h, int style, int fontSize,
                                 boolean visibility) {

        // setup borrowed from stackOverflow
        button.setFont(new Font("Verdana", style, fontSize));
        button.setBounds(x, y, w, h);
        button.setVisible(visibility);
        button.setBackground(new Color(164, 63, 252));
        button.setOpaque(true);
        button.addActionListener(this);
        this.background.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.background.getComponent(3)) {
            dispose();
            new AdminMainMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase);
        } else if (e.getSource() == this.background.getComponent(4)) {
            returnToChoices();
        } else if (e.getSource() == this.background.getComponent(5)) {
            enterStartBidMenu();
        } else if (e.getSource() == this.background.getComponent(6)) {
            enterEndBidMenu();
        } else if (e.getSource() == this.background.getComponent(11)) {
            verifyItem();
        } else {
            searchForItem();
        }
    }

    private void returnToChoices() {
        this.background.getComponent(4).setVisible(false);
        this.background.getComponent(7).setVisible(false);
        this.background.getComponent(8).setVisible(false);
        this.background.getComponent(9).setVisible(false);
        this.background.getComponent(10).setVisible(false);
        this.background.getComponent(11).setVisible(false);
        this.background.getComponent(12).setVisible(false);
        this.background.getComponent(13).setVisible(false);
        this.background.getComponent(14).setVisible(false);
        this.background.getComponent(15).setVisible(false);
        this.background.getComponent(16).setVisible(false);
        this.background.getComponent(17).setVisible(false);
        this.background.getComponent(18).setVisible(false);
        this.background.getComponent(19).setVisible(false);

        this.background.getComponent(5).setVisible(true);
        this.background.getComponent(6).setVisible(true);

        this.startBidItemName.setText("");
        this.startBidMinBidAmt.setText("");
    }

    private void enterStartBidMenu() {
        this.background.getComponent(5).setVisible(false);
        this.background.getComponent(6).setVisible(false);

        this.background.getComponent(4).setVisible(true);
        this.background.getComponent(7).setVisible(true);
        this.background.getComponent(8).setVisible(true);
        this.background.getComponent(9).setVisible(true);
        this.background.getComponent(10).setVisible(true);
        this.background.getComponent(11).setVisible(true);

        this.endBidItemName.setText("");
    }

    private void enterEndBidMenu() {
        this.background.getComponent(5).setVisible(false);
        this.background.getComponent(6).setVisible(false);

        this.background.getComponent(4).setVisible(true);
        this.background.getComponent(15).setVisible(true);
        this.background.getComponent(16).setVisible(true);
        this.background.getComponent(17).setVisible(true);

    }

    private void verifyItem() {
        String itemName = this.startBidItemName.getText();
        String itemMinBid = this.startBidMinBidAmt.getText();

        if (itemName.length() < 4) {
            displayBidNameTooShortError();
        } else {
            try {
                int itemMinBidAmt = Integer.parseInt(itemMinBid);
                if (itemMinBidAmt <= 0) {
                    displayBidPriceError();
                } else {
                    this.auctionMarket.getItemsUpForAuction().add(new Item(itemName, itemMinBidAmt, new
                            CustomerAccount("No One", "null", 0, new ArrayList<>())));
                    displayItemAddedSuccessMessage();
                }
            } catch (NumberFormatException e) {
                displayBidPriceError();
            }
        }
    }

    private void displayBidPriceError() {
        this.background.getComponent(12).setVisible(true);
        this.background.getComponent(12).setForeground(Color.red);
        this.background.getComponent(13).setVisible(false);
        this.background.getComponent(14).setVisible(false);
    }

    private void displayBidNameTooShortError() {
        this.background.getComponent(13).setVisible(true);
        this.background.getComponent(13).setForeground(Color.red);
        this.background.getComponent(12).setVisible(false);
        this.background.getComponent(14).setVisible(false);
    }

    private void displayItemAddedSuccessMessage() {
        this.background.getComponent(14).setVisible(true);
        this.background.getComponent(14).setForeground(Color.green);
        this.background.getComponent(12).setVisible(false);
        this.background.getComponent(13).setVisible(false);

        this.startBidMinBidAmt.setText("");
        this.startBidItemName.setText("");
    }

    private void searchForItem() {
        String itemToAuctionOff = this.endBidItemName.getText();
        Item itemToBeAuctionedOff = this.auctionMarket.searchForItem(itemToAuctionOff);

        if (itemToBeAuctionedOff == null) {
            displayItemNotFoundError();
        } else {
            displayItemAuctionedOffMessage();
            this.auctionMarket.getItemsUpForAuction().remove(itemToBeAuctionedOff);
            this.auctionMarket.auctionOffItem(itemToBeAuctionedOff);
        }
    }

    private void displayItemNotFoundError() {
        this.background.getComponent(18).setVisible(true);
        this.background.getComponent(18).setForeground(Color.red);
        this.background.getComponent(19).setVisible(false);
    }

    private void displayItemAuctionedOffMessage() {
        this.background.getComponent(19).setVisible(true);
        this.background.getComponent(19).setForeground(Color.green);
        this.background.getComponent(18).setVisible(false);

        this.endBidItemName.setText("");
    }

}
