package persistence;

import model.AuctionMarket;
import model.CustomerAccount;
import model.CustomerDatabase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonReaderAuctionMarketTest extends JsonTest {

    @Test
    void testNonExistentFile() {
        CustomerDatabase cd = new CustomerDatabase();
        JsonReaderAuctionMarket jram = new JsonReaderAuctionMarket("./data/doesNotExist.json", cd.getCustomerAccountDatabase());
        try {
            AuctionMarket aa = jram.read();
            fail("Exception should have been thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyFile() {
        CustomerDatabase cd = new CustomerDatabase();
        JsonReaderAuctionMarket jram = new JsonReaderAuctionMarket("./data/testReaderEmptyAuctionMarketData.json", cd.getCustomerAccountDatabase());
        try {
            AuctionMarket newAa = jram.read();
            assertTrue(newAa.getItemsUpForAuction().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNormalCircumstances() {
        CustomerDatabase cd = new CustomerDatabase();
        JsonReaderAuctionMarket jram = new JsonReaderAuctionMarket("./data/testReaderGeneralAuctionMarketData.json", cd.getCustomerAccountDatabase());
        try {
            AuctionMarket newAm = jram.read();
            checkItem("asuna yuuki", 200, "Dummy account",
                    "Notinuse", 0, new ArrayList<>(), newAm.getItemsUpForAuction().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
