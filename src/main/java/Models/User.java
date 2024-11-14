package Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    public UUID id;
    public String email;
    public String password;
    public String name;
    public  LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public User() {
    }

    public User(UUID id, String email, String name, String password) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public UUID getId() {
        return id;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "User ID: " + id + "\nEmail: " + email + "\nName: " + name;
    }
}
