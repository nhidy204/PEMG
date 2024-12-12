package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class FinancialGoal {
    public UUID id;
    public UUID userId;
    public String name;
    public double targetAmount;
    public String createdAt;
    public String updatedAt;

    public FinancialGoal(String goalName, double goalAmount) {
        this.id = UUID.randomUUID(); // Tự động tạo UUID mới
        this.name = goalName;
        this.targetAmount = goalAmount;
        this.createdAt = LocalDateTime.now().toString(); // Gán thời gian hiện tại
        this.updatedAt = LocalDateTime.now().toString();
    }

    public FinancialGoal(int financialGoalID, UUID id, UUID userId, String name, double targetAmount) {
        this.id = id != null ? id : UUID.randomUUID(); // Sử dụng ID được truyền vào hoặc tự động tạo
        this.userId = userId;
        this.name = name;
        this.targetAmount = targetAmount;
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
    }
    @Override
    public String toString() {
        return name + " - Target: $" + targetAmount;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getTargetAmount() {
        return targetAmount;
    }
    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
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
}


