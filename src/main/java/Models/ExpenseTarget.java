package Models;
import java.time.LocalDateTime;

public class ExpenseTarget {
    public int expenseTargetId;
    public String name;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    public ExpenseTarget(int expenseTargetId, String name) {
        this.expenseTargetId = expenseTargetId;
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public int getExpenseTargetId() {
        return expenseTargetId;
    }
    public void setExpenseTargetId(int expenseTargetId) {
        this.expenseTargetId = expenseTargetId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
