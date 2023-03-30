package ui;

import model.AdminAccount;
import model.AuctionMarket;
import model.CustomerAccount;
import model.CustomerDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerCreateAccountMenuGui extends JFrame implements ActionListener {

    // Fields with data storage
    private AdminAccount adminAccount;
    private AuctionMarket auctionMarket;
    private CustomerDatabase customerDatabase;

    // Fields needed for GUI render
    private final ConstructGui guiMaker;
    private JPanel panel = new JPanel();
    private final ImageIcon backgroundImageIcon = new ImageIcon("./images/Screen Shot 2023-03-27 at 11.17.58 AM.png");
    private JLabel background = new JLabel(this.backgroundImageIcon);
    private JTextField usernameInput = new JTextField();
    private JTextField passwordInput = new JTextField();

    public CustomerCreateAccountMenuGui(AuctionMarket auctionMarket, AdminAccount adminAccount,
                                CustomerDatabase customerDatabase) {
        super("Auction Market");
        this.adminAccount = adminAccount;
        this.auctionMarket = auctionMarket;
        this.customerDatabase = customerDatabase;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructCustomerCreateAccountPromptPanel();

    }

    private void constructCustomerCreateAccountPromptPanel() {

        this.guiMaker.constructLabel(this.background, 150, 80, 800, 60, "CREATE ACCOUNT",
                "Verdana", true, Font.BOLD, 60);
        this.guiMaker.constructLabel(this.background, 260, 160, 600, 30,
                "Please Choose a username at least 4 characters long:",
                "Verdana", true, Font.ITALIC, 15);
        this.guiMaker.constructLabel(this.background, 260, 310, 600, 30, "Please choose a password",
                "Verdana", true, Font.ITALIC, 15);

        this.guiMaker.constructTextField(this.usernameInput, this.background, 250, 200, 500, 70,
                "Verdana", Font.PLAIN, 40, true);
        this.guiMaker.constructTextField(this.passwordInput, this.background, 250, 350, 500, 70,
                "Verdana", Font.PLAIN, 40, true);

        constructButton(new JButton("Create Account"), 300, 490, 400, 50, 25);
        constructButton(new JButton("Go Back"), 50, 600, 200, 40, 20);

        this.guiMaker.constructLabel(this.background, 260, 420, 600, 30,
                "Account with username exists already. Please try again", "Verdana", false,
                Font.ITALIC, 13);

        this.guiMaker.constructLabel(this.background, 260, 420, 400, 30,
                "Username is not long enough. Please try again.", "Verdana", false,
                Font.ITALIC, 13);

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
        String username = this.usernameInput.getText();
        String password = this.passwordInput.getText();

        if (e.getSource().equals(this.background.getComponentAt(300, 490))) {
            if (!checkUsername(username)) {
                displayInvalidMessage();
            } else if (this.customerDatabase.checkUsernameDuplicate(username)) {
                displayUsernameDuplicateMessage();
            } else {
                CustomerAccount newCustomer = new CustomerAccount(username, password, 0, new ArrayList<>());
                this.customerDatabase.getCustomerAccountDatabase().add(newCustomer);
                dispose();
                new CustomerMainMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase,
                        newCustomer);
            }
        } else {
            dispose();
            new CustomerChooseSignInTypeGui(this.auctionMarket, this.adminAccount, this.customerDatabase);
        }
    }

    private boolean checkUsername(String username) {
        return username.length() >= 4;
    }

    private void displayUsernameDuplicateMessage() {
        this.background.getComponent(7).setForeground(Color.red);
        this.background.getComponent(7).setVisible(true);
        this.background.getComponent(8).setVisible(false);
    }

    private void displayInvalidMessage() {
        this.background.getComponent(8).setForeground(Color.red);
        this.background.getComponent(8).setVisible(true);
        this.background.getComponent(7).setVisible(false);
    }
}
