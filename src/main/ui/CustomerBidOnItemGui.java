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

public class CustomerBidOnItemGui extends JFrame implements ActionListener {

    // Databases with saved memory
    private AdminAccount adminAccount;
    private AuctionMarket auctionMarket;
    private CustomerDatabase customerDatabase;
    private CustomerAccount loggedInCustomer;

    // Fields needed for GUI render
    private ConstructGui guiMaker;
    private JPanel panel = new JPanel();
    private final ImageIcon backgroundImageIcon = new ImageIcon("./images/Screen Shot 2023-03-27 at 11.17.58 AM.png");
    private JLabel background = new JLabel(this.backgroundImageIcon);
    private JTextField itemSearch = new JTextField();
    private JTextField bidAmount = new JTextField();
    private JTable layout;
    private JScrollPane itemsUpForAuctionDisplay;

    public CustomerBidOnItemGui(AuctionMarket auctionMarket, AdminAccount adminAccount,
                                     CustomerDatabase customerDatabase, CustomerAccount customerAccount) {
        super("Auction Market");
        this.auctionMarket = auctionMarket;
        this.adminAccount = adminAccount;
        this.customerDatabase = customerDatabase;
        this.loggedInCustomer = customerAccount;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructBidOnItemGui();
        constructButtons();
        constructMessages();
        constructItemListing();

    }

    private void constructBidOnItemGui() {

        this.guiMaker.constructLabel(this.background, 5, 5, 500, 20,
                "Logged in as: " + this.loggedInCustomer.getUserName(), "Verdana", true,
                Font.PLAIN, 10);

        this.guiMaker.constructLabel(this.background, 430, 70, 500, 70, "BID",
                "Verdana", true, Font.BOLD, 80);

        this.guiMaker.constructLabel(this.background, 550, 170, 500, 60, "BID ON ITEM",
                "Verdana", true, Font.BOLD, 50);

        this.guiMaker.constructLabel(this.background, 550, 250, 400, 30, "Enter item name",
                "Verdana", true, Font.ITALIC, 13);

        this.guiMaker.constructLabel(this.background, 550, 350, 200, 30, "Enter bid amount",
                "Verdana", true, Font.ITALIC, 13);

        this.guiMaker.constructTextField(this.itemSearch, this.background, 550, 280, 350, 60,
                "Verdana", Font.PLAIN, 30, true);

        this.guiMaker.constructTextField(this.bidAmount, this.background, 550, 380, 350, 60,
                "Verdana", Font.PLAIN, 30, true);
    }

    private void constructButtons() {
        constructButton(new JButton("Go Back"), 50, 600, 200, 40, 20);
        constructButton(new JButton("Place Bid"), 600, 500, 250, 50, 25);
    }

    private void constructMessages() {

        this.guiMaker.constructLabel(this.background, 550, 440, 400, 30, //9
                "Item with given name not found", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 550, 440, 400, 30,
                "Please enter an integer bid > 0", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 550, 440, 400, 30,
                "Can not bid for more than balance", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 550, 440, 400, 30,
                "Must bid for more than item min bid amount",
                "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 550, 440, 400, 30,
                "Successfully bidded on item", "Verdana", false, Font.ITALIC, 13);


    }

    private void constructItemListing() {

        // Code borrowed from Stack overflow

        this.guiMaker.constructLabel(this.background, 50, 170, 500, 60, "ITEMS ON MARKET",
                "Verdana", true, Font.BOLD, 35);

        constructHeaders();

        JPanel displayPanel = new JPanel(new GridLayout(1, 3));
        this.background.add(displayPanel);
        displayPanel.setVisible(true);
        displayPanel.setBounds(50, 270, 400, 280);

        ArrayList<String> itemName = new ArrayList<>();
        ArrayList<String> itemPrices = new ArrayList<>();
        ArrayList<String> hasHighestBid = new ArrayList<>();

        addItemsToList(itemName, itemPrices, hasHighestBid);

        JList<String> itemNameJ = new JList<>(itemName.toArray(new String[0]));
        JList<String> itemPricesJ = new JList<>(itemPrices.toArray(new String[0]));
        JList<String> hasHighestBidJ = new JList<>(hasHighestBid.toArray(new String[0]));

        itemNameJ.setFont(new Font("Verdana", Font.PLAIN, 12));
        itemPricesJ.setFont(new Font("Verdana", Font.PLAIN, 12));
        hasHighestBidJ.setFont(new Font("Verdana", Font.PLAIN, 12));

        formatScrollPane(itemNameJ, itemPricesJ, hasHighestBidJ, displayPanel);
    }

    private void constructHeaders() {
        this.guiMaker.constructLabel(this.background, 50, 250, 500, 20, "Item name",
                "Verdana", true, Font.BOLD, 10);
        this.guiMaker.constructLabel(this.background, 183, 250, 500, 20, "Min Bid price",
                "Verdana", true, Font.BOLD, 10);
        this.guiMaker.constructLabel(this.background, 317, 250, 500, 20, "You have highest bid?",
                "Verdana", true, Font.BOLD, 10);

    }

