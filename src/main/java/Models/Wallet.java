package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Wallet {
    public UUID id;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    public double balance;
    public UUID userId;

    public Wallet() {
    }

    public Wallet(LocalDateTime createdAt, LocalDateTime updatedAt, UUID id, UUID userId, double balance) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
