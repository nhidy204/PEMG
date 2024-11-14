package Services;

import Models.User;
import Services.Interfaces.IUserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    public static UserService instance;
    public final List<User> users = new ArrayList<>();
    private final String FILE_PATH = "users.json"; // File to store the users' data

    public UserService() {
        loadUsers();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void registerUser(String username, String password) {
        User newUser = new User();
        users.add(newUser);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting() //de doc hon
                .create();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("User registered successfully!");
    }

    @Override
    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getId().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null; // Return null if no matching user is found
    }

    @Override
    public double getUserBalance() {
        return 0;
    }

    @Override
    public void viewReports() {}

    //load user tu file
    public void loadUsers() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<User>>() {}.getType();
                List<User> loadedUsers = gson.fromJson(reader, listType);
                users.addAll(loadedUsers); // Load users into the list
            } catch (IOException e) {
                System.out.println("Error reading from file: " + e.getMessage());
            }
        }
    }
}
