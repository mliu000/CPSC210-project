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

public class AdminViewCustomerDatabaseGui extends JFrame implements ActionListener {

    private AdminAccount adminAccount;
    private AuctionMarket auctionMarket;
    private CustomerDatabase customerDatabase;
    private final CheckString passwordChecker;

    // Fields needed for GUI render
    private final ConstructGui guiMaker;
    private JPanel panel = new JPanel();
    private final ImageIcon backgroundImageIcon = new ImageIcon("./images/Screen Shot 2023-03-27 at 11.17.58 AM.png");
    private JLabel background = new JLabel(this.backgroundImageIcon);

    public AdminViewCustomerDatabaseGui(AdminAccount adminAccount, AuctionMarket auctionMarket,
                                  CustomerDatabase customerDatabase) {
        super("Auction Market");
        this.adminAccount = adminAccount;
        this.auctionMarket = auctionMarket;
        this.customerDatabase = customerDatabase;
        this.passwordChecker = new CheckString();
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructCustomerDatabasePanel();
        constructButton(new JButton("Go Back"));
    }

    private void constructCustomerDatabasePanel() {
        this.guiMaker.constructLabel(this.background, 150, 70, 700, 60,
                "Total number of customers: " + this.customerDatabase.getCustomerAccountDatabase().size(),
                "Verdana", true, Font.BOLD, 35);

        constructHeaders();

        JPanel displayPanel = new JPanel(new GridLayout(1, 3));
        this.background.add(displayPanel);
        displayPanel.setVisible(true);
        displayPanel.setBounds(150, 190, 700, 350);

        ArrayList<String> customerUsername = new ArrayList<>();
        ArrayList<String> customerPassword = new ArrayList<>();
        ArrayList<String> balance = new ArrayList<>();

        addCustomersToList(customerUsername, customerPassword, balance);

        JList<String> customerUsernameJ = new JList<>(customerUsername.toArray(new String[0]));
        JList<String> customerPasswordJ = new JList<>(customerPassword.toArray(new String[0]));
        JList<String> balanceJ = new JList<>(balance.toArray(new String[0]));

        customerUsernameJ.setFont(new Font("Verdana", Font.PLAIN, 12));
        customerPasswordJ.setFont(new Font("Verdana", Font.PLAIN, 12));
        balanceJ.setFont(new Font("Verdana", Font.PLAIN, 12));

        formatScrollPane(customerUsernameJ, customerPasswordJ, balanceJ, displayPanel);
    }

    private void addCustomersToList(ArrayList<String> customerUsername, ArrayList<String> customerPassword,
                                    ArrayList<String> balance) {

        for (CustomerAccount customerAccount: this.customerDatabase.getCustomerAccountDatabase()) {
            customerUsername.add(customerAccount.getUserName());
            customerPassword.add(customerAccount.getPassword());
            balance.add(Integer.toString(customerAccount.getBalance()));
        }
    }

    private void formatScrollPane(JList<String> customerUsernameJ, JList<String> customerPasswordJ,
                                  JList<String> balanceJ, JPanel displayPanel) {
        // Parts of code borrowed from stack overflow and chatGPT

        JScrollPane itemNameScroll = new JScrollPane();
        itemNameScroll.setViewportView(customerUsernameJ);
        JScrollPane itemPriceScroll = new JScrollPane();
        itemPriceScroll.setViewportView(customerPasswordJ);
        JScrollPane hasHighestBidScroll = new JScrollPane();
        hasHighestBidScroll.setViewportView(balanceJ);

        displayPanel.add(new JScrollPane(customerUsernameJ));
        displayPanel.add(new JScrollPane(customerPasswordJ));
        displayPanel.add(new JScrollPane(balanceJ));
    }

    private void constructHeaders() {
        this.guiMaker.constructLabel(this.background, 150, 170, 500, 20, "Customer Username",
                "Verdana", true, Font.BOLD, 10);
        this.guiMaker.constructLabel(this.background, 383, 170, 500, 20, "Customer Password",
                "Verdana", true, Font.BOLD, 10);
        this.guiMaker.constructLabel(this.background, 617, 170, 500, 20, "Balance",
                "Verdana", true, Font.BOLD, 10);

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
        new AdminMainMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase);
    }

}
