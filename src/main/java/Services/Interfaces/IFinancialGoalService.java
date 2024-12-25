package Services.Interfaces;

import Models.FinancialGoal;
import java.util.ArrayList;

public interface IFinancialGoalService {
    void addFinancialGoal(FinancialGoal financialGoal, ArrayList<FinancialGoal> financialGoals);
    ArrayList<FinancialGoal> listFinancialGoals(String userId, ArrayList<FinancialGoal> financialGoals);
    void editFinancialGoal(String financialGoalId, FinancialGoal updatedFinancialGoal, ArrayList<FinancialGoal> financialGoals);
    void deleteFinancialGoal(String financialGoalId, ArrayList<FinancialGoal> financialGoals);
    void saveFinancialGoals(ArrayList<FinancialGoal> financialGoals);
    ArrayList<FinancialGoal> loadFinancialGoals();
    void checkGoalProgress(String userId, double walletBalance, ArrayList<FinancialGoal> financialGoals);
}
