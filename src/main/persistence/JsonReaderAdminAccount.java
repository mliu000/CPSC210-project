package persistence;

import model.AdminAccount;
import model.CustomerDatabase;
import org.json.JSONObject;

import java.io.IOException;

public class JsonReaderAdminAccount extends JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderAdminAccount(String source) {
        this.source = source;

    }

    // EFFECTS: reads admin account, then returns it
    public AdminAccount read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        return new AdminAccount(username, password);
    }

}
