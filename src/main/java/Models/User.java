package Models;

import java.time.LocalDateTime;

public class User {
    public String userId;
    public String email;
    public String password;
    public String name;
    public final LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public User(int userId, String email, String name, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + "\nEmail: " + email + "\nName: " + name;
    }
}
