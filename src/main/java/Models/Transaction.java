package Models;

public class Transaction {
    public int transactionId;
    public int userId;
    public double amount;
    public String type;
    public Integer targetId;

    public Transaction(int transactionId, int userId, double amount, String type, Integer targetId) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.targetId = targetId;
    }
    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
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
}

