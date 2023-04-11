package ui;

import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AdminMainMenuGui extends JFrame implements ActionListener {

    // Databases with saved memory
    private AdminAccount adminAccount;
    private AuctionMarket auctionMarket;
    private CustomerDatabase customerDatabase;

    // Fields needed for GUI render
    private ConstructGui guiMaker;
    private JPanel panel = new JPanel();
    private final ImageIcon backgroundImageIcon = new ImageIcon("./images/Screen Shot 2023-03-27 at 11.17.58 AM.png");
    private JLabel background = new JLabel(this.backgroundImageIcon);

    public AdminMainMenuGui(AuctionMarket auctionMarket, AdminAccount adminAccount,
                            CustomerDatabase customerDatabase) {
        super("Auction Market");
        this.auctionMarket = auctionMarket;
        this.adminAccount = adminAccount;
        this.customerDatabase = customerDatabase;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructAdminMainMenuPanel();
    }

    private void constructAdminMainMenuPanel() {

        this.guiMaker.constructLabel(this.background, 200, 100, 800, 60, "ADMIN MAIN MENU",
                "Verdana", true, Font.BOLD, 50);

        constructButton(new JButton("Manage Auction Market"), 200, 220, 600, 60);
        constructButton(new JButton("View Customer database"), 200, 320, 600, 60);
        constructButton(new JButton("Change Password"), 200, 420, 600, 60);
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
        if (e.getSource() == this.background.getComponentAt(200, 220)) {
            dispose();
            new AdminManageAuctionMarketGui(this.adminAccount, this.auctionMarket, this.customerDatabase);
        } else if (e.getSource() == this.background.getComponentAt(200, 320)) {
            dispose();
            new AdminViewCustomerDatabaseGui(this.adminAccount, this.auctionMarket, this.customerDatabase);
        } else if (e.getSource() == this.background.getComponentAt(200, 420)) {
            dispose();
            new AdminChangePasswordGui(this.adminAccount, this.auctionMarket, this.customerDatabase);
        } else {
            dispose();
            new StartupInterfaceGui(this.customerDatabase, this.auctionMarket, this.adminAccount, false, false);
        }
    }

}
