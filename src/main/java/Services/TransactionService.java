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
    private final IWalletService walletService;
    private final IBudgetService budgetService;

    private TransactionService(IWalletService walletService, IBudgetService budgetService) {
        this.walletService = walletService;
        this.budgetService = budgetService;
    }

    public static TransactionService getInstance(IWalletService walletService, IBudgetService budgetService) {
        if (instance == null) {
            instance = new TransactionService(walletService, budgetService);
        }
        return instance;
    }

    @Override
    public void addTransaction(Transaction transaction, ArrayList<Transaction> transactions) {
        Wallet wallet = walletService.getWalletByUserId(transaction.getUserId());
        if (wallet == null) {
            System.out.println("Wallet not found for user ID: " + transaction.getUserId());
            return;
        }

        if (transaction.getType().equals("income")) {
            double newBalance = wallet.getBalance() + transaction.getAmount();
            ArrayList<Wallet> currentWallets = walletService.loadWallets();
            for (Wallet currentWallet : currentWallets) {
                if (wallet.getId().equals(currentWallet.getId())) {
                    currentWallet.setBalance(newBalance);
                    break;
                }
            }
            walletService.saveWallets(currentWallets);
        } else if (transaction.getType().equals("expense")) {
            Budget budget = budgetService.getBudgetByExpenseTargetId(transaction.getExpenseTargetId());
            if (budget != null) {
                ArrayList<Transaction> userTransactions = listTransactions(transaction.getUserId(), transactions);
                double totalExpense = transaction.getAmount();
                for (Transaction t : userTransactions) {
                    if (t.getExpenseTargetId() != null && t.getExpenseTargetId().equals(transaction.getExpenseTargetId())) {
                        totalExpense += t.getAmount();
                    }
                }
                if (totalExpense > budget.getMaximumAmount()) {
                    System.out.println("Transaction exceeds budget limit. Cannot create transaction.");
                    return;
                } else {
                    double newBalance = wallet.getBalance() - transaction.getAmount();
                    ArrayList<Wallet> currentWallets = walletService.loadWallets();
                    for (Wallet currentWallet : currentWallets) {
                        if (wallet.getId().equals(currentWallet.getId())) {
                            currentWallet.setBalance(newBalance);
                            break;
                        }
                    }
                    walletService.saveWallets(currentWallets);
                }
            }
        }
        transactions.add(transaction);
        saveTransactions(transactions);
        System.out.println("Transaction added successfully.");
    }

    @Override
    public ArrayList<Transaction> listTransactions(String userId, ArrayList<Transaction> transactions) {
        ArrayList<Transaction> userTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getUserId().equals(userId)) {
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }

    @Override
    public void editTransaction(String transactionId, Transaction updatedTransaction, ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(transactionId)) {
                transaction.setType(updatedTransaction.getType());
                transaction.setAmount(updatedTransaction.getAmount());
                transaction.setCategory(updatedTransaction.getCategory());
                transaction.setExpenseTargetId(updatedTransaction.getExpenseTargetId());
                transaction.setName(updatedTransaction.getName());
                transaction.setUpdatedAt(updatedTransaction.getUpdatedAt());
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
            if (transactionsArray == null) {
                return new ArrayList<>();
            }
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
