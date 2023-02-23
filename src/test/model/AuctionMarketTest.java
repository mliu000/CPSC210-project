package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AuctionMarketTest {

    private AuctionMarket auctionMarketTest;
    private Item item1;
    private Item item2;
    private Item item3;
    private CustomerAccount customerAccount1;
    private CustomerAccount customerAccount2;

    @BeforeEach
    // Creates a new arraylist, adds items to array list.
    public void runBefore() {

        this.auctionMarketTest = new AuctionMarket();

        // Creates sample items to add to auction market
        this.item1 = new Item("Tanjiro figure", 100);
        this.item2 = new Item("Nezuko figure", 100);
        this.item3 = new Item("Shinobu figure", 120);

        // Creates sample customers
        this.customerAccount1 = new CustomerAccount("AnimeLover", "1234");
        this.customerAccount2 = new CustomerAccount("AnimeObsessed", "1234");

        // Updates bid status of some items
        this.item1.updateBidStatus(this.customerAccount1, 120);
        this.item2.updateBidStatus(this.customerAccount2, 150);

        // Adds items to auction market
        this.auctionMarketTest.getItemsUpForAuction().add(item1);
        this.auctionMarketTest.getItemsUpForAuction().add(item2);
        this.auctionMarketTest.getItemsUpForAuction().add(item3);
    }

    @Test
    // Tests the constructor
    public void constructorTest() {
        // Initializes a new auction market just for this test
        AuctionMarket auctionMarketTest2 = new AuctionMarket();

        // Checks to make sure the newly instantiated auction market is an empty list
        assertTrue(auctionMarketTest2.getItemsUpForAuction().isEmpty());
    }

    @Test
    // Tests to see whether this search method returns the correct item if found, null otherwise
    public void searchForItemTest() {
        // Item is found
        assertEquals(this.item1, this.auctionMarketTest.searchForItem("Tanjiro figure"));

        // Item is not found (should return null)
        assertNull(this.auctionMarketTest.searchForItem("Zenitsu figure"));
    }

    @Test
    /* Tests to make sure what method removes item from auction market, changes item auction status to false,
    and adds item to customer's inventory
     */
    public void auctionOffItemTest() {
        this.auctionMarketTest.auctionOffItem(this.item1);
        // customer11 should now have item1, status of item1 should be false

        // Checks to make sure the item not in the auction market list anymore
        assertNull(this.auctionMarketTest.searchForItem("Tanjiro figure"));

        // Checks to make sure the status of item 1 is false.
        assertFalse(this.item1.isUpForAuction());

        // Checks to make sure the list has the remaining items
        ArrayList<Item> remainingItems = new ArrayList<>();
        remainingItems.add(item2);
        remainingItems.add(item3);
        assertEquals(remainingItems, this.auctionMarketTest.getItemsUpForAuction());

        // Checks to make sure customer1 has the item in itemsWon list
        assertTrue(this.customerAccount1.getItemsWon().contains(item1));
    }
}
