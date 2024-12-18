package Models;
import java.time.LocalDateTime;
import java.util.UUID;

public class FinancialGoal {
    public String id; //id financial
    public String goalName;
    public double goalTarget;
    public String createdAt;
    public String updatedAt;
    public String userId;



    public FinancialGoal() {
    }

    public FinancialGoal(String id, String goalName, double goalTarget, String createdAt, String updatedAt, String userId) {
        this.id = UUID.randomUUID().toString();
        this.goalName = goalName;
        this.goalTarget = goalTarget;
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public double getGoalTarget() {
        return goalTarget;
    }

    public void setGoalTarget(double goalTarget) {
        this.goalTarget = goalTarget;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
