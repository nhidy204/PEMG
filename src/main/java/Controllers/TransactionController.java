package Controllers;

import Models.Transaction;
import Services.Interfaces.ITransactionService;
import Services.Interfaces.IValidateService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class TransactionController {
    private final ITransactionService transactionService;
    private final IValidateService validateService;
    private final ArrayList<Transaction> transactions;
    private final Scanner scanner = new Scanner(System.in);

    public TransactionController(ITransactionService transactionService, IValidateService validateService) {
        this.transactionService = transactionService;
        this.validateService = validateService;
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
        String type = validateService.inputString("Enter transaction type (income/expense): ", null);
        double amount = validateService.inputDouble("Enter transaction amount: ");
        String category = validateService.inputString("Enter transaction category: ", null);
        String expenseTargetId = null;
        if (type.equals("expense")) {
            expenseTargetId = validateService.inputString("Enter expense target ID: ", null);
        }
        String name = validateService.inputString("Enter transaction name: ", null);

        Transaction transaction = new Transaction(UUID.randomUUID().toString(), type, amount, category, expenseTargetId, LocalDateTime.now().toString(), LocalDateTime.now().toString(), name, userId);
        transactionService.addTransaction(transaction, transactions);
        saveTransactions();
    }

    private void listTransactions(String userId) {
        ArrayList<Transaction> transactionList = transactionService.listTransactions(userId, transactions);
        for (Transaction transaction : transactionList) {
            System.out.println("Transaction Name: " + transaction.getName() + ", Amount: " + transaction.getAmount() + ", ID: " + transaction.getId());
        }
    }

    private void editTransaction(String userId) {
        String id = validateService.inputString("Enter transaction ID to edit: ", null);
        String type = validateService.inputString("Enter new transaction type (income/expense): ", null);
        double amount = validateService.inputDouble("Enter new transaction amount: ");
        String category = validateService.inputString("Enter new transaction category: ", null);
        String expenseTargetId = null;
        if (type.equals("expense")) {
            expenseTargetId = validateService.inputString("Enter new expense target ID: ", null);
        }
        String name = validateService.inputString("Enter new transaction name: ", null);

        Transaction updatedTransaction = new Transaction(id, type, amount, category, expenseTargetId, LocalDateTime.now().toString(), LocalDateTime.now().toString(), name, userId);
        transactionService.editTransaction(id, updatedTransaction, transactions);
        saveTransactions();
    }

    private void deleteTransaction(String userId) {
        String id = validateService.inputString("Enter transaction ID to delete: ", null);
        transactionService.deleteTransaction(id, transactions);
        saveTransactions();
    }

    private void saveTransactions() {
        transactionService.saveTransactions(transactions);
    }
}
