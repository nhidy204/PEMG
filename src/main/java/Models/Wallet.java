package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Wallet {
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    public final UUID id;
    public int userId;
    public double balance;
    public Wallet (int walletId, UUID id, int userId, double balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
     public int getUserId() {
        return userId;
     }
     public void setUserId(int userId) {
        this.userId = userId;
     }
     public double getBalance() {
        return balance;
     }
     public void setBalance(double balance) {
        this.balance = balance;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
     }
     public LocalDateTime getCreatedAt() {
        return createdAt;
     }
     public LocalDateTime getUpdatedAt() {
        return updatedAt;
     }
     public UUID getId() {
        return id;
     }
}
