package Services;

import Constants.FileConstants;
import Models.Transaction;
import Models.Wallet;
import Models.Budget;
import Services.Interfaces.ITransactionService;
import Services.Interfaces.IWalletService;
import Services.Interfaces.IBudgetService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TransactionService implements ITransactionService {
    private static TransactionService instance;
    private final String FILE_PATH = FileConstants.ROOT_PATH + "/" + FileConstants.TRANSACTION_FILE_NAME;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final IWalletService walletService = WalletService.getInstance();
    private final IBudgetService budgetService = BudgetService.getInstance();

    private TransactionService() {}

    public static TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionService();
        }
        return instance;
    }

    @Override
    public void addTransaction(Transaction transaction, ArrayList<Transaction> transactions) {
        if (transaction.getType().equalsIgnoreCase("income")) {
            Wallet wallet = walletService.getWalletById(transaction.getUserId());
            if (wallet != null) {
                wallet.setBalance(wallet.getBalance() + transaction.getAmount());
                walletService.saveWallets(walletService.loadWallets());
            }
        } else if (transaction.getType().equalsIgnoreCase("expense")) {
            Budget budget = budgetService.getBudgetByExpenseTargetId(transaction.getExpenseTargetId());
            if (budget != null) {
                double totalExpense = transactions.stream()
                        .filter(t -> t.getExpenseTargetId().equals(transaction.getExpenseTargetId()))
                        .mapToDouble(Transaction::getAmount)
                        .sum();
                if (totalExpense + transaction.getAmount() > budget.getMaximumAmount()) {
                    System.out.println("Transaction exceeds budget limit. Cannot create transaction.");
                    return;
                } else {
                    Wallet wallet = walletService.getWalletById(transaction.getUserId());
                    if (wallet != null) {
                        wallet.setBalance(wallet.getBalance() - transaction.getAmount());
                        walletService.saveWallets(walletService.loadWallets());
                    }
                }
            }
        }
        transactions.add(transaction);
        saveTransactions(transactions);
        System.out.println("Transaction added successfully.");
    }

    @Override
    public ArrayList<Transaction> listTransactions(ArrayList<Transaction> transactions) {
        return transactions;
    }

    @Override
    public void editTransaction(String transactionId, Transaction updatedTransaction, ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(transactionId)) {
                transaction.setType(updatedTransaction.getType());
                transaction.setAmount(updatedTransaction.getAmount());
                transaction.setCategory(updatedTransaction.getCategory());
                transaction.setExpenseTargetId(updatedTransaction.getExpenseTargetId());
                transaction.setUpdatedAt(updatedTransaction.getUpdatedAt());
                transaction.setName(updatedTransaction.getName());
                saveTransactions(transactions);
                System.out.println("Transaction updated successfully.");
                return;
            }
        }
        System.out.println("Transaction not found.");
    }

    @Override
    public void deleteTransaction(String transactionId, ArrayList<Transaction> transactions) {
        transactions.removeIf(transaction -> transaction.getId().equals(transactionId));
        saveTransactions(transactions);
        System.out.println("Transaction deleted successfully.");
    }

    @Override
    public void saveTransactions(ArrayList<Transaction> transactions) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(transactions, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Transaction> loadTransactions() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Transaction[] transactionsArray = gson.fromJson(reader, Transaction[].class);
            return new ArrayList<>(Arrays.asList(transactionsArray));
        } catch (FileNotFoundException e) {
            System.out.println("Transaction file not found. Creating a new one.");
            return new ArrayList<>();
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
