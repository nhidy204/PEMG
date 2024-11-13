package Services;

import Models.User;
import Services.Interfaces.IUserService;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    private static UserService instance;
    private final List<User> users = new ArrayList<>();

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void registerUser(String username, String password) {
        User newUser = new User(username, password); // Assuming initial balance is 0
        users.add(newUser);
        System.out.println("User registered successfully!");
    }

    @Override
    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Return the matched user
            }
        }
        return null; // Return null if no matching user is found
    }

    @Override
    public double getUserBalance() {
        return 0;
    }

    @Override
    public void viewReports() {}
}
