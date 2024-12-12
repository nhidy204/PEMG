package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Wallet {
    public UUID id;
    public String createdAt;
    public String updatedAt;
    public double balance;
    public UUID userId;

    public Wallet(double v) {
        this.balance = 1000;
    }

    public void addIncome(double amount) {
        this.balance += amount;
    }

    public boolean subtractExpense(double amount) {
        this.balance -= amount;
        return false;
    }

    public Wallet(LocalDateTime createdAt, LocalDateTime updatedAt, UUID id, UUID userId, double balance) {
        this.createdAt = createdAt.toString();
        this.updatedAt = updatedAt.toString();
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "Wallet balance: $" + balance;
    }
}
