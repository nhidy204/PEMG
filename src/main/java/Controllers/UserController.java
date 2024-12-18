package Controllers;

import Models.User;
import Services.Interfaces.IUserService;
import Services.Interfaces.IValidateService;
import Services.UserService;
import Services.ValidateService;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController {
    private final IUserService userService;
    private final IValidateService validateService;
    private final Scanner scanner = new Scanner(System.in);

    public UserController() {
        this.userService = UserService.getInstance();
        this.validateService = ValidateService.getInstance();
    }

    public void register(ArrayList<User> users) {
        String username = validateService.inputString("Enter username: ", null);
        String password = validateService.inputString("Enter password: ", null);

        if (userService.isUsernameTaken(username, users)) {
            System.out.println("Username is already taken. Please try a different one.");
        } else {
            User newUser = userService.registerUser(username, password, users);
            System.out.println(newUser.getUsername() + " registered successfully.");
        }
    }

    public User login(ArrayList<User> users) {
        String loginUsername = validateService.inputString("Login with your username: ", null);
        String loginPassword = validateService.inputString("Enter your password: ", null);

        User user = userService.loginUser(loginUsername, loginPassword, users);
        while (user == null) {
            Boolean option = validateService.inputYesNo("Login again?");
            if (option) {
                loginUsername = validateService.inputString("Login with your username: ", null);
                loginPassword = validateService.inputString("Enter your password: ", null);

                user = userService.loginUser(loginUsername, loginPassword, users);
            } else {
                return null;
            }
        }
        return user;
    }

    public ArrayList<User> loadUser() {
        return userService.loadUsers();
    }
}
