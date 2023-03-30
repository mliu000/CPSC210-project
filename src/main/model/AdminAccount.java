package model;

import org.json.JSONObject;
import persistence.Writable;

// Class that constructs the admin account. There is only ever one instance of this object in this program.
public class AdminAccount implements Account, Writable {

    private String username;
    private String password;

    // EFFECTS: constructs the one and only admin
    public AdminAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // EFFECTS: returns true if the both user inputted credentials match the fields, false otherwise.
    public boolean checkCredentials(String usernameInput, String passwordInput) {
        return this.username.equals(usernameInput) && this.password.equals(passwordInput);
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

    // MODIFIES: JSONObject
    // EFFECTS: Stores the contents of the admin account to JSON file
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", this.username);
        jsonObject.put("password", this.password);
        return jsonObject;
    }
}
