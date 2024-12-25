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

public class UserService implements IUserService {
    public static UserService instance;
    private final String FILE_PATH = FileConstants.ROOT_PATH + "/" + FileConstants.USER_FILE_NAME;

    public UserService() {}

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public User registerUser(String username, String password, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUsername() != null && user.getUsername().equals(username)) {
                System.out.println("Username already exists. Please choose a different username.");
                return user;
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
        return newUser;
    }

    @Override
    public User loginUser(String username, String password, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
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

    public boolean isUsernameTaken(String username, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> loadUsers() {
        Gson gson = new Gson(); //tao new data trong gson
        try (FileReader reader = new FileReader(FILE_PATH)) {  //mo file json tai duong dan dc chi dinh
            StringBuilder contentBuilder = new StringBuilder();
            char[] buffer = new char[1024]; // khai bao 1 mang ky tu buffer co kich thuoc = 1024
            int charsRead; //doc tung ky tu duoc luu

            while ((charsRead = reader.read(buffer)) != -1) { //doc
                contentBuilder.append(buffer, 0, charsRead);
            }
            String content = contentBuilder.toString().trim();
            if (content.isEmpty()) {
                System.out.println("File is empty.");
                return new ArrayList<>();
            }

            if (!content.startsWith("[") || !content.endsWith("]")) {
                System.out.println("Invalid JSON format. Starting with an empty list.");
                return new ArrayList<>();
            }

            User[] userArray = gson.fromJson(content, User[].class);

            if (userArray != null) {
                ArrayList<User> users = new ArrayList<>(Arrays.asList(userArray));
                System.out.println("Loaded users: " + users.size());
                return users;
            } else {
                System.out.println("No valid user data found in the file.");
                return new ArrayList<>();
            }

        } catch (FileNotFoundException e) {
            System.out.println("No users file found at " + FILE_PATH + ", starting with an empty list.");
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return new ArrayList<>();
        } catch (JsonSyntaxException e) {
            System.out.println("JSON syntax error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
