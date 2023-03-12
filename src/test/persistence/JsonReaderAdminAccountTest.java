package persistence;

import model.AdminAccount;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderAdminAccountTest extends JsonTest {

    @Test
    void testNonExistentFile() {
        JsonReaderAdminAccount jraa = new JsonReaderAdminAccount("./data/doesNotExist.json");
        try {
            AdminAccount aa = jraa.read();
            fail("Exception should have been thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderNormalCircumstances() {
        JsonReaderAdminAccount jraa = new JsonReaderAdminAccount("./data/testReaderGeneralAdminAccount.json");
        try {
            AdminAccount aa = jraa.read();
            checkAdminAccount("AuctionAdmin", "DoNotLeak1010", aa);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
