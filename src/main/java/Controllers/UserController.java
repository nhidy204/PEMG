package Controllers;

import Models.*;
import Services.*;
import Services.Interfaces.ITransactionService;
import Services.Interfaces.IUserService;
import Services.Interfaces.IValidateService;
import Controllers.TransactionController;


import java.util.List;
import java.util.Scanner;

public class UserController {
    private final IUserService userService;
    private final ITransactionService transactionService;  // Dịch vụ giao dịch
    private final IValidateService validateService;
    private final Scanner scanner = new Scanner(System.in);
    private User loggedInUser;  // Store the logged-in user

    public UserController() {
        this.userService = UserService.getInstance();
        this.transactionService = TransactionService.getInstance();  // Khởi tạo dịch vụ giao dịch
        this.validateService = ValidateService.getInstance();

    }
    // Phương thức thêm giao dịch
    private void addTransaction() {
        System.out.println("Enter transaction details:");

        String type = validateService.inputString("Enter type (e.g., Income, Expense): ", null);
        double amount = validateService.inputDouble("Enter amount: ");
        String category = validateService.inputString("Enter category: ", null);

        Transaction transaction = new Transaction(type, amount, category);
        transactionService.addTransaction(transaction);
    }


    public void register() {
        String username = validateService.inputString("Enter username: ", null);
        String password = validateService.inputString("Enter password: ", null);

        if (userService.isUsernameTaken(username)) {
            System.out.println("Username is already taken. Please try a different one.");
        } else {
            User newUser = userService.registerUser(username, password);
            newUser.setWallet(new Wallet(0.0)); // Khởi tạo ví mặc định
            System.out.println("User registered successfully!");
        }
    }

    public boolean login() {
        String loginUsername = validateService.inputString("Login with your username: ", null);
        String loginPassword = validateService.inputString("Enter your password: ", null);

        User user = userService.loginUser(loginUsername, loginPassword);
        if (user != null) {
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
            System.out.println("Menu for you:");
            System.out.println("1. Transaction");
            System.out.println("2. Budget");
            System.out.println("3. Financial Goals");
            System.out.println("4. Wallet");
            System.out.println("5. Logout");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);
            if (loggedInUser != null) {
                loggedInUser.listBudgets();
            } else {
                System.out.println("No user is logged in.");
            }

            switch (choice) {
                case 1:
                    showTransactionMenu();
                    break;

                case 2:
                    System.out.println("1. List Budgets\n2. Add Budget");
                    int budgetChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (budgetChoice == 1) {
                        if (loggedInUser != null) {
                            loggedInUser.listBudgets();
                        } else {
                            System.out.println("No user is logged in.");
                        }
                    } else if (budgetChoice == 2) {
                        System.out.print("Enter budget name: ");
                        String budgetName = scanner.nextLine();
                        System.out.print("Enter budget amount: ");
                        try {
                            double budgetAmount = scanner.nextDouble();
                            scanner.nextLine();
                            Budget budget = new Budget(budgetName, budgetAmount);
                            loggedInUser.addBudget(budget);
                            System.out.println("Budget added successfully!");
                        } catch (Exception e) {
                            System.out.println("Invalid amount! Please try again.");
                            scanner.nextLine(); // Clear buffer
                        }
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;

                case 3:
                    System.out.println("1. List Financial Goals\n2. Add Financial Goal\n3. Check Financial Goal Progress");
                    int goalChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (goalChoice == 1) {
                        loggedInUser.listFinancialGoals();
                    } else if (goalChoice == 2) {
                        System.out.print("Enter goal name: ");
                        String goalName = scanner.nextLine();
                        System.out.print("Enter target amount: ");
                        try {
                            double goalAmount = scanner.nextDouble();
                            scanner.nextLine();
                            FinancialGoal goal = new FinancialGoal(goalName, goalAmount);
                            loggedInUser.addFinancialGoal(goal);
                            System.out.println("Financial Goal added successfully!");
                        } catch (Exception e) {
                            System.out.println("Invalid amount! Please try again.");
                            scanner.nextLine(); // Clear buffer
                        }
                    } else if (goalChoice == 3) {
                        FinancialGoalService financialGoalService = new FinancialGoalService();
                        financialGoalService.checkAndNotifyGoals(loggedInUser);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;

                case 4: // Wallet
                    if (loggedInUser.getWallet() == null) {
                        loggedInUser.setWallet(new Wallet(0.0)); // Khởi tạo ví mặc định
                        System.out.println("Wallet was not initialized. A default wallet has been created.");
                    }
                    System.out.println("Wallet Details: ");
                    System.out.println(loggedInUser.getWallet());
                    break;


                case 5:
                    System.out.println("Logging out!");
                    loggedInUser = null;
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
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
                    TransactionController.addTransaction(); // Gọi phương thức addTransaction
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
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }



    // Phương thức hiển thị danh sách giao dịch
    private void listTransactions() {
        List<Transaction> transactions = transactionService.listTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("Transaction List:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    // Phương thức sửa giao dịch
    private void editTransaction() {
        listTransactions();
        int id = validateService.inputInt("Enter the ID of the transaction to edit: ", 1, Integer.MAX_VALUE);

        String type = validateService.inputString("Enter new type (or press Enter to keep current): ", null);
        String amountInput = validateService.inputString("Enter new amount (or press Enter to keep current): ", null);
        String category = validateService.inputString("Enter new category (or press Enter to keep current): ", null);

        // Cập nhật giao dịch
        Transaction updatedTransaction = new Transaction(
                type.isEmpty() ? null : type,
                amountInput.isEmpty() ? -1 : Double.parseDouble(amountInput),
                category.isEmpty() ? null : category
        );
        transactionService.editTransaction(id, updatedTransaction);
    }

    // Phương thức xóa giao dịch
    private void deleteTransaction() {
        listTransactions();
        int id = validateService.inputInt("Enter the ID of the transaction to delete: ", 1, Integer.MAX_VALUE);
        transactionService.deleteTransaction(id);
    }
}