    private void addItemsToList(ArrayList<String> itemName, ArrayList<String> itemPrices,
                                ArrayList<String> hasHighestBid) {

        ArrayList<Item> items = this.auctionMarket.getItemsUpForAuction();

        for (Item item: items) {
            itemName.add(item.getItemName());
            itemPrices.add(Integer.toString(item.getItemMinBiddingPrice()));
            if (this.loggedInCustomer.equals(item.getCustomerWithTheHighestBid())) {
                hasHighestBid.add("Yes");
            } else {
                hasHighestBid.add("No");
            }
        }
    }

    private void formatScrollPane(JList<String> itemNameJ, JList<String> itemPricesJ, JList<String> hasHighestBidJ,
                                  JPanel displayPanel) {
        // Parts of code borrowed from stack overflow and chatGPT

        JScrollPane itemNameScroll = new JScrollPane();
        itemNameScroll.setViewportView(itemNameJ);
        JScrollPane itemPriceScroll = new JScrollPane();
        itemPriceScroll.setViewportView(itemPricesJ);
        JScrollPane hasHighestBidScroll = new JScrollPane();
        hasHighestBidScroll.setViewportView(hasHighestBidJ);

        displayPanel.add(new JScrollPane(itemNameJ));
        displayPanel.add(new JScrollPane(itemPricesJ));
        displayPanel.add(new JScrollPane(hasHighestBidJ));
    }

    private void constructButton(JButton button, int x, int y, int w, int h, int fontSize) {
        button.setFont(new Font("Verdana", Font.BOLD, fontSize));
        button.setBounds(x, y, w, h);
        button.setVisible(true);
        button.setBackground(new Color(164, 63, 252));
        button.setOpaque(true);
        button.addActionListener(this);
        this.background.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.background.getComponent(7)) {
            dispose();
            new CustomerMainMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase, loggedInCustomer);
        } else if (e.getSource() == this.background.getComponent(8)) {
            checkIfItemFound();
        }
    }

    private void checkIfItemFound() {
        String searchName = this.itemSearch.getText();
        String bidAmount = this.bidAmount.getText();

        Item itemToBidOn = this.auctionMarket.searchForItem(searchName);

        if (itemToBidOn == null) {
            displayItemNotFound();
        } else {
            checkBidValidity(bidAmount, itemToBidOn);
        }
    }

    private void checkBidValidity(String bidAmount, Item item) {
        try {
            int bidAmt = Integer.parseInt(bidAmount);
            if (bidAmt <= 0) {
                displayBidInputError();
            } else if (bidAmt > this.loggedInCustomer.getBalance()) {
                displayBidForMoreThanBalanceError();
            } else if (bidAmt <= item.getItemMinBiddingPrice()) {
                displayBidForLessThanMinBidError();
            } else {
                displayBidSuccessMessage();
                item.updateBidStatus(this.loggedInCustomer, bidAmt);
            }
        } catch (NumberFormatException e) {
            displayBidInputError();
        }
    }

    private void displayItemNotFound() {
        this.background.getComponent(9).setVisible(true);
        this.background.getComponent(9).setForeground(Color.red);
        this.background.getComponent(10).setVisible(false);
        this.background.getComponent(11).setVisible(false);
        this.background.getComponent(12).setVisible(false);
        this.background.getComponent(13).setVisible(false);
    }

    private void displayBidInputError() {
        this.background.getComponent(10).setVisible(true);
        this.background.getComponent(10).setForeground(Color.red);
        this.background.getComponent(9).setVisible(false);
        this.background.getComponent(11).setVisible(false);
        this.background.getComponent(12).setVisible(false);
        this.background.getComponent(13).setVisible(false);
    }

    private void displayBidForMoreThanBalanceError() {
        this.background.getComponent(11).setVisible(true);
        this.background.getComponent(11).setForeground(Color.red);
        this.background.getComponent(9).setVisible(false);
        this.background.getComponent(10).setVisible(false);
        this.background.getComponent(12).setVisible(false);
        this.background.getComponent(13).setVisible(false);
    }

    private void displayBidForLessThanMinBidError() {
        this.background.getComponent(12).setVisible(true);
        this.background.getComponent(12).setForeground(Color.red);
        this.background.getComponent(9).setVisible(false);
        this.background.getComponent(10).setVisible(false);
        this.background.getComponent(11).setVisible(false);
        this.background.getComponent(13).setVisible(false);
    }

    private void displayBidSuccessMessage() {
        this.background.getComponent(13).setVisible(true);
        this.background.getComponent(13).setForeground(Color.green);
        this.background.getComponent(9).setVisible(false);
        this.background.getComponent(10).setVisible(false);
        this.background.getComponent(11).setVisible(false);
        this.background.getComponent(12).setVisible(false);

        this.bidAmount.setText("");
        this.itemSearch.setText("");
    }

}
