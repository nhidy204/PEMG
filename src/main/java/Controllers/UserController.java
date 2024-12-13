package Controllers;

import Models.*;
import Services.*;
import Services.Interfaces.IUserService;
import Services.Interfaces.IValidateService;

import java.util.Scanner;

public class UserController {
    private final IUserService userService;
    private final IValidateService validateService;
    private final Scanner scanner = new Scanner(System.in);

    public UserController() {
        this.userService = UserService.getInstance();
        this.validateService = ValidateService.getInstance();

    }

    public void register() {
        String username = validateService.inputString("Enter username: ", null);
        String password = validateService.inputString("Enter password: ", null);

        if (userService.isUsernameTaken(username)) {
            System.out.println("Username is already taken. Please try a different one.");
        } else {
            User newUser = userService.registerUser(username, password);
            System.out.println(newUser.username + " registered successfully.");
        }
    }

    public User login() {
        String loginUsername = validateService.inputString("Login with your username: ", null);
        String loginPassword = validateService.inputString("Enter your password: ", null);

        User user = userService.loginUser(loginUsername, loginPassword);
        while (user == null) {
            Boolean option = validateService.inputYesNo("Login again?");
            if (option){
                loginUsername = validateService.inputString("Login with your username: ", null);
                loginPassword = validateService.inputString("Enter your password: ", null);

                user = userService.loginUser(loginUsername, loginPassword);
            }else {
                return null;
            }
        }
        showPostLoginMenu();
        return user;
    }

    private void showPostLoginMenu() {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Transaction");
            System.out.println("2. Budget");
            System.out.println("3. Financial Goals");
            System.out.println("4. Wallet");
            System.out.println("5. Logout");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4: // Wallet

                    break;


                case 5:

                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}