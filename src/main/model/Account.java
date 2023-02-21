package model;

public interface Account {

    String getUserName();

    String getPassword();

    void changePassword(String newPassword);
}
