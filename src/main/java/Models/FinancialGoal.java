package Models;

public class FinancialGoal {
    public int financialGoalID;
    public int userId;
    public String name;
    public double targetAmount;

    public FinancialGoal(int financialGoalID, int userId, String name, double targetAmount) {
        this.financialGoalID = financialGoalID;
        this.userId = userId;
        this.name = name;
        this.targetAmount = targetAmount;
    }
    public int getFinancialGoalID() {
        return financialGoalID;
    }
    public void setFinancialGoalID(int financialGoalID) {
        this.financialGoalID = financialGoalID;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getTargetAmount() {
        return targetAmount;
    }
    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }
}


