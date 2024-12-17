package Services.Interfaces;

import Models.ExpenseTarget;

import java.util.ArrayList;

public interface IExpenseTargetService {
    void addExpenseTarget(ExpenseTarget expenseTarget, ArrayList<ExpenseTarget> expenseTargets);
    ArrayList<ExpenseTarget> listExpenseTargetsByUserId(String userId, ArrayList<ExpenseTarget> expenseTargets); // Thêm phương thức này
    void editExpenseTarget(String userId, String expenseTargetId, ExpenseTarget updatedExpenseTarget, ArrayList<ExpenseTarget> expenseTargets);
    void deleteExpenseTarget(String userId, String expenseTargetId, ArrayList<ExpenseTarget> expenseTargets);
    void saveExpenseTargets(ArrayList<ExpenseTarget> expenseTargets);
    ArrayList<ExpenseTarget> loadExpenseTargets();
}
