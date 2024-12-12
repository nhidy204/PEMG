package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class ExpenseTarget {
    public UUID id;
    public String name;
    public String createdAt;
    public String updatedAt;

    public ExpenseTarget() {
    }

    public ExpenseTarget(int expenseTargetId, UUID id, String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
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
