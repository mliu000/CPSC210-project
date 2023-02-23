package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckStringTest {

    CheckString stringChecker = new CheckString();
    private String stringWithUpperCaseLowerCaseAndDigits;
    private String stringWithoutDigits;
    private String stringWithOutUpperCase;
    private String stringWithoutLowerCase;

    @BeforeEach
    public void runBefore() {
        this.stringWithUpperCaseLowerCaseAndDigits = "muyeLiu1208";
        this.stringWithoutDigits = "muyeLiu";
        this.stringWithoutLowerCase = "MUYE1208";
        this.stringWithOutUpperCase = "muye1208";
    }

    // For each of the tests below, it makes sure that the following methods are producing the correct boolean values.

    @Test
    // Test to make sure method produces true if string contains lower case letters, false if not
    public void containsLowerCaseLettersTest() {
        assertTrue(stringChecker.containsLowerCaseLetters(this.stringWithoutDigits));
        assertTrue(stringChecker.containsLowerCaseLetters(this.stringWithUpperCaseLowerCaseAndDigits));
        assertTrue(stringChecker.containsLowerCaseLetters(this.stringWithOutUpperCase));
        assertFalse(stringChecker.containsLowerCaseLetters(this.stringWithoutLowerCase));
    }

    @Test
    // Test to make sure method produces true if string contains upper case letters, false if not
    public void containsUpperCaseLettersTest() {
        assertTrue(stringChecker.containsUpperCaseLetters(this.stringWithUpperCaseLowerCaseAndDigits));
        assertTrue(stringChecker.containsUpperCaseLetters(this.stringWithoutDigits));
        assertTrue(stringChecker.containsUpperCaseLetters(this.stringWithoutLowerCase));
        assertFalse(stringChecker.containsUpperCaseLetters(this.stringWithOutUpperCase));
    }

    @Test
    // Test to make sure method produces true if string contains digits, false if not
    public void containsDigitsTest() {
        assertTrue(stringChecker.containsDigits(this.stringWithUpperCaseLowerCaseAndDigits));
        assertFalse(stringChecker.containsDigits(this.stringWithoutDigits));
        assertTrue(stringChecker.containsDigits(this.stringWithoutLowerCase));
        assertTrue(stringChecker.containsDigits(this.stringWithOutUpperCase));
    }

}