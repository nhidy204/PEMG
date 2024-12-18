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
            System.out.println("1. Wallet");
            System.out.println("2. Budget");
            System.out.println("3. Transaction");
            System.out.println("4. Financial Goals");
            System.out.println("5. Logout");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1: // Wallet
                    System.out.println("Wallet of " + currentUser.getUsername());
                    walletController.showWalletMenu(currentUser.getId());
                    break;
                case 2: // Budget
                    System.out.println("Budget of " + currentUser.getUsername());
                    budgetController.showBudgetMenu(currentUser.getId());
                    break;
                case 3: // Transaction
                    if (walletController.getWalletByUserId(currentUser.getId()) == null) {
                        System.out.println("You need to create a wallet first.");
                        walletController.showWalletMenu(currentUser.getId());
                    } else if (budgetController.listBudgets(currentUser.getId()).isEmpty()) {
                        System.out.println("You need to create a budget first.");
                        budgetController.showBudgetMenu(currentUser.getId());
                    } else {
                        System.out.println("Transaction of " + currentUser.getUsername());
                        transactionController.showTransactionMenu(currentUser.getId());
                    }
                    break;
                case 4: // Financial Goals
                    System.out.println("Financial Goals of " + currentUser.getUsername());
                    financialGoalController.showFinancialGoalMenu(currentUser.getId());
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
        IBudgetService budgetService = BudgetService.getInstance(walletService); // Initialize budgetService to manage budget-related functions
        BudgetController budgetController = new BudgetController(budgetService, validateService); // Initialize budgetController
        ITransactionService transactionService = TransactionService.getInstance(walletService, budgetService); // Initialize transactionService to manage transaction-related functions
        TransactionController transactionController = new TransactionController(transactionService, validateService, budgetService, walletService); // Initialize transactionController
        IFinancialGoalService financialGoalService = FinancialGoalService.getInstance(); // Initialize financialGoalService to manage financial goal-related functions
        FinancialGoalController financialGoalController = new FinancialGoalController(financialGoalService, validateService, walletService); // Initialize financialGoalController

        User currentUser = new User();

        ArrayList<User> users = userController.loadUser();
        System.out.println("Loaded users: " + users.size());
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("Welcome to the Personal Expense Management System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");

            int option = validateService.inputInt("Your choice: ", 1, 3);

            switch (option) {
                case 1:
                    userController.register(users);
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
