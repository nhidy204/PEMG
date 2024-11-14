package Models;
import java.time.LocalDateTime;

public class FinancialGoal {
    public int financialGoalID;
    public int userId;
    public String name;
    public double targetAmount;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public FinancialGoal(int financialGoalID, int userId, String name, double targetAmount) {
        this.financialGoalID = financialGoalID;
        this.userId = userId;
        this.name = name;
        this.targetAmount = targetAmount;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}


