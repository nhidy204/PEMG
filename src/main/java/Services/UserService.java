package Services;

import Constants.FileConstants;
import Models.User;
import Services.Interfaces.IUserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService implements IUserService {
    public static UserService instance;
    public List<User> users = new ArrayList<>();
    private final String FILE_PATH = FileConstants.ROOT_PATH + "/" + FileConstants.USER_FILE_NAME;

    public UserService() {
        users = new ArrayList<>();
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
        for (User user : users) {
            if (user.getUsername() != null && user.getUsername().equals(username)) {
                System.out.println("Username already exists. Please choose a different username.");
                return;
            }
        }
        User newUser = new User(username, password);
        users.add(newUser);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    @Override
    public double getUserBalance() {
        return 0;
    }

    @Override
    public void viewReports() {
    }

    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getName().equals(username)) { // Kiểm tra name
                return true;
            }
        }
        return false;
    }



    private void loadUsers() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            StringBuilder contentBuilder = new StringBuilder();
            char[] buffer = new char[1024];
            int charsRead;

            while ((charsRead = reader.read(buffer)) != -1) {
                contentBuilder.append(buffer, 0, charsRead);
            }

            String content = contentBuilder.toString().trim();

            if (content.isEmpty()) {
                System.out.println("File is empty.");
                users = new ArrayList<>();
                return;
            }

            if (!content.startsWith("[") || !content.endsWith("]")) {
                System.out.println("Invalid JSON format. Starting with an empty list.");
                users = new ArrayList<>();
                return;
            }

            User[] userArray = gson.fromJson(content, User[].class);

            if (userArray != null) {
                users = new ArrayList<>(Arrays.asList(userArray));
            } else {
                users = new ArrayList<>();
                System.out.println("No valid user data found in the file.");
            }

            System.out.println("Loaded users: " + users.size());
        } catch (FileNotFoundException e) {
            System.out.println("No users file found at " + FILE_PATH + ", starting with an empty list.");
            users = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            users = new ArrayList<>();
        } catch (JsonSyntaxException e) {
            System.out.println("JSON syntax error: " + e.getMessage());
            users = new ArrayList<>();
        }
    }

}
