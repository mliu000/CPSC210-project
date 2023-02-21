package model;

public class CheckString {

    public CheckString() {
    }

    public boolean containsUpperCaseLetters(String string) {
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (Character.isUpperCase(character)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsLowerCaseLetters(String string) {
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (Character.isLowerCase(character)) {
                return true;
            }
        }
        return false;
    }

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
