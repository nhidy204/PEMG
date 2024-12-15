package Services.Interfaces;

import Models.Budget;
import java.util.ArrayList;

public interface IBudgetService {
    void addBudget(Budget budget, ArrayList<Budget> budgets);
    ArrayList<Budget> listBudgets(ArrayList<Budget> budgets);
    void editBudget(String budgetId, Budget updatedBudget, ArrayList<Budget> budgets);
    void deleteBudget(String budgetId, ArrayList<Budget> budgets);
    void saveBudgets(ArrayList<Budget> budgets);
    ArrayList<Budget> loadBudgets();

    Budget getBudgetByExpenseTargetId(String expenseTargetId);
}
