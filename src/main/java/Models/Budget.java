package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Budget {
    public UUID id;
    public UUID userId;
    public int expenseTargetId;
    public double maximumAmount;
    public String budgetName;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public Budget() {
    }

    public Budget (int budgetId, UUID id, UUID userId, int expenseTargetId, double maximumAmount, String budgetName) {
        this.id = UUID.randomUUID();
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
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
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


