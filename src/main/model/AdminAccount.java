package model;

public class AdminAccount implements Account {

    private String username;
    private String password;

    public AdminAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUserName() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
