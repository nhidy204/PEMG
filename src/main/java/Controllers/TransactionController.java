package Controllers;

import Models.Transaction;
import Models.Budget;
import Services.Interfaces.ITransactionService;
import Services.Interfaces.IValidateService;
import Services.Interfaces.IBudgetService;
import Services.Interfaces.IWalletService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class TransactionController {
    private final ITransactionService transactionService;
    private final IValidateService validateService;
    private final IBudgetService budgetService;
    private final IWalletService walletService;
    private final ArrayList<Transaction> transactions;
    private final Scanner scanner = new Scanner(System.in);

    public TransactionController(ITransactionService transactionService, IValidateService validateService, IBudgetService budgetService, IWalletService walletService) {
        this.transactionService = transactionService;
        this.validateService = validateService;
        this.budgetService = budgetService;
        this.walletService = walletService;
        this.transactions = transactionService.loadTransactions();
    }

    public void showTransactionMenu(String userId) {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Add Transaction");
            System.out.println("2. List Transactions");
            System.out.println("3. Edit Transaction");
            System.out.println("4. Delete Transaction");
            System.out.println("5. Back to main menu");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    addTransaction(userId);
                    break;
                case 2:
                    listTransactions(userId);
                    break;
                case 3:
                    editTransaction(userId);
                    break;
                case 4:
                    deleteTransaction(userId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addTransaction(String userId) {
        ArrayList<Budget> budgets = budgetService.listBudgets(userId, budgetService.loadBudgets());

        if (walletService.getWalletByUserId(userId) == null) {
            System.out.println("You need to create a wallet first.");
            return;
        } else if (budgets.isEmpty()) {
            System.out.println("You need to create a budget first.");
            return;
        }

        System.out.println("List of Budgets:");
        for (Budget budget : budgets) {
            System.out.printf("%-50s %-20s %-12s\n", "|ID|","|Name|", "|Max Amount|" );
            System.out.printf("%-50s %-20s %-12s\n",  budget.getId(), budget.getBudgetName(),  budget.getMaximumAmount() );
           // System.out.println("Budget Name: " + budget.getBudgetName() + ", Maximum Amount: " + budget.getMaximumAmount() + ", ID: " + budget.getId());
        }

        String budgetId = validateService.inputString("Enter budget ID: ", null);

        String type = validateService.inputString("Enter transaction type (income/expense): ", null);
        double amount = validateService.inputDouble("Enter transaction amount: ");
        String category = validateService.inputString("Enter transaction category: ", null);
        String name = validateService.inputString("Enter transaction name: ", null);

        Transaction transaction = new Transaction(UUID.randomUUID().toString(), type, amount, category, budgetId, LocalDateTime.now().toString(), LocalDateTime.now().toString(), name, userId);
        transactionService.addTransaction(transaction, transactions);
    }

    private void listTransactions(String userId) {
        ArrayList<Transaction> userTransactions = transactionService.listTransactions(userId, transactions);
        if (userTransactions.isEmpty()) {
            System.out.println("No transactions found for this user.");
        } else {
            for (Transaction transaction : userTransactions) {
                System.out.println("Transaction Type: " + transaction.getType() + ", Amount: " + transaction.getAmount() + ", Category: " + transaction.getCategory() + ", Name: " + transaction.getName() + ", ID: " + transaction.getId());
            }
        }
    }

    private void editTransaction(String userId) {
        String transactionId = validateService.inputString("Enter transaction ID to edit: ", null);
        ArrayList<Transaction> userTransactions = transactionService.listTransactions(userId, transactions);

        Transaction oldTransaction = null;
        for (Transaction transaction : userTransactions) {
            if (transaction.getId().equals(transactionId)) {
                oldTransaction = transaction;
                break;
            }
        }

        if (oldTransaction == null) {
            System.out.println("No transaction found with the given ID.");
            return;
        }

        String type = validateService.inputString("Enter new transaction type (income/expense): ", null);
        double amount = validateService.inputDouble("Enter new transaction amount: ");
        String category = validateService.inputString("Enter new transaction category: ", null);
        String name = validateService.inputString("Enter new transaction name: ", null);

        Transaction updatedTransaction = new Transaction(transactionId, type, amount, category, oldTransaction.getCategory(), oldTransaction.getCreatedAt(), LocalDateTime.now().toString(), name, userId);
        transactionService.editTransaction(transactionId, updatedTransaction, transactions);
    }

    private void deleteTransaction(String userId) {
        String transactionId = validateService.inputString("Enter transaction ID to delete: ", null);
        transactionService.deleteTransaction(transactionId, transactions);
    }
}
