package ui;

import model.AdminAccount;
import model.AuctionMarket;
import model.CustomerAccount;
import model.CustomerDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerViewItemsWonGui extends JFrame implements ActionListener {

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
    private JList<String> itemsWon;
    private JScrollPane displayOfItemsWon;

    public CustomerViewItemsWonGui(AuctionMarket auctionMarket, AdminAccount adminAccount,
                                   CustomerDatabase customerDatabase, CustomerAccount customerAccount) {
        super("Auction Market");
        this.auctionMarket = auctionMarket;
        this.adminAccount = adminAccount;
        this.customerDatabase = customerDatabase;
        this.loggedInCustomer = customerAccount;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructItemsWonGui();
    }

    private void constructItemsWonGui() {

        this.guiMaker.constructLabel(this.background, 5, 5, 500, 20,
                "Logged in as: " + this.loggedInCustomer.getUserName(), "Verdana", true,
                Font.PLAIN, 10);

        this.itemsWon = new JList<>(this.loggedInCustomer.getItemsWon().toArray(new String[0]));

        this.guiMaker.constructLabel(this.background, 250, 100, 600, 60,
                "Number of items won: " + this.loggedInCustomer.getItemsWon().size(),
                "Verdana", true, Font.BOLD, 40);

        this.displayOfItemsWon = new JScrollPane(itemsWon);
        this.displayOfItemsWon.setVisible(true);
        this.displayOfItemsWon.setBounds(150, 250, 700, 250);
        this.itemsWon.setFont(new Font("Verdana", Font.BOLD, 35));
        this.background.add(this.displayOfItemsWon);

        constructButton(new JButton("Go Back"));
    }

    private void constructButton(JButton button) {
        button.setFont(new Font("Verdana", Font.BOLD, 20));
        button.setBounds(50, 600, 200, 40);
        button.setVisible(true);
        button.setBackground(new Color(164, 63, 252));
        button.setOpaque(true);
        button.addActionListener(this);
        this.background.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        new CustomerMainMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase, loggedInCustomer);
    }
}
