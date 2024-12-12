package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Budget {
    public UUID id;
    public UUID userId;
    public int expenseTargetId;
    public double maximumAmount;
    public String budgetName;
    public String createdAt;
    public String updatedAt;
    public String expenseTarget;

    public Budget() {
    }

    public Budget (int budgetId, UUID id, UUID userId, int expenseTargetId, double maximumAmount, String budgetName, String expenseTarget) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.expenseTargetId = expenseTargetId;
        this.maximumAmount = maximumAmount;
        this.budgetName = budgetName;
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
        this.expenseTarget = expenseTarget;
    }

    public Budget(String budgetName, double budgetAmount) {
        this.budgetName = budgetName;
        this.maximumAmount = budgetAmount;
    }
    @Override
    public String toString() {
        return budgetName + " - $" + maximumAmount;
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
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getExpensexTarget() {
        return null;
    }
}


