package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminAccountTest {

    AdminAccount adminTestAccount;

    @BeforeEach
    public void runBefore() {

        this.adminTestAccount = new AdminAccount("AuctionAdmin", "DoNotLeak1010");
    }

    @Test
    // Checks to see whether the newly constructed fields are storing the correct results.
    public void constructorTest() {
        assertEquals("AuctionAdmin", this.adminTestAccount.getUserName());
        assertEquals("DoNotLeak1010", this.adminTestAccount.getPassword());
    }

    @Test
    // Checks to see whether the change password method actually changes the password.
    public void changePasswordTest() {
        this.adminTestAccount.changePassword("newPassword1010");
        assertEquals("newPassword1010", this.adminTestAccount.getPassword());
    }
}
