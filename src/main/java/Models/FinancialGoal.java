package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class FinancialGoal {
    public String id; //id financial
    public String userId; // userid
    public String name;  //ten goal
    public double targetAmount;  //so tien da dat duoc
    public String createdAt; //thoi gian tao goal
    public String updatedAt; //thoi gian sua goal

    public FinancialGoal() {
    }

    public FinancialGoal(String id, String userId, String name, double targetAmount, String createdAt, String updatedAt) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.name = name;
        this.targetAmount = targetAmount;
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

