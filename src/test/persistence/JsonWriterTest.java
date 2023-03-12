package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriteToInvalidFile() {
        try {
            JsonWriter jsonWriter = new JsonWriter("/data/notFound");
            jsonWriter.open();
            fail("Exception should have been thrown");
        } catch (IOException e) {
            // Expected
        }
    }

    @Test
    void testWriteEmptyCustomerDatabase() {
        try {
            CustomerDatabase cd = new CustomerDatabase();
            JsonWriter jsonWriter = new JsonWriter("./data/testWriterEmptyData.json");
            jsonWriter.open();
            jsonWriter.write(cd);
            jsonWriter.close();

            JsonReaderCustomerDatabase jrcd = new JsonReaderCustomerDatabase("./data/testWriterEmptyData.json");
            CustomerDatabase newCd = jrcd.read(cd);
            assertTrue(newCd.getCustomerAccountDatabase().isEmpty());
        } catch (IOException e) {
            fail("Exception should not haven been thrown");
        }
    }

    @Test
    void testWriteEmptyAuctionMarket() {
        try {
            CustomerDatabase cd = new CustomerDatabase();
            AuctionMarket am = new AuctionMarket();
            JsonWriter jsonWriter = new JsonWriter("./data/testWriterEmptyData.json");
            jsonWriter.open();
            jsonWriter.write(am);
            jsonWriter.close();

            JsonReaderAuctionMarket jram = new JsonReaderAuctionMarket("./data/testWriterEmptyData.json",
                    cd.getCustomerAccountDatabase());
            AuctionMarket newAm = jram.read();
            assertTrue(newAm.getItemsUpForAuction().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testCustomerDatabaseNotEmpty() {
        try {
            CustomerDatabase cd = new CustomerDatabase();
            cd.getCustomerAccountDatabase().add(new CustomerAccount("1", "a", 0, new ArrayList<>()));
            cd.getCustomerAccountDatabase().add(new CustomerAccount("2", "b", 0, new ArrayList<>()));
            cd.getCustomerAccountDatabase().add(new CustomerAccount("3", "c", 0, new ArrayList<>()));

            JsonWriter jsonWriter = new JsonWriter("./data/testWriterNotEmptyData.json");
            jsonWriter.open();
            jsonWriter.write(cd);
            jsonWriter.close();

            JsonReaderCustomerDatabase jrcd = new JsonReaderCustomerDatabase("./data/testWriterEmptyData.json");
            CustomerDatabase newCd = jrcd.read(cd);

            assertEquals(3, newCd.getCustomerAccountDatabase().size());
            assertEquals("1", newCd.getCustomerAccountDatabase().get(0).getUserName());
            assertEquals("2", newCd.getCustomerAccountDatabase().get(1).getUserName());
            assertEquals("3", newCd.getCustomerAccountDatabase().get(2).getUserName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testAuctionMarketNotEmpty() {
        try {
            CustomerDatabase cd = new CustomerDatabase();
            CustomerAccount ca = new CustomerAccount("0", "a", 0, new ArrayList<>());
            AuctionMarket am = new AuctionMarket();
            am.getItemsUpForAuction().add(new Item("1", 100, ca));
            am.getItemsUpForAuction().add(new Item("2", 100, ca));
            am.getItemsUpForAuction().add(new Item("3", 100, ca));

            JsonWriter jsonWriter = new JsonWriter("./data/testWriterEmptyData.json");
            jsonWriter.open();
            jsonWriter.write(am);
            jsonWriter.close();

            JsonReaderAuctionMarket jram = new JsonReaderAuctionMarket("./data/testWriterEmptyData.json",
                    cd.getCustomerAccountDatabase());
            AuctionMarket newAm = jram.read();

            assertEquals(3, newAm.getItemsUpForAuction().size());
            assertEquals("1", newAm.getItemsUpForAuction().get(0).getItemName());
            assertEquals("2", newAm.getItemsUpForAuction().get(1).getItemName());
            assertEquals("3", newAm.getItemsUpForAuction().get(2).getItemName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // Cannot be empty
    @Test
    void testAdminAccount() {
        try {
            AdminAccount aa = new AdminAccount("AuctionAdmin", "DoNotLeak1010");

            JsonWriter jsonWriter = new JsonWriter("./data/testWriterAdminAccount.json");
            jsonWriter.open();
            jsonWriter.write(aa);
            jsonWriter.close();

            JsonReaderAdminAccount jraa = new JsonReaderAdminAccount("./data/testWriterAdminAccount.json");
            AdminAccount newAa = jraa.read();

            checkAdminAccount("AuctionAdmin", "DoNotLeak1010", aa);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }
}
