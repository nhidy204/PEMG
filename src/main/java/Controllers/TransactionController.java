package Controllers;

import Models.Transaction;
import Models.User;
import Services.BudgetService;
import Services.TransactionService;
import Services.WalletService;

import java.util.Scanner;

public class TransactionController {

    private static final Scanner scanner = new Scanner(System.in);
    private static WalletService walletService = new WalletService();
    private static TransactionService transactionService = new TransactionService();
    private static BudgetService budgetService = new BudgetService();
    private static User user;


    public TransactionController(BudgetService budgetService, WalletService walletService) {
        TransactionController.budgetService = budgetService;
        TransactionController.walletService = walletService;
        transactionService = TransactionService.getInstance();
        user = new User();
    }

    public static void addTransaction() {

        System.out.println("Adding a new transaction:");
        System.out.print("Enter type (Income/Expense): ");
        String type = scanner.nextLine().trim();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter category (Expense Target): ");
        String category = scanner.nextLine().trim();

        Transaction transaction = new Transaction(type, amount, category);

        if (type.equalsIgnoreCase("Income")) {
            walletService.addIncome(user, amount);
            transactionService.addTransaction(transaction);
            System.out.println("Income transaction added successfully!");
        } else if (type.equalsIgnoreCase("Expense")) {
            if (!budgetService.isTransactionExceedingBudget(user, transaction)) {
                if (walletService.deductExpense(user, amount)) {
                    transactionService.addTransaction(transaction);
                    System.out.println("Expense transaction added successfully!");
                } else {
                    System.out.println("Not enough balance in the wallet.");
                }
            } else {
                System.out.println("Transaction exceeds budget limit.");
            }
        } else {
            System.out.println("Invalid transaction type.");
        }
    }


    public WalletService getWalletService() {
        return walletService;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
