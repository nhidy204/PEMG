package Controllers;

import Models.User;
import Services.Interfaces.IUserService;
import Services.Interfaces.IValidateService;
import Services.UserService;
import Services.ValidateService;

public class UserController {
    private final IUserService userService;
    private final IValidateService validateService;

    public UserController() {
        this.userService = UserService.getInstance();
        this.validateService = ValidateService.getInstance();
    }

    public void register() {
        String username = validateService.inputString("Enter username: ", null);
        String password = validateService.inputString("Enter password: ", null);
        userService.registerUser(username, password);
    }

    public boolean login() {
        String loginUsername = validateService.inputString("Login with your username: ", null);
        String loginPassword = validateService.inputString("Enter your password: ", null);

        // Attempt to retrieve the user from the service
        User user = userService.loginUser(loginUsername, loginPassword);

        if (user != null) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }
}
