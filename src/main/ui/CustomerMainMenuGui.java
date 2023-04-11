package ui;

import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CustomerMainMenuGui extends JFrame implements ActionListener {

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

    public CustomerMainMenuGui(AuctionMarket auctionMarket, AdminAccount adminAccount,
                            CustomerDatabase customerDatabase, CustomerAccount customerAccount) {
        super("Auction Market");
        this.auctionMarket = auctionMarket;
        this.adminAccount = adminAccount;
        this.customerDatabase = customerDatabase;
        this.loggedInCustomer = customerAccount;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructCustomerMainMenuPanel();
    }

    private void constructCustomerMainMenuPanel() {

        this.guiMaker.constructLabel(this.background, 150, 70, 800, 60, "CUSTOMER MAIN MENU", //0
                "Verdana", true, Font.BOLD, 50);
        this.guiMaker.constructLabel(this.background, 5, 5, 500, 20,
                "Logged in as: " + this.loggedInCustomer.getUserName(), "Verdana", true,
                Font.PLAIN, 10);

        constructButton(new JButton("Bid on item"), 200, 200, 600, 60);
        constructButton(new JButton("Deposit/Extract balance"), 200, 300, 600, 60);
        constructButton(new JButton("Change password"), 200, 400, 600, 60);
        constructButton(new JButton("View Items won"), 200, 500, 600, 60);
        constructButton(new JButton("Log Out"), 50, 600, 200, 40);
    }

    private void constructButton(JButton button, int x, int y, int w, int h) {

        // setup borrowed from stackOverflow
        button.setFont(new Font("Verdana", Font.BOLD, 20));
        button.setBounds(x, y, w, h);
        button.setVisible(true);
        button.setBackground(new Color(164, 63, 252));
        button.setOpaque(true);
        button.addActionListener(this);
        this.background.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.background.getComponent(2)) {
            dispose();
            new CustomerBidOnItemGui(this.auctionMarket, this.adminAccount, this.customerDatabase, loggedInCustomer);
        } else if (e.getSource() == this.background.getComponent(3)) {
            dispose();
            new CustomerDepositExtractGui(this.auctionMarket, this.adminAccount,
                    this.customerDatabase, this.loggedInCustomer);
        } else if (e.getSource() == this.background.getComponent(4)) {
            dispose();
            new CustomerChangePasswordGui(auctionMarket, adminAccount, customerDatabase, this.loggedInCustomer);
        } else if (e.getSource() == this.background.getComponent(5)) {
            dispose();
            new CustomerViewItemsWonGui(this.auctionMarket, this.adminAccount, this.customerDatabase, loggedInCustomer);
        } else {
            dispose();
            new StartupInterfaceGui(this.customerDatabase, this.auctionMarket, this.adminAccount, false, false);
        }
    }


}
