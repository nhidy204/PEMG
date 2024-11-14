package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    public UUID id;
    public UUID userId;
    public double amount;
    public String type;
    public Integer targetId;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public Transaction() {
    }

    public Transaction(int transactionId, UUID id, UUID userId, double amount, String type, Integer targetId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.targetId = targetId;
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
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Integer getTargetId() {
        return targetId;
    }
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

