package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Wallet {
    public String id;
    public String createdAt;
    public String updatedAt;
    public double balance;
    public String userId;

    public Wallet() {
    }

    public Wallet(String id, String createdAt, String updatedAt, double balance, String userId) {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now().toString();;
        this.updatedAt = LocalDateTime.now().toString();;
        this.balance = balance;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
