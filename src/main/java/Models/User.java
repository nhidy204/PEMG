package Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    public UUID id;
    public String password;
    public String name;
    public String createdAt;
    public String updatedAt;
    private Object username;

    public User() {
    }

    public User(String name, String password) {
        this.id = UUID.randomUUID();
        this.password = password;
        this.name = name;
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
    }
    public UUID getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "User ID: " + id + "\nName: " + name;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }
}
