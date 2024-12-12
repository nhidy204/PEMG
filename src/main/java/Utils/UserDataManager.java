package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Models.User;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataManager {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public User loadUser(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, User.class);
        } catch (IOException e) {
            System.out.println("Unable to load user data.");
            return null;
        }
    }

    public void saveUser(User user) {
        try (FileWriter writer = new FileWriter("user.json")) {
            gson.toJson(user, writer);
            System.out.println("User data saved.");
        } catch (IOException e) {
            System.out.println("Unable to save user data.");
        }
    }
}
