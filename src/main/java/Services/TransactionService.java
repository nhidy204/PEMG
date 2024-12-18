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
            System.out.println("No wallet found for this user. Cannot create transaction.");
            return;
        }

        ArrayList<Wallet> wallets = walletService.loadWallets();
        ArrayList<Budget> budgets = budgetService.loadBudgets();
        Budget selectedBudget = null;

        for (Budget budget : budgets) {
            if (budget.getId().equals(transaction.getCategoryId()) && budget.getUserId().equals(transaction.getUserId())) {
                selectedBudget = budget;
                break;
            }
        }

        if (selectedBudget == null) {
            System.out.println("No matching budget found for this user. Cannot create transaction.");
            return;
        }

        if (transaction.getType().equals("income")) {
            wallet.setBalance(wallet.getBalance() + transaction.getAmount());
            selectedBudget.setMaximumAmount(selectedBudget.getMaximumAmount() + transaction.getAmount());
        } else if (transaction.getType().equals("expense")) {
            if (transaction.getAmount() > wallet.getBalance() || transaction.getAmount() > selectedBudget.getMaximumAmount()) {
                System.out.println("Transaction exceeds wallet or budget balance. Cannot create transaction.");
                return;
            } else {
                wallet.setBalance(wallet.getBalance() - transaction.getAmount());
                selectedBudget.setMaximumAmount(selectedBudget.getMaximumAmount() - transaction.getAmount());
            }
        }

        for (int i = 0; i < wallets.size(); i++) {
            if (wallets.get(i).getId().equals(wallet.getId())) {
                wallets.set(i, wallet);
                break;
            }
        }

        for (int i = 0; i < budgets.size(); i++) {
            if (budgets.get(i).getId().equals(selectedBudget.getId())) {
                budgets.set(i, selectedBudget);
                break;
            }
        }

        walletService.saveWallets(wallets);
        budgetService.saveBudgets(budgets);

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
        Wallet wallet = walletService.getWalletByUserId(updatedTransaction.getUserId());
        ArrayList<Wallet> wallets = walletService.loadWallets();
        ArrayList<Budget> budgets = budgetService.loadBudgets();
        Budget selectedBudget = null;

        for (Budget budget : budgets) {
            if (budget.getId().equals(updatedTransaction.getCategoryId()) && budget.getUserId().equals(updatedTransaction.getUserId())) {
                selectedBudget = budget;
                break;
            }
        }

        if (wallet == null || selectedBudget == null) {
            System.out.println("No wallet or budget found for this user. Cannot edit transaction.");
            return;
        }

        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(transactionId)) {
                // Revert wallet and budget changes from the original transaction
                if (transaction.getType().equals("income")) {
                    wallet.setBalance(wallet.getBalance() - transaction.getAmount());
                    selectedBudget.setMaximumAmount(selectedBudget.getMaximumAmount() - transaction.getAmount());
                } else if (transaction.getType().equals("expense")) {
                    wallet.setBalance(wallet.getBalance() + transaction.getAmount());
                    selectedBudget.setMaximumAmount(selectedBudget.getMaximumAmount() + transaction.getAmount());
                }

                // Apply wallet and budget changes from the updated transaction
                if (updatedTransaction.getType().equals("income")) {
                    wallet.setBalance(wallet.getBalance() + updatedTransaction.getAmount());
                    selectedBudget.setMaximumAmount(selectedBudget.getMaximumAmount() + updatedTransaction.getAmount());
                } else if (updatedTransaction.getType().equals("expense")) {
                    if (updatedTransaction.getAmount() > wallet.getBalance() || updatedTransaction.getAmount() > selectedBudget.getMaximumAmount()) {
                        System.out.println("Transaction exceeds wallet or budget balance. Cannot edit transaction.");
                        return;
                    } else {
                        wallet.setBalance(wallet.getBalance() - updatedTransaction.getAmount());
                        selectedBudget.setMaximumAmount(selectedBudget.getMaximumAmount() - updatedTransaction.getAmount());
                    }
                }

                transaction.setType(updatedTransaction.getType());
                transaction.setAmount(updatedTransaction.getAmount());
                transaction.setCategory(updatedTransaction.getCategory());
                transaction.setName(updatedTransaction.getName());
                transaction.setUpdatedAt(updatedTransaction.getUpdatedAt());
                saveTransactions(transactions);

                // Update the wallet and budget in the list
                for (int i = 0; i < wallets.size(); i++) {
                    if (wallets.get(i).getId().equals(wallet.getId())) {
                        wallets.set(i, wallet);
                        break;
                    }
                }

                for (int i = 0; i < budgets.size(); i++) {
                    if (budgets.get(i).getId().equals(selectedBudget.getId())) {
                        budgets.set(i, selectedBudget);
                        break;
                    }
                }

                walletService.saveWallets(wallets);
                budgetService.saveBudgets(budgets);

                System.out.println("Transaction updated successfully.");
                return;
            }
        }
        System.out.println("Transaction not found.");
    }

    @Override
    public void deleteTransaction(String transactionId, ArrayList<Transaction> transactions) {
        Wallet wallet = null;
        Budget selectedBudget = null;
        Transaction deletedTransaction = null;

        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(transactionId)) {
                wallet = walletService.getWalletByUserId(transaction.getUserId());
                ArrayList<Budget> budgets = budgetService.loadBudgets();
                for (Budget budget : budgets) {
                    if (budget.getId().equals(transaction.getCategoryId()) && budget.getUserId().equals(transaction.getUserId())) {
                        selectedBudget = budget;
                        break;
                    }
                }
                deletedTransaction = transaction;
                break;
            }
        }

        if (wallet == null || selectedBudget == null || deletedTransaction == null) {
            System.out.println("No wallet, budget, or transaction found. Cannot delete transaction.");
            return;
        }

        // Revert wallet and budget changes from the deleted transaction
        if (deletedTransaction.getType().equals("income")) {
            wallet.setBalance(wallet.getBalance() - deletedTransaction.getAmount());
            selectedBudget.setMaximumAmount(selectedBudget.getMaximumAmount() - deletedTransaction.getAmount());
        } else if (deletedTransaction.getType().equals("expense")) {
            wallet.setBalance(wallet.getBalance() + deletedTransaction.getAmount());
            selectedBudget.setMaximumAmount(selectedBudget.getMaximumAmount() + deletedTransaction.getAmount());
        }

        transactions.remove(deletedTransaction);
        saveTransactions(transactions);

        // Update the wallet and budget in the list
        ArrayList<Wallet> wallets = walletService.loadWallets();
        for (int i = 0; i < wallets.size(); i++) {
            if (wallets.get(i).getId().equals(wallet.getId())) {
                wallets.set(i, wallet);
                break;
            }
        }

        ArrayList<Budget> budgets = budgetService.loadBudgets();
        for (int i = 0; i < budgets.size(); i++) {
            if (budgets.get(i).getId().equals(selectedBudget.getId())) {
                budgets.set(i, selectedBudget);
                break;
            }
        }

        walletService.saveWallets(wallets);
        budgetService.saveBudgets(budgets);

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
