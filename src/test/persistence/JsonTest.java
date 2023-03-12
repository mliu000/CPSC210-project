package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.AdminAccount;
import model.CustomerAccount;
import model.Item;

import java.util.ArrayList;

public class JsonTest {

    protected void checkItem(String name,
                             int minBiddingPrice,
                             String highestBidUsername, String highestBidPassword, int balance, ArrayList<String> itemsWon,
                             Item item) {
        assertEquals(name, item.getItemName());
        assertEquals(minBiddingPrice, item.getItemMinBiddingPrice());
        assertEquals(highestBidUsername, item.getCustomerWithTheHighestBid().getUserName());
        assertEquals(highestBidPassword, item.getCustomerWithTheHighestBid().getPassword());
        assertEquals(balance, item.getCustomerWithTheHighestBid().getBalance());
        assertTrue(item.isUpForAuction());
    }

    protected void checkCustomerAccount(String un, String pw, int bal, ArrayList<String> iw, CustomerAccount ca) {
        assertEquals(un, ca.getUserName());
        assertEquals(pw, ca.getPassword());
        assertEquals(bal, ca.getBalance());
        assertEquals(iw, ca.getItemsWon());
    }

    protected void checkAdminAccount(String un, String pw, AdminAccount aa) {
        assertEquals(un, aa.getUserName());
        assertEquals(pw, aa.getPassword());
    }
}
