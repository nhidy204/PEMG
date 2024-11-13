package Controllers;

import Models.User;
import Services.Interfaces.IUserService;
import Services.Interfaces.IValidateService;
import Services.UserService;
import Services.ValidateService;
import java.util.Scanner;

public class UserController {
    private final IUserService userService;
    private final IValidateService validateService;
    private final Scanner scanner = new Scanner(System.in);
    private User loggedInUser;  // Store the logged-in user

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
            loggedInUser = user;  // Set loggedInUser to the authenticated user
            showPostLoginMenu();
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    private void showPostLoginMenu() {
        while (true) {
            System.out.println("\nMenu for you:");
            System.out.println("1. Transaction");
            System.out.println("2. Budget");
            System.out.println("3. Financial Goals");
            System.out.println("4. Logout");

            int choice = validateService.inputInt("Choose an option: ", 1, 4);

            switch (choice) {
                case 1:
                    showTransactionMenu();
                    break;
                case 2:
                    // Placeholder for Budget functionality
                    System.out.println("Budget functionality coming soon.");
                    break;
                case 3:
                    // Placeholder for Financial Goals functionality
                    System.out.println("Financial Goals functionality coming soon.");
                    break;
                case 4:
                    System.out.println("Logging out...");
                    loggedInUser = null;  // Clear logged-in user on logout
                    return;
            }
        }
    }

    private void showTransactionMenu() {
        while (true) {
            System.out.println("\nTransaction Menu:");
            System.out.println("1. Add Transaction");
            System.out.println("2. List Transactions");
            System.out.println("3. Edit Transaction");
            System.out.println("4. Delete Transaction");
            System.out.println("5. Back to Main Menu");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    listTransactions();
                    break;
                case 3:
                    editTransaction();
                    break;
                case 4:
                    deleteTransaction();
                    break;
                case 5:
                    return;
            }
        }
    }

    private void addTransaction() {
        System.out.println("Adding a new transaction: ");
        System.out.print("Enter type (e.g., Income, Expense): ");
        String type = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        // Use loggedInUser to add the transaction
        if (loggedInUser != null) {
            loggedInUser.addTransaction(type, amount, category);
            System.out.println("Transaction added!");
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    private void listTransactions() {
        System.out.println("Listing all transactions:");
        // Example of listing transactions; implementation may vary based on User class
        if (loggedInUser != null) {
            loggedInUser.listTransactions();  // Assume this method is in the User class
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    private void editTransaction() {
        System.out.println("Editing a transaction:");
    }

    private void deleteTransaction() {
        System.out.println("Deleting a transaction:");
    }
}
