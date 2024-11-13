package Models;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    public String userName;
    public String password;
    public List<Transaction> transactions = new ArrayList<>();
    public Budget budget;
    public List<FinancialGoal> goals = new ArrayList<>();

    // Constructor, getters, and setters
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.budget = new Budget();
    }

    public String getUserName() {
        return userName;
    }

    public boolean loginUser(String password) {
        return this.password.equals(password);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Save user data to JSON
    public void saveUserData() {
        try (FileWriter writer = new FileWriter(userName + ".json")) {
            Gson gson = new Gson();
            gson.toJson(this, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Load user data from JSON
    public static User loadUserData(String userName) {
        try (FileReader reader = new FileReader(userName + ".json")) {
           Gson gson = new Gson();
           return gson.fromJson(reader, User.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

