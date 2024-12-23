package Controllers;

import Models.Transaction;
import Models.Budget;
import Services.Interfaces.ITransactionService;
import Services.Interfaces.IValidateService;
import Services.Interfaces.IBudgetService;
import Services.Interfaces.IWalletService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println("5. Display Daily Expenditure Chart");
            System.out.println("6. Back to main menu");

            int choice = validateService.inputInt("Choose an option: ", 1, 6);

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
                    displayDailyExpenditureChart(userId);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addTransaction(String userId) {
        ArrayList<Budget> budgets = budgetService.listBudgets(userId, budgetService.loadBudgets());
        int id = 1;
        if (walletService.getWalletByUserId(userId) == null) {
            System.out.println("You need to create a wallet first.");
            return;
        } else if (budgets.isEmpty()) {
            System.out.println("You need to create a budget first.");
            return;
        }

        System.out.println("List of Budgets:");
        for (Budget budget : budgets) {
            System.out.printf("%-10s %-20s %-12s\n", "No.", "Name", "Max Amount");
            System.out.printf("%-10s %-20s %-12s\n", id, budget.getBudgetName(), budget.getMaximumAmount());
            id++;
        }
        int choice = validateService.inputInt("Select number of budget: ", 1, budgets.size());
        Budget selectedBudget = budgets.get(choice - 1);

        String type = validateService.inputString("Enter transaction type (income/expense): ", null);
        double amount = validateService.inputDouble("Enter transaction amount: ");
        String category = validateService.inputString("Enter transaction category: ", null);
        String name = validateService.inputString("Enter transaction name: ", null);

        Transaction transaction = new Transaction(UUID.randomUUID().toString(), type, amount, category, selectedBudget.id, LocalDateTime.now().toString(), LocalDateTime.now().toString(), name, userId);
        transactionService.addTransaction(transaction, transactions);
    }

    private void listTransactions(String userId) {
        ArrayList<Transaction> userTransactions = transactionService.listTransactions(userId, transactions);
        int id = 1;
        if (userTransactions.isEmpty()) {
            System.out.println("No transactions found for this user.");
        } else {
            for (Transaction transaction : userTransactions) {
                System.out.printf("%-10s %-20s %-20s %-12s\n", "No.", "Name", "Type", "Transaction ID");
                System.out.printf("%-10s %-20s %-20s %-12s\n", id, transaction.getName(), transaction.getType(), transaction.getId());
                id++;
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

        Transaction updatedTransaction = new Transaction(transactionId, type, amount, category, oldTransaction.getCategoryId(), oldTransaction.getCreatedAt(), LocalDateTime.now().toString(), name, userId);
        transactionService.editTransaction(transactionId, updatedTransaction, transactions);
    }

    private void deleteTransaction(String userId) {
        String transactionId = validateService.inputString("Enter transaction ID to delete: ", null);
        transactionService.deleteTransaction(transactionId, transactions);
    }

    private void displayDailyExpenditureChart(String userId) {
        List<Transaction> userTransactions = transactionService.listTransactions(userId, transactions);
        List<String> dates = new ArrayList<>();
        List<Double> amounts = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");

        for (Transaction transaction : userTransactions) {
            if (transaction.getType().equalsIgnoreCase("expense")) {
                String dateTimeString = transaction.getCreatedAt();
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                String date = dateTime.toLocalDate().toString();
                if (!dates.contains(date)) {
                    dates.add(date);
                    amounts.add(transaction.getAmount());
                } else {
                    int index = dates.indexOf(date);
                    amounts.set(index, amounts.get(index) + transaction.getAmount());
                }
            }
        }

        for (int i = 0; i < dates.size(); i++) {
            String date = dates.get(i);
            double total = amounts.get(i);
            int numHashes = (int) (total / 10); // Adjust this value for different scales

            System.out.print(date + ": ");
            for (int j = 0; j < numHashes; j++) {
                System.out.print("#");
            }
            System.out.println(" (" + total + ")");
        }
    }
}
