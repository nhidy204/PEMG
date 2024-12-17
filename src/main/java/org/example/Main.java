package org.example;

import Models.User;
import Controllers.FileController;
import Controllers.UserController;
import Controllers.WalletController;
import Controllers.BudgetController;
import Controllers.TransactionController;
import Controllers.FinancialGoalController;
import Controllers.ExpenseTargetController;
import Services.ValidateService;
import Services.WalletService;
import Services.BudgetService;
import Services.TransactionService;
import Services.FinancialGoalService;
import Services.ExpenseTargetService;
import Services.Interfaces.IWalletService;
import Services.Interfaces.IBudgetService;
import Services.Interfaces.ITransactionService;
import Services.Interfaces.IFinancialGoalService;
import Services.Interfaces.IExpenseTargetService;

import java.util.ArrayList;

public class Main {
    static void showPostLoginMenu(ValidateService validateService, User currentUser, WalletController walletController, BudgetController budgetController, TransactionController transactionController, FinancialGoalController financialGoalController, ExpenseTargetController expenseTargetController) {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Wallet");
            System.out.println("2. Budget");
            System.out.println("3. Transaction");
            System.out.println("4. Expense Targets");
            System.out.println("5. Financial Goals");
            System.out.println("6. Logout");

            int choice = validateService.inputInt("Choose an option: ", 1, 6);

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
                    System.out.println("Transaction of " + currentUser.getUsername());
                    transactionController.showTransactionMenu(currentUser.getId());
                    break;
                case 4: // Expense Targets
                    System.out.println("Expense Targets of " + currentUser.getUsername());
                    expenseTargetController.showExpenseTargetMenu(currentUser.getId());
                    break;
                case 5: // Financial Goals
                    System.out.println("Financial Goals of " + currentUser.getUsername());
                    financialGoalController.showFinancialGoalMenu(currentUser.getId());
                    break;
                case 6:
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
        ITransactionService transactionService = TransactionService.getInstance(walletService, budgetService); // Initialize transactionService to manage transaction-related functions
        TransactionController transactionController = new TransactionController(transactionService, validateService); // Initialize transactionController
        IFinancialGoalService financialGoalService = FinancialGoalService.getInstance(); // Initialize financialGoalService to manage financial goal-related functions
        FinancialGoalController financialGoalController = new FinancialGoalController(financialGoalService, validateService, walletService); // Initialize financialGoalController
        IExpenseTargetService expenseTargetService = ExpenseTargetService.getInstance(); // Initialize expenseTargetService to manage expense target-related functions
        ExpenseTargetController expenseTargetController = new ExpenseTargetController(expenseTargetService, validateService); // Initialize expenseTargetController

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
                    userController.register(users);
                    break;
                case 2:
                    User user = userController.login(users); // Call the login method from userController and save the current user if login is successful
                    if (user != null) {
                        currentUser = user;
                        showPostLoginMenu(validateService, currentUser, walletController, budgetController, transactionController, financialGoalController, expenseTargetController);
                    }
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    System.exit(0);
            }
        }
    }
}
