package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Budget {
    public String id;
    public String budgetName;
    public double maximumAmount;
    public String createdAt;
    public String updatedAt;
    public String userId;

    public Budget() {
    }

    public Budget(String id, String budgetName, double maximumAmount, String createdAt, String updatedAt, String userId) {
        this.id = UUID.randomUUID().toString();
        this.budgetName = budgetName;
        this.maximumAmount = maximumAmount;
        this.createdAt = LocalDateTime.now().toString();;
        this.updatedAt = LocalDateTime.now().toString();;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public double getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(double maximumAmount) {
        this.maximumAmount = maximumAmount;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}


