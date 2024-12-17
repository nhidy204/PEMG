package Models;

import java.util.UUID;

public class ExpenseTarget {
    private String id;
    private String name;
    private String userId; // Thêm thuộc tính userId

    public ExpenseTarget(String id, String name, String userId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.userId = userId; // Đặt giá trị cho userId
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
