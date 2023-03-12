package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CustomerAccountTest {

    private CustomerAccount customer;
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    private Item item5;
    private Item item6;

    @BeforeEach
    public void runBefore() {
        customer = new CustomerAccount("AnimeLover", "1234", 0, new ArrayList<>());
        this.item1 = new Item("Tanjiro figure", 100,
                new CustomerAccount("null", "null", 0, new ArrayList<>()));
        this.item2 = new Item("Nezuko figure", 100,
                new CustomerAccount("null", "null", 0, new ArrayList<>()));
        this.item3 = new Item("Shinobu figure", 120,
                new CustomerAccount("null", "null", 0, new ArrayList<>()));
        this.item4 = new Item("Kanao figure", 120,
                new CustomerAccount("null", "null", 0, new ArrayList<>()));
        this.item5 = new Item("Zenitsu figure", 120,
                new CustomerAccount("null", "null", 0, new ArrayList<>()));
        this.item6 = new Item("Muzan figure", 120,
                new CustomerAccount("null", "null", 0, new ArrayList<>()));
    }

    @Test
    // Tests the constructor
    public void constructorTest() {
        assertEquals("AnimeLover", this.customer.getUserName());
        assertEquals("1234", this.customer.getPassword());
        assertEquals(0, this.customer.getBalance());
        assertTrue(this.customer.getItemsWon().isEmpty());
    }

    @Test
    // Tests the change password method to make sure it changes the password
    public void changePasswordTest() {
        // Changes password to "4321"
        this.customer.changePassword("4321");
        assertEquals("4321", this.customer.getPassword());
    }

    @Test
    // Tests the deposit into balance method to make sure it deposits the balance
    public void depositIntoBalanceTest() {
        // Deposit $1000
        this.customer.depositIntoBalance(1000);
        assertEquals(1000, this.customer.getBalance());
    }

    @Test
    // Tests the deposit into balance method to make sure it extracts the balance
    public void extractFromBalanceTest() {
        // Deposit $1000, then extract $200.
        this.customer.depositIntoBalance(1000);
        this.customer.extractFromBalance(200);

        // Should produce 800
        assertEquals(800, this.customer.getBalance());
    }
}
