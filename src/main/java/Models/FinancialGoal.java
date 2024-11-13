package Models;

public class FinancialGoal {
    public String goalName;
    public double targetAmount;
    public double currentAmount;

    public FinancialGoal(String goalName, double targetAmount) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentAmount = 0;
    }

    public void addContribution(double amount) {
        currentAmount += amount;
    }

    public boolean isGoalReached() {
        return currentAmount >= targetAmount;
    }

    // Getters
    public String getGoalName() {
        return goalName;
    }

    public double getProgress() {
        return (currentAmount / targetAmount) * 100;
    }
}

