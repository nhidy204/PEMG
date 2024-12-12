package Services.Interfaces;

import Models.Transaction;
import Models.User;

public interface IBudgetService {
    boolean isTransactionExceedingBudget(User user, Transaction transaction);
}
