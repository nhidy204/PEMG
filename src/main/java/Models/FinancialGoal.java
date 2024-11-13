package Models;

import java.time.LocalDateTime;

public class FinancialGoal {
    public String goalName;
    public double targetAmount;
    public double currentAmount;
    public final LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public FinancialGoal(String goalName, double targetAmount) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentAmount = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void addContribution(double amount) {
        if (amount > 0) {
            currentAmount += amount;
            //time
            this.updatedAt = LocalDateTime.now();
        } else {
            System.out.println("Contribution amount must be positive.");
        }
    }

    public boolean isGoalReached() {
        return currentAmount >= targetAmount;
    }

    //get set
    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
        //time
        this.updatedAt = LocalDateTime.now();
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
        //time
        this.updatedAt = LocalDateTime.now();
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
        //time
        this.updatedAt = LocalDateTime.now();
    }

    public double getProgress() {
        if (targetAmount == 0) {
            return 0;
        }
        return (currentAmount / targetAmount) * 100;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
