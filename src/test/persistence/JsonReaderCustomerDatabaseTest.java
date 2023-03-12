package persistence;

import model.AdminAccount;
import model.CustomerDatabase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderCustomerDatabaseTest extends JsonTest {

    @Test
    void testNonExistentFile() {
        JsonReaderCustomerDatabase jrcd = new JsonReaderCustomerDatabase("./data/doesNotExist.json");
        try {
            CustomerDatabase aa = jrcd.read(new CustomerDatabase());
            fail("Exception should have been thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyFile() {
        JsonReaderCustomerDatabase jrcd = new JsonReaderCustomerDatabase("./data/testReaderEmptyCustomerDatabaseData.json");
        try {
            CustomerDatabase newCd = jrcd.read(new CustomerDatabase());
            assertTrue(newCd.getCustomerAccountDatabase().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNormalCircumstances() {
        JsonReaderCustomerDatabase jrcd = new JsonReaderCustomerDatabase("./data/testReaderGeneralCustomerDatabaseData.json");
        try {
            CustomerDatabase newCd = jrcd.read(new CustomerDatabase());
            assertEquals(2, newCd.getCustomerAccountDatabase().size());

            ArrayList<String> itemsWon0 = new ArrayList<>();
            itemsWon0.add("tanjiro");
            itemsWon0.add("nezuko");
            itemsWon0.add("shinobu");
            itemsWon0.add("kanao");
            itemsWon0.add("aqua");
            checkCustomerAccount("muye", "4321", 750, itemsWon0, newCd.getCustomerAccountDatabase().get(0));

            ArrayList<String> itemsWon1 = new ArrayList<>();
            itemsWon1.add("yuji itadori");
            checkCustomerAccount("muye1", "1234", 150, itemsWon1, newCd.getCustomerAccountDatabase().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
