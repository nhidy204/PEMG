package Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    public String id;
    public String type;
    public double amount;
    public String category;
    public String expenseTargetId;
    public String createdAt;
    public String updatedAt;
    public String name;
    public String userId;

    public Transaction() {
    }

    public Transaction(String id, String type, double amount, String category, String expenseTargetId, String createdAt, String updatedAt, String name,String userId) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.expenseTargetId = expenseTargetId;
        this.createdAt = LocalDateTime.now().toString();;
        this.updatedAt = LocalDateTime.now().toString();;
        this.name = name;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExpenseTargetId() {
        return expenseTargetId;
    }

    public void setExpenseTargetId(String expenseTargetId) {
        this.expenseTargetId = expenseTargetId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
