package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private Item item;
    private CustomerAccount customerAccount1;
    private CustomerAccount customerAccount2;


    @BeforeEach
    public void runBefore() {
        // Instantiates the items and test customers
        this.item = new Item("Tanjiro figure", 100, new CustomerAccount("Dummy account", "Notinuse", 0, new ArrayList<>()));
        this.customerAccount1 = new CustomerAccount("AnimeLover", "1234", 0, new ArrayList<>());
        this.customerAccount2 = new CustomerAccount("AnimeObsessed", "1234", 0, new ArrayList<>());

        this.customerAccount1.depositIntoBalance(1000);
        this.customerAccount2.depositIntoBalance(1000);
    }

    @Test
    // Tests the constructor
    public void constructorTest() {
        assertEquals("Tanjiro figure", this.item.getItemName());
        assertEquals(100, this.item.getItemMinBiddingPrice());
        assertTrue(this.item.isUpForAuction());
        assertEquals("Dummy account", this.item.getCustomerWithTheHighestBid().getUserName());
    }

    @Test
    // Tests to see if the update bid method modifies the item and customers properly
    public void updateBidStatusTest() {
        // customer1 decided to bid on item for $120
        this.item.updateBidStatus(this.customerAccount1, 120);

        // customer1 should have $880, customer1 should be the highest bidder.
        assertEquals(880, this.customerAccount1.getBalance());
        assertEquals(this.customerAccount1, this.item.getCustomerWithTheHighestBid());

        // customer2 decided to bid on item for $150
        this.item.updateBidStatus(this.customerAccount2, 150);
        /* customer1 should have $1000 again, because customer's bid gets returned when they no longer have the
        highest bid. customer2 should be the highest bidder now with $850
         */
        assertEquals(1000, this.customerAccount1.getBalance());
        assertEquals(850, this.customerAccount2.getBalance());
        assertEquals(this.customerAccount2, this.item.getCustomerWithTheHighestBid());
    }

    @Test
    // Tests the method to auction off items
    public void changeStatusToAuctionedOffTest() {
        // Auctions off item with customer 1 as highest bidding customer
        this.item.updateBidStatus(this.customerAccount1, 200);
        this.item.changeStatusToAuctionedOff();

        // customer 1 should have the item in their itemsWon inventory, and have $800. Item auction status should = false
        assertEquals(800, this.customerAccount1.getBalance());
        assertTrue(this.customerAccount1.getItemsWon().contains(item.getItemName()));
        assertFalse(this.item.isUpForAuction());
    }
}
