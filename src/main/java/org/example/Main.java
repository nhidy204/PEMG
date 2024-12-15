package org.example;

import Models.User;
import Controllers.FileController;
import Controllers.UserController;
import Controllers.WalletController;
import Controllers.BudgetController;
import Controllers.TransactionController;
import Controllers.FinancialGoalController;
import Services.ValidateService;
import Services.WalletService;
import Services.BudgetService;
import Services.TransactionService;
import Services.FinancialGoalService;
import Services.Interfaces.IWalletService;
import Services.Interfaces.IBudgetService;
import Services.Interfaces.ITransactionService;
import Services.Interfaces.IFinancialGoalService;

import java.util.ArrayList;

public class Main {
    static void showPostLoginMenu(ValidateService validateService, User currentUser, WalletController walletController, BudgetController budgetController, TransactionController transactionController, FinancialGoalController financialGoalController) {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Transaction");
            System.out.println("2. Budget");
            System.out.println("3. Financial Goals");
            System.out.println("4. Wallet");
            System.out.println("5. Logout");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1: // Transaction
                    System.out.println("Transaction of " + currentUser.getUsername());
                    transactionController.showTransactionMenu();
                    break;
                case 2: // Budget
                    System.out.println("Budget of " + currentUser.getUsername());
                    budgetController.showBudgetMenu();
                    break;
                case 3: // Financial Goals
                    System.out.println("Financial Goals of " + currentUser.getUsername());
                    financialGoalController.showFinancialGoalMenu();
                    break;
                case 4: // Wallet
                    System.out.println("Wallet of " + currentUser.getUsername());
                    walletController.showWalletMenu();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        FileController fileController = new FileController(); // Create an instance for fileController to manage data files
        fileController.createUserFile(); // Create a file to store user information
        ValidateService validateService = ValidateService.getInstance(); // Initialize validateService to authenticate and get data from the user
        UserController userController = new UserController();  // Initialize userController to manage user-related functions
        IWalletService walletService = WalletService.getInstance(); // Initialize walletService to manage wallet-related functions
        WalletController walletController = new WalletController(walletService, validateService); // Initialize walletController
        IBudgetService budgetService = BudgetService.getInstance(); // Initialize budgetService to manage budget-related functions
        BudgetController budgetController = new BudgetController(budgetService, validateService); // Initialize budgetController
        ITransactionService transactionService = TransactionService.getInstance(); // Initialize transactionService to manage transaction-related functions
        TransactionController transactionController = new TransactionController(transactionService, validateService); // Initialize transactionController
        IFinancialGoalService financialGoalService = FinancialGoalService.getInstance(); // Initialize financialGoalService to manage financial goal-related functions
        FinancialGoalController financialGoalController = new FinancialGoalController(financialGoalService, validateService); // Initialize financialGoalController
        User currentUser = new User();

        ArrayList<User> users = userController.loadUser();
        System.out.println(users);
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("Welcome to the Personal Expense Management System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");

            int option = validateService.inputInt("Your choice: ", 1, 3);

            switch (option) {
                case 1:
                    userController.register(users); // Call the register method from userController
                    break;
                case 2:
                    User user = userController.login(users); // Call the login method from userController and save the current user if login is successful
                    if (user != null) {
                        currentUser = user;
                        showPostLoginMenu(validateService, currentUser, walletController, budgetController, transactionController, financialGoalController);
                    }
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    System.exit(0);
            }
        }
    }
}
