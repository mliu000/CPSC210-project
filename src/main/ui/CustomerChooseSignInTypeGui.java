package ui;

import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CustomerChooseSignInTypeGui extends JFrame implements ActionListener {

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

    public CustomerChooseSignInTypeGui(AuctionMarket auctionMarket, AdminAccount adminAccount,
                                       CustomerDatabase customerDatabase) {
        super("Auction Market");
        this.adminAccount = adminAccount;
        this.auctionMarket = auctionMarket;
        this.customerDatabase = customerDatabase;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructChooseSignInTypeGui();

    }

    private void constructChooseSignInTypeGui() {

        this.guiMaker.constructLabel(this.background, 130, 80, 900, 60, "LOGIN OR REGISTER",
                "Verdana", true, Font.BOLD, 60);

        constructButton(new JButton("Login"), 300, 200, 400, 100, 25);
        constructButton(new JButton("Register"), 300, 400, 400, 100, 25);
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
        if (e.getSource() == this.background.getComponentAt(300, 200)) {
            dispose();
            new CustomerLoginMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase);
        } else if (e.getSource() ==  this.background.getComponentAt(300, 400)) {
            dispose();
            new CustomerCreateAccountMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase);
        } else {
            dispose();
            new StartupInterfaceGui(this.customerDatabase, this.auctionMarket, this.adminAccount, false, false);
        }
    }

}
