package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Budget {
    public final UUID id;
    public int userId;
    public int expenseTargetId;
    public double maximumAmount;
    public String budgetName;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public Budget (int budgetId, UUID id, int userId, int expenseTargetId, double maximumAmount, String budgetName) {
        this.id = id;
        this.userId = userId;
        this.expenseTargetId = expenseTargetId;
        this.maximumAmount = maximumAmount;
        this.budgetName = budgetName;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public UUID getId() {
        return id;
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


