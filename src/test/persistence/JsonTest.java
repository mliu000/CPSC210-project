package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.AdminAccount;
import model.CustomerAccount;
import model.Item;

import java.util.ArrayList;

public class JsonTest {

    protected void checkItem(String name, int minBiddingPrice, CustomerAccount highestBid, Item item) {
        assertEquals(name, item.getItemName());
        assertEquals(minBiddingPrice, item.getItemMinBiddingPrice());
        assertEquals(highestBid, item.getCustomerWithTheHighestBid());
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
