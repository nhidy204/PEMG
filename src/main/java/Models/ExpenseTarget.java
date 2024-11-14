package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class ExpenseTarget {
    public UUID id;
    public String name;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public ExpenseTarget() {
    }

    public ExpenseTarget(int expenseTargetId, UUID id, String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public UUID getId() {
        return id;
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
