package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDatabaseTest {

    private CustomerDatabase customerDatabaseTestList;
    private CustomerAccount customerAccount1;
    private CustomerAccount customerAccount2;
    private CustomerAccount customerAccount3;

    @BeforeEach
    public void runBefore() {
        // Instantiates the customers and customerDatabase
        this.customerDatabaseTestList = new CustomerDatabase();
        this.customerAccount1 = new CustomerAccount("AnimeLover", "1234", 0, new ArrayList<>());
        this.customerAccount2 = new CustomerAccount("AnimeObsessed", "1234", 0, new ArrayList<>());
        this.customerAccount3 = new CustomerAccount("AnimeCosplayer", "1234", 0, new ArrayList<>());
    }

    @Test
    // Tests the constructor
    public void constructorTest() {
        assertTrue(this.customerDatabaseTestList.getCustomerAccountDatabase().isEmpty());
    }

    @Test
    /* Tests to make sure that the method can find the appropriate account when inputted credentials matches,
    or null if no account found
     */
    public void checkIfAccountExistsTest() {
        // Adds the accounts to the list
        this.customerDatabaseTestList.getCustomerAccountDatabase().add(this.customerAccount1);
        this.customerDatabaseTestList.getCustomerAccountDatabase().add(this.customerAccount2);
        this.customerDatabaseTestList.getCustomerAccountDatabase().add(this.customerAccount3);

        // Customer match not found, username incorrect
        assertNull(this.customerDatabaseTestList.checkIfAccountExists("Anime", "1234"));

        // Customer match not found, password incorrect
        assertNull(this.customerDatabaseTestList.checkIfAccountExists("AnimeLover", "12345"));

        // Customer match not found, both password and username incorrect
        assertNull(this.customerDatabaseTestList.checkIfAccountExists("Anime", "12345"));

        // Customer found
        assertEquals(this.customerAccount1,
                this.customerDatabaseTestList.checkIfAccountExists("AnimeLover", "1234"));
    }

    @Test
    // Tests to make sure that the method produces true if there is a duplicate, false if not
    public void checkUsernameDuplicateTest() {
        // Adds the accounts to the list
        this.customerDatabaseTestList.getCustomerAccountDatabase().add(this.customerAccount1);
        this.customerDatabaseTestList.getCustomerAccountDatabase().add(this.customerAccount2);
        this.customerDatabaseTestList.getCustomerAccountDatabase().add(this.customerAccount3);

        // Case where duplicate found
        assertTrue(this.customerDatabaseTestList.checkUsernameDuplicate("AnimeLover"));

        // Case where there is no duplicate
        assertFalse(this.customerDatabaseTestList.checkUsernameDuplicate("AnimeHater"));
    }
}
