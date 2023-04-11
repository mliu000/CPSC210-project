package ui;

import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AdminChangePasswordGui extends JFrame implements ActionListener {

    // Databases with saved memory
    private AdminAccount adminAccount;
    private AuctionMarket auctionMarket;
    private CustomerDatabase customerDatabase;
    private final CheckString passwordChecker;

    // Fields needed for GUI render
    private final ConstructGui guiMaker;
    private JPanel panel = new JPanel();
    private final ImageIcon backgroundImageIcon = new ImageIcon("./images/Screen Shot 2023-03-27 at 11.17.58 AM.png");
    private JLabel background = new JLabel(this.backgroundImageIcon);
    private JTextField newPassword = new JTextField();
    private JTextField confirmPassword = new JTextField();


    public AdminChangePasswordGui(AdminAccount adminAccount, AuctionMarket auctionMarket,
                                  CustomerDatabase customerDatabase) {
        super("Auction Market");
        this.adminAccount = adminAccount;
        this.auctionMarket = auctionMarket;
        this.customerDatabase = customerDatabase;
        this.passwordChecker = new CheckString();
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructChangePasswordGui();
        constructMessages();
        constructButtons();

    }

    private void constructChangePasswordGui() {

        this.guiMaker.constructLabel(this.background, 200, 60, 700, 60, "CHANGE PASSWORD",
                "Verdana", true, Font.BOLD, 50);
        this.guiMaker.constructLabel(this.background, 250, 140, 800, 60,
                "Please enter password at least 10 characters long, contains at least one uppercase",
                "Verdana", true, Font.ITALIC, 15);
        this.guiMaker.constructLabel(this.background, 250, 160, 700, 60,
                "character, one lowercase character, one digit, and no spaces.",
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
                "Password does not meet requirements or do not match. Please try again", "Verdana", false,
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
            if (checkPasswordMeetRequirements()) {
                this.adminAccount.changePassword(this.newPassword.getText());
                displayPasswordChangedLabel();
            } else {
                displayPasswordInvalidLabel();
            }
        } else {
            dispose();
            new AdminMainMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase);
        }
    }

    private void displayPasswordInvalidLabel() {
        this.background.getComponent(6).setForeground(Color.red);
        this.background.getComponent(6).setVisible(true);
        this.background.getComponent(7).setVisible(false);
    }

    private void displayPasswordChangedLabel() {
        this.background.getComponent(7).setForeground(Color.green);
        this.background.getComponent(7).setVisible(true);
        this.background.getComponent(6).setVisible(false);

        this.newPassword.setText("");
        this.confirmPassword.setText("");
    }

    private boolean checkPasswordMeetRequirements() {
        return (this.passwordChecker.containsDigits(this.newPassword.getText())
                && this.passwordChecker.containsUpperCaseLetters(this.newPassword.getText())
                && this.passwordChecker.containsLowerCaseLetters(this.newPassword.getText())
                && this.newPassword.getText().equals(this.confirmPassword.getText())
                && this.newPassword.getText().length() >= 10);
    }
}
