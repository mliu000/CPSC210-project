package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CustomerAccountTest {

    private CustomerAccount customer;
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    private Item item5;
    private Item item6;

    @BeforeEach
    public void runBefore() {
        customer = new CustomerAccount("AnimeLover", "1234");
        this.item1 = new Item("Tanjiro figure", 100);
        this.item2 = new Item("Nezuko figure", 100);
        this.item3 = new Item("Shinobu figure", 120);
        this.item4 = new Item("Kanao figure", 120);
        this.item5 = new Item("Zenitsu figure", 120);
        this.item6 = new Item("Muzan figure", 120);
    }

    @Test
    // Tests the constructor
    public void constructorTest() {
        assertEquals("AnimeLover", this.customer.getUserName());
        assertEquals("1234", this.customer.getPassword());
        assertEquals(0, this.customer.getBalance());
        assertTrue(this.customer.getItemsWon().isEmpty());
        assertTrue(this.customer.getRecentlyBiddedItemsLog().isEmpty());
    }

    @Test
    // Tests the change password method to make sure it changes the password
    public void changePasswordTest() {
        // Changes password to "4321"
        this.customer.changePassword("4321");
        assertEquals("4321", this.customer.getPassword());
    }

    @Test
    // Tests the deposit into balance method to make sure it deposits the balance
    public void depositIntoBalanceTest() {
        // Deposit $1000
        this.customer.depositIntoBalance(1000);
        assertEquals(1000, this.customer.getBalance());
    }

    @Test
    // Tests the deposit into balance method to make sure it extracts the balance
    public void extractFromBalanceTest() {
        // Deposit $1000, then extract $200.
        this.customer.depositIntoBalance(1000);
        this.customer.extractFromBalance(200);

        // Should produce 800
        assertEquals(800, this.customer.getBalance());
    }

    @Test
    // Tests the complicated method "addItemToRecentlyBidded" to make sure it functions properly
    public void addItemToRecentlyBiddedTestCase1() {
        // Case 1, adds item without duplicate and has is not full
        this.item1.updateBidStatus(this.customer, 300);
        this.customer.addItemToRecentlyBidded(item1);

        ArrayList<Item> expectedResult = new ArrayList<>();
        expectedResult.add(item1);

        assertEquals(expectedResult, this.customer.getRecentlyBiddedItemsLog());
    }

    @Test
    public void addItemToRecentlyBiddedTestCase2() {
        // Case 2, adds item to full list
        this.customer.addItemToRecentlyBidded(item1);
        this.customer.addItemToRecentlyBidded(item2);
        this.customer.addItemToRecentlyBidded(item3);
        this.customer.addItemToRecentlyBidded(item4);
        this.customer.addItemToRecentlyBidded(item5);
        this.customer.addItemToRecentlyBidded(item6);

        ArrayList<Item> expectedResult = new ArrayList<>();
        expectedResult.add(item2);
        expectedResult.add(item3);
        expectedResult.add(item4);
        expectedResult.add(item5);
        expectedResult.add(item6);

        // List should not have item1
        assertEquals(expectedResult, this.customer.getRecentlyBiddedItemsLog());
    }

    @Test
    public void addItemToRecentlyBiddedTestCase3() {
        // Case 3, there is a duplicate, when duplicate is found, it should keep the last instance.
        this.customer.addItemToRecentlyBidded(item1);
        this.customer.addItemToRecentlyBidded(item2);
        this.customer.addItemToRecentlyBidded(item3);
        this.customer.addItemToRecentlyBidded(item4);
        this.customer.addItemToRecentlyBidded(item5);
        this.customer.addItemToRecentlyBidded(item2);

        // First instance of the duplicate item2 is removed
        ArrayList<Item> expectedResult = new ArrayList<>();
        expectedResult.add(item1);
        expectedResult.add(item3);
        expectedResult.add(item4);
        expectedResult.add(item5);
        expectedResult.add(item2);

        assertEquals(expectedResult, this.customer.getRecentlyBiddedItemsLog());
    }
}
