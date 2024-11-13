package Models;

import java.time.LocalDateTime;

public class Transaction {
    public String type;
    public double amount;
    public String category;
    public final LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public Transaction(String type, double amount, String category) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        //time
        this.updatedAt = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        //time
        this.updatedAt = LocalDateTime.now();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        //time
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Transaction{" + "type='" + type + '\'' + ", amount=" + amount + ", category='" + category + '\'' + '}';
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
