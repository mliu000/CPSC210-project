package model;

public class AdminAccount implements Account {

    private String username;
    private String password;

    // EFFECTS: constructs an admin (there is only ever one)
    public AdminAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // EFFECTS: returns username
    @Override
    public String getUserName() {
        return this.username;
    }

    // EFFECTS: returns password
    @Override
    public String getPassword() {
        return this.password;
    }

    // MODIFIES: this
    // EFFECTS: changes password to new input
    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
