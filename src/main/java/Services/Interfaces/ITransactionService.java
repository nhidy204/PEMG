package Services.Interfaces;

import Models.Transaction;
import java.util.ArrayList;

public interface ITransactionService {
    void addTransaction(Transaction transaction, ArrayList<Transaction> transactions);
    ArrayList<Transaction> listTransactions(ArrayList<Transaction> transactions);
    void editTransaction(String transactionId, Transaction updatedTransaction, ArrayList<Transaction> transactions);
    void deleteTransaction(String transactionId, ArrayList<Transaction> transactions);
    void saveTransactions(ArrayList<Transaction> transactions);
    ArrayList<Transaction> loadTransactions();
}
