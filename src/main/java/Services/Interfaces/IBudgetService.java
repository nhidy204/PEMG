package Services.Interfaces;

import Models.Budget;
import Models.Wallet;
import java.util.ArrayList;

public interface IBudgetService {

    void addBudget(Budget budget, ArrayList<Budget> budgets);

    ArrayList<Budget> listBudgets(String userId, ArrayList<Budget> budgets);
    void editBudget(String budgetId, Budget updatedBudget, ArrayList<Budget> budgets);
    void deleteBudget(String budgetId, ArrayList<Budget> budgets);
    void saveBudgets(ArrayList<Budget> budgets);
    ArrayList<Budget> loadBudgets();
}
