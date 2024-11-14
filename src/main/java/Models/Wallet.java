package Models;
import java.time.LocalDateTime;

public class Wallet {
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    public int walletId;
    public int userId;
    public double balance;
    public Wallet (int walletId, int userId, double balance) {
        this.walletId = walletId;
        this.userId = userId;
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
     public int getWalletId() {
        return walletId;
     }
     public void setWalletId(int walletId) {
        this.walletId = walletId;
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
}
