package Models;

public class Budget {
    public int budgetId;
    public int userId;
    public int expenseTargetId;
    public double maximumAmount;
    public String budgetName;

    public Budget (int budgetId, int userId, int expenseTargetId, double maximumAmount, String budgetName) {
        this.budgetId = budgetId;
        this.userId = userId;
        this.expenseTargetId = expenseTargetId;
        this.maximumAmount = maximumAmount;
        this.budgetName = budgetName;
    }
    public int getBudgetId() {
        return budgetId;
    }
    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getExpenseTargetId() {
        return expenseTargetId;
    }
    public void setExpenseTargetId(int expenseTargetId) {
        this.expenseTargetId = expenseTargetId;
    }
    public double getMaximumAmount() {
        return maximumAmount;
    }
    public void setMaximumAmount(double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }
    public String getBudgetName() {
        return budgetName;
    }
    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }
}


