package Services;

import Models.Transaction;
import Services.Interfaces.ITransactionService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionService implements ITransactionService {
    @Override
    public void addTransaction(Transaction transaction) {

    }

    @Override
    public List<Transaction> listTransactions() {
        return List.of();
    }

    @Override
    public void editTransaction(int id, Transaction updatedTransaction) {

    }

    @Override
    public void deleteTransaction(int id) {

    }
//    private final List<Transaction> transactions = new ArrayList<>();
//    private static TransactionService instance;
//
//    public TransactionService() {}
//
//    public static TransactionService getInstance() {
//        if (instance == null) {
//            instance = new TransactionService();
//        }
//        return instance;
//    }
//
//    @Override
//    public void addTransaction(Transaction transaction) {
//        transaction.setCreatedAt(LocalDateTime.now().toString());
//        transaction.setUpdatedAt(LocalDateTime.now().toString());
//        transactions.add(transaction);
//        System.out.println("Transaction added successfully!");
//    }
//
//
//    @Override
//    public List<Transaction> listTransactions() {
//        return transactions;
//    }
//
//    @Override
//    public void editTransaction(int id, Transaction updatedTransaction) {
//        for (Transaction transaction : transactions) {
//            if (transaction.getId() == id) {
//                updatedTransaction.setUpdatedAt(LocalDateTime.now().toString());
//                transaction.setType(updatedTransaction.getType());
//                transaction.setAmount(updatedTransaction.getAmount());
//                transaction.setCategory(updatedTransaction.getCategory());
//                transaction.setUpdatedAt(updatedTransaction.getUpdatedAt());
//                System.out.println("Transaction updated successfully!");
//                return;
//            }
//        }
//        System.out.println("Transaction not found!");
//    }
//
//    @Override
//    public void deleteTransaction(int id) {
//        transactions.removeIf(transaction -> transaction.getId() == id);
//        System.out.println("Transaction deleted successfully!");
//    }
}
