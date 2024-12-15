package Services.Interfaces;

import Models.FinancialGoal;
import java.util.ArrayList;

public interface IFinancialGoalService {
    void addFinancialGoal(FinancialGoal financialGoal, ArrayList<FinancialGoal> financialGoals);
    ArrayList<FinancialGoal> listFinancialGoals(ArrayList<FinancialGoal> financialGoals);
    void editFinancialGoal(String financialGoalId, FinancialGoal updatedFinancialGoal, ArrayList<FinancialGoal> financialGoals);
    void deleteFinancialGoal(String financialGoalId, ArrayList<FinancialGoal> financialGoals);
    void saveFinancialGoals(ArrayList<FinancialGoal> financialGoals);
    ArrayList<FinancialGoal> loadFinancialGoals();
    FinancialGoal getFinancialGoalById(String financialGoalId);
    double getWalletBalanceByUserId(String userId);
}
