package model;

public class CheckString {

    public CheckString() {
    }

    // EFFECTS: checks to make sure string contains at least one uppercase character
    public boolean containsUpperCaseLetters(String string) {
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (Character.isUpperCase(character)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: checks to make sure string contains at least one lowercase character
    public boolean containsLowerCaseLetters(String string) {
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (Character.isLowerCase(character)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: checks to make sure string contains at least one digit
    public boolean containsDigits(String string) {
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (Character.isDigit(character)) {
                return true;
            }
        }
        return false;
    }
}
