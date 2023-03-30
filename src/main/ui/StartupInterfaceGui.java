package ui;

import model.AdminAccount;
import model.AuctionMarket;
import model.CustomerDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartupInterfaceGui extends JFrame implements ActionListener {

    private CustomerDatabase customerDatabase;
    private AuctionMarket auctionMarket;
    private AdminAccount adminAccount;

    private ConstructGui guiMaker;
    private JPanel panel = new JPanel();
    private ImageIcon backgroundImageIcon = new ImageIcon("./images/Screen Shot 2023-03-27 at 11.17.58 AM.png");
    private JLabel background = new JLabel(this.backgroundImageIcon);
    private JButton adminButton = new JButton("Administrator");
    private JButton customerButton = new JButton("Customer");
    private JButton saveAndExitButton = new JButton("Save");
    private JButton exitWithoutSavingButton = new JButton("Exit");
    private JLabel loadedMessage;
    private JLabel startFromScratchMessage;


    public StartupInterfaceGui(CustomerDatabase customerDatabase,
                               AuctionMarket auctionMarket, AdminAccount adminAccount, boolean one, boolean two) {
        super("Auction Market");

        this.customerDatabase = customerDatabase;
        this.auctionMarket = auctionMarket;
        this.adminAccount = adminAccount;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructPanel();
        constructMessages();

        this.loadedMessage.setVisible(one);
        this.startFromScratchMessage.setVisible(two);
    }

    private void constructMessages() {
        this.loadedMessage = new JLabel("Loaded Auction Market");
        this.loadedMessage.setFont(new Font("Verdana", Font.ITALIC, 13));
        this.loadedMessage.setBounds(30, 630, 400, 20);
        this.background.add(this.loadedMessage);

        this.startFromScratchMessage = new JLabel("Created new auction market");
        this.startFromScratchMessage.setFont(new Font("Verdana", Font.ITALIC, 13));
        this.startFromScratchMessage.setBounds(30, 630, 400, 20);
        this.background.add(this.startFromScratchMessage);

        this.guiMaker.constructLabel(this.background, 780, 630, 300, 20, "Saved Auction market",
                "Verdana", false, Font.ITALIC, 13);
    }

    private void constructPanel() {

        this.guiMaker.constructLabel(this.background, 95, 70, 900, 100,
                "Welcome to the Mu Ye's auction bidding market!",
                "Verdana", true, Font.BOLD, 30);

        constructButton(this.adminButton, 200);
        constructButton(this.customerButton, 300);
        constructButton(this.saveAndExitButton, 400);
        constructButton(this.exitWithoutSavingButton, 500);

    }

    private void constructButton(JButton button, int y) {

        // setup borrowed from stackOverflow
        button.setFont(new Font("Verdana", Font.BOLD, 20));
        button.setBounds(250, y, 500, 60);
        button.setVisible(true);
        button.setBackground(new Color(164, 63, 252));
        button.setOpaque(true);
        button.addActionListener(this);
        this.background.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.adminButton) {
            dispose();
            new AdminLoginMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase);
        } else if (e.getSource() == this.customerButton) {
            dispose();
            new CustomerChooseSignInTypeGui(this.auctionMarket, this.adminAccount, this.customerDatabase);
        } else if (e.getSource() == this.saveAndExitButton) {
            new SaveData(this.auctionMarket, this.adminAccount, this.customerDatabase);
            this.background.getComponent(7).setVisible(true);
        } else {
            System.exit(0);
        }
    }
}
