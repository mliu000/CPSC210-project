package ui;

import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CustomerDepositExtractGui extends JFrame implements ActionListener {

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
    private JLabel balanceDisplay;
    private JTextField depositAmt = new JTextField();
    private JTextField extractAmt = new JTextField();

    public CustomerDepositExtractGui(AuctionMarket auctionMarket, AdminAccount adminAccount,
                               CustomerDatabase customerDatabase, CustomerAccount customerAccount) {
        super("Auction Market");
        this.auctionMarket = auctionMarket;
        this.adminAccount = adminAccount;
        this.customerDatabase = customerDatabase;
        this.loggedInCustomer = customerAccount;
        this.guiMaker = new ConstructGui(this, this.background, this.panel);

        constructCustomerDepositExtractGui();
        constructMessages();
        constructBalanceDisplay();
    }

    private void constructCustomerDepositExtractGui() {

        this.guiMaker.constructLabel(this.background, 5, 5, 500, 20,
                "Logged in as: " + this.loggedInCustomer.getUserName(), "Verdana", true,
                Font.PLAIN, 10);
        this.guiMaker.constructLabel(this.background, 150, 200, 300, 60, "DEPOSIT",
                "Verdana", true, Font.BOLD, 50);
        this.guiMaker.constructLabel(this.background, 600, 200, 300, 60, "EXTRACT",
                "Verdana", true, Font.BOLD, 50);

        this.guiMaker.constructTextField(this.depositAmt, this.background, 120, 330, 300, 70,
                "Verdana", Font.PLAIN, 40, true);
        this.guiMaker.constructTextField(this.extractAmt, this.background, 600, 330, 300, 70,
                "Verdana", Font.PLAIN, 40, true);

        this.guiMaker.constructLabel(this.background, 120, 300, 400, 40, "Enter deposit amount",
                "Verdana", true, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 600, 300, 400, 40, "Enter extract amount",
                "Verdana", true, Font.ITALIC, 13);

        constructButton(new JButton("Go Back"), 50, 600, 200, 40, 20);
        constructButton(new JButton("Deposit"), 160, 450, 200, 50, 25);
        constructButton(new JButton("Extract"), 640, 450, 200, 50, 25);
    }

    private void constructMessages() {

        this.guiMaker.constructLabel(this.background, 120, 400, 500, 20, // 10
                "Can not deposit negative amount", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 120, 400, 500, 20,
                "Unable to process transaction", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 120, 400, 500, 20,
                "Transaction successful", "Verdana", false, Font.ITALIC, 13);

        this.guiMaker.constructLabel(this.background, 600, 400, 500, 20,
                "Cannot extract more than balance", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 600, 400, 500, 20,
                "Can not extract negative amount", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 600, 400, 500, 20,
                "Unable to process transaction", "Verdana", false, Font.ITALIC, 13);
        this.guiMaker.constructLabel(this.background, 600, 400, 500, 20,
                "Transaction successful", "Verdana", false, Font.ITALIC, 13);
    }

    private void constructBalanceDisplay() {
        this.balanceDisplay = new JLabel("Your current balance is: $" + this.loggedInCustomer.getBalance());
        this.balanceDisplay.setBounds(200, 80, 800, 60);
        this.balanceDisplay.setVisible(true);
        this.balanceDisplay.setFont(new Font("Verdana", Font.ITALIC, 40));
        this.background.add(this.balanceDisplay);
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
        if (e.getSource() == this.background.getComponentAt(160, 450)) {
            processDeposit();
        } else if (e.getSource() == this.background.getComponentAt(640, 450)) {
            processExtract();
        } else {
            dispose();
            new CustomerMainMenuGui(this.auctionMarket, this.adminAccount, this.customerDatabase,
                    this.loggedInCustomer);
        }
    }

    private void processDeposit() {
        String deposit = this.depositAmt.getText();

        try {
            int depositAmt = Integer.parseInt(deposit);
            if (depositAmt <= 0) {
                displayDepositNegativeAmountError();
            } else {
                this.loggedInCustomer.depositIntoBalance(depositAmt);
                displayDepositSuccessMessage();
            }
        } catch (NumberFormatException e) {
            displayDepositInputError();
        }
    }

    private void displayDepositNegativeAmountError() {
        this.background.getComponent(10).setVisible(true);
        this.background.getComponent(10).setForeground(Color.red);
        this.background.getComponent(11).setVisible(false);
        this.background.getComponent(12).setVisible(false);
    }

    private void displayDepositInputError() {
        this.background.getComponent(11).setVisible(true);
        this.background.getComponent(11).setForeground(Color.red);
        this.background.getComponent(10).setVisible(false);
        this.background.getComponent(12).setVisible(false);
    }

    private void displayDepositSuccessMessage() {
        this.background.getComponent(12).setVisible(true);
        this.background.getComponent(12).setForeground(Color.green);
        this.background.getComponent(10).setVisible(false);
        this.background.getComponent(11).setVisible(false);

        this.depositAmt.setText("");

        this.balanceDisplay.setText("Your current balance is: $" + this.loggedInCustomer.getBalance());
    }


    private void processExtract() {
        String extract = this.extractAmt.getText();

        try {
            int extractAmt = Integer.parseInt(extract);
            if (extractAmt <= 0) {
                displayExtractNegativeAmountError();
            } else if (extractAmt > this.loggedInCustomer.getBalance()) {
                displayExtractTooMuchMoneyError();
            } else {
                this.loggedInCustomer.extractFromBalance(extractAmt);
                displayExtractSuccessMessage();
            }
        } catch (NumberFormatException e) {
            displayExtractInputError();
        }
    }

    private void displayExtractNegativeAmountError() {
        this.background.getComponent(14).setVisible(true);
        this.background.getComponent(14).setForeground(Color.red);
        this.background.getComponent(13).setVisible(false);
        this.background.getComponent(15).setVisible(false);
        this.background.getComponent(16).setVisible(false);
    }

    private void displayExtractTooMuchMoneyError() {
        this.background.getComponent(13).setVisible(true);
        this.background.getComponent(13).setForeground(Color.red);
        this.background.getComponent(14).setVisible(false);
        this.background.getComponent(15).setVisible(false);
        this.background.getComponent(16).setVisible(false);
    }

    private void displayExtractInputError() {
        this.background.getComponent(15).setVisible(true);
        this.background.getComponent(15).setForeground(Color.red);
        this.background.getComponent(13).setVisible(false);
        this.background.getComponent(14).setVisible(false);
        this.background.getComponent(16).setVisible(false);
    }

    private void displayExtractSuccessMessage() {
        this.background.getComponent(16).setVisible(true);
        this.background.getComponent(16).setForeground(Color.green);
        this.background.getComponent(13).setVisible(false);
        this.background.getComponent(14).setVisible(false);
        this.background.getComponent(15).setVisible(false);

        this.depositAmt.setText("");

        this.balanceDisplay.setText("Your current balance is: $" + this.loggedInCustomer.getBalance());
    }
}
