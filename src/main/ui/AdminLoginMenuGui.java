package ui;

import model.AdminAccount;
import model.AuctionMarket;
import model.CustomerDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginMenuGui extends JFrame implements ActionListener {

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


    public AdminLoginMenuGui(AuctionMarket auctionMarket, AdminAccount adminAccount,
                             CustomerDatabase customerDatabase) {
        super("Auction Market");
        this.adminAccount = adminAccount;
        this.auctionMarket = auctionMarket;
        this.customerDatabase = customerDatabase;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructAdminLoginPromptPanel();
    }

    private void constructAdminLoginPromptPanel() {

        this.guiMaker.constructLabel(this.background, 425, 80, 300, 60, "LOGIN",
                "Verdana", true, Font.BOLD, 60);
        this.guiMaker.constructLabel(this.background, 260, 160, 200, 30, "Username",
                "Verdana", true, Font.ITALIC, 25);
        this.guiMaker.constructLabel(this.background, 260, 310, 200, 30, "Password",
                "Verdana", true, Font.ITALIC, 25);

        this.guiMaker.constructTextField(this.usernameInput, this.background, 250, 200, 500, 70,
                "Verdana", Font.PLAIN, 40, true);
        this.guiMaker.constructTextField(this.passwordInput, this.background, 250, 350, 500, 70,
                "Verdana", Font.PLAIN, 40, true);

        constructButton(new JButton("Login"), 300, 490, 400, 50, 25);
        constructButton(new JButton("Go Back"), 50, 600, 200, 40, 20);

        this.guiMaker.constructLabel(this.background, 260, 420, 400, 30,
                "Username and/or password incorrect. Please try again", "Verdana", false,
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
        String usernameInput = this.usernameInput.getText();
        String passwordInput = this.passwordInput.getText();

        if (e.getSource() == this.background.getComponentAt(300, 490)) {
            if (this.adminAccount.checkCredentials(usernameInput, passwordInput)) {
                dispose();
                new AdminMainMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase);
            } else {
                setIncorrectCredentialsMessage();
            }
        } else if (e.getSource() ==  this.background.getComponentAt(50, 600)) {
            dispose();
            new StartupInterfaceGui(this.customerDatabase, this.auctionMarket, this.adminAccount, false, false);
        }
    }

    private void setIncorrectCredentialsMessage() {
        this.background.getComponentAt(260, 420).setForeground(Color.red);
        this.background.getComponentAt(260, 420).setVisible(true);
    }

}
