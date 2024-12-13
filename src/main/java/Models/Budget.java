package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Budget {
    public String id;
    public String userId;
    public String expenseTargetId;
    public double maximumAmount;
    public String budgetName;
    public String createdAt;
    public String updatedAt;

    public Budget() {
    }

    public Budget(String id, String userId, String expenseTargetId, double maximumAmount, String budgetName, String createdAt, String updatedAt) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.expenseTargetId = expenseTargetId;
        this.maximumAmount = maximumAmount;
        this.budgetName = budgetName;
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpenseTargetId() {
        return expenseTargetId;
    }

    public void setExpenseTargetId(String expenseTargetId) {
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

}


