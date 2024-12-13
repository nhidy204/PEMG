package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class ExpenseTarget {
    public String id;
    public String name;
    public String userId;
    public String createdAt;
    public String updatedAt;

    public ExpenseTarget() {
    }

    public ExpenseTarget(String id, String userId, String name, String createdAt, String updatedAt) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.name = name;
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
