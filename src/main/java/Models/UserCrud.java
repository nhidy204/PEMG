package Models;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;

public class UserCrud {
    public static final String DIRECTORY_PATH = "users";
    static {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    public static void createUser(User user) throws IOException {
        String filePath = DIRECTORY_PATH + "/User_" + user.getUserId() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(user.toString());
        }
        System.out.println("User created successfully");
    }
    public static User readUser(int userId) throws IOException {
        String filePath = DIRECTORY_PATH + "/User_" + userId + ".txt";
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("User not found");
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int id = Integer.parseInt(reader.readLine().split(",")[1]);
            String email = reader.readLine().split(",")[1];
            String name = reader.readLine().split(",")[1];
            String password = reader.readLine().split(",")[1];
            return new User(id, email, password, name);
        }
    }
    public static void updateUser(User updatedUser) throws IOException {
        String filePath = DIRECTORY_PATH + "/User_" + updatedUser.getUserId() + ".txt";
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("User not found");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(updatedUser.toString());
        }
        System.out.println("User updated successfully");
    }
    public static void deleteUser(int userId) throws IOException {
        String filePath = DIRECTORY_PATH + "/User_" + userId + ".txt";
        File file = new File(filePath);

        if (file.delete()) {
            System.out.println("User deleted successfully");
        } else {
            System.out.println("User not found or could not be deleted");
        }
    }
    public static void listALLUsers() throws IOException {
        File directory = new File(DIRECTORY_PATH);
        File[] files = directory.listFiles((dir, name) -> name.startsWith("User_") && name.endsWith(".txt"));

        if (files != null && files.length > 0) {
            for (File file : files) {
                System.out.println("User file: " + file.getName());
            }
        } else {
            System.out.println("Users not found");
        }
    }
}
