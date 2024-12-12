package Services.Interfaces;
import Models.Transaction;
import java.util.List;

public interface ITransactionService {
    void addTransaction(Transaction transaction); // Thêm giao dịch
    List<Transaction> listTransactions();        // Danh sách giao dịch
    void editTransaction(int id, Transaction updatedTransaction); // Sửa giao dịch
    void deleteTransaction(int id);              // Xóa giao dịch
}
