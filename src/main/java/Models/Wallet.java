package Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Wallet {
    private String id;
    private String createdAt;
    private String updatedAt;
    private double balance;
    private String userId;
    private String name;

    public Wallet(String string, String name, double balance, String s, String userId, String id) {
    }

    public Wallet(String id, String createdAt, String updatedAt, double balance, String userId, String name) {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
        this.balance = balance;
        this.userId = userId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
