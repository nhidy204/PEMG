package Services;

import Models.Budget;
import Models.Transaction;
import Models.User;
import Services.Interfaces.IBudgetService;

import java.util.Optional;

public class BudgetService implements IBudgetService {

    @Override
    public boolean isTransactionExceedingBudget(User user, Transaction transaction) {
        // Lấy danh sách ngân sách từ User
        Optional<Budget> matchingBudget = user.getBudgets().stream()
                .filter(budget -> budget.getExpensexTarget().equals(transaction.getExpenseTarget()))
                .findFirst();

        if (matchingBudget.isPresent()) {
            // Tính tổng giao dịch liên quan đến ExpenseTarget
            double totalExpenses = user.getTransactions().stream()
                    .filter(t -> t.getExpenseTarget().equals(transaction.getExpenseTarget()))
                    .mapToDouble(Transaction::getAmount)
                    .sum() + transaction.getAmount();

            // So sánh với MaximumAmount
            return totalExpenses > matchingBudget.get().getMaximumAmount();
        }
        return false;
    }
}
