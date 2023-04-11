package ui;

import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CustomerChangePasswordGui extends JFrame implements ActionListener {

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
    private JTextField newPassword = new JTextField();
    private JTextField confirmPassword = new JTextField();

    public CustomerChangePasswordGui(AuctionMarket auctionMarket, AdminAccount adminAccount,
                               CustomerDatabase customerDatabase, CustomerAccount customerAccount) {
        super("Auction Market");
        this.auctionMarket = auctionMarket;
        this.adminAccount = adminAccount;
        this.customerDatabase = customerDatabase;
        this.loggedInCustomer = customerAccount;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructCustomerChangePasswordPanel();
        constructMessages();
        constructButtons();

    }

    private void constructCustomerChangePasswordPanel() {

        this.guiMaker.constructLabel(this.background, 5, 5, 500, 20,
                "Logged in as: " + this.loggedInCustomer.getUserName(), "Verdana", true,
                Font.PLAIN, 10);

        this.guiMaker.constructLabel(this.background, 200, 60, 700, 60, "CHANGE PASSWORD",
                "Verdana", true, Font.BOLD, 50);
        this.guiMaker.constructLabel(this.background, 250, 160, 700, 60,
                "Enter new password at least 4 characters long",
                "Verdana", true, Font.ITALIC, 15);
        this.guiMaker.constructLabel(this.background, 250, 310, 700, 60,
                "Confirm Password", "Verdana", true, Font.ITALIC, 15);

        this.guiMaker.constructTextField(this.newPassword, this.background, 250, 200, 500, 70,
                "Verdana", Font.PLAIN, 40, true);
        this.guiMaker.constructTextField(this.confirmPassword, this.background, 250, 350, 500, 70,
                "Verdana", Font.PLAIN, 40, true);

    }

    private void constructMessages() {
        this.guiMaker.constructLabel(this.background, 260, 420, 600, 30,
                "Password too short. Please try again", "Verdana", false,
                Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 260, 420, 600, 30,
                "Passwords do not match. Please try again", "Verdana", false,
                Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 260, 420, 400, 30,
                "Password Changed. Success!", "Verdana", false,
                Font.ITALIC, 13);
    }

    private void constructButtons() {

        constructButton(new JButton("Change Password"), 300, 490, 400, 50, 25);
        constructButton(new JButton("Go Back"), 50, 600, 200, 40, 20);
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
        if (e.getSource() == this.background.getComponentAt(300, 490)) {
            checkPassword();
        } else {
            dispose();
            new CustomerMainMenuGui(this.auctionMarket, this.adminAccount,
                    this.customerDatabase, this.loggedInCustomer);
        }
    }

    private void checkPassword() {
        String newPassword = this.newPassword.getText();
        String confirmPassword = this.confirmPassword.getText();

        if (newPassword.length() < 4) {
            displayPasswordTooShortError();
        } else if (!newPassword.equals(confirmPassword)) {
            displayPasswordsDoNotMatchError();
        } else {
            this.loggedInCustomer.changePassword(newPassword);
            displaySuccessfullyChangedMessage();
        }
    }

    private void displayPasswordTooShortError() {
        this.background.getComponent(6).setVisible(true);
        this.background.getComponent(6).setForeground(Color.red);
        this.background.getComponent(7).setVisible(false);
        this.background.getComponent(8).setVisible(false);
    }

    private void displayPasswordsDoNotMatchError() {
        this.background.getComponent(7).setVisible(true);
        this.background.getComponent(7).setForeground(Color.red);
        this.background.getComponent(6).setVisible(false);
        this.background.getComponent(8).setVisible(false);
    }

    private void displaySuccessfullyChangedMessage() {
        this.background.getComponent(8).setVisible(true);
        this.background.getComponent(8).setForeground(Color.green);
        this.background.getComponent(6).setVisible(false);
        this.background.getComponent(7).setVisible(false);

        this.newPassword.setText("");
        this.confirmPassword.setText("");
    }
}
