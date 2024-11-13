package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    public final String username;
    public final String password;
    public double balance;
    public final List<Transaction> transactions;
    public final LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
        //time
        this.updatedAt = LocalDateTime.now();
    }

    public void addTransaction(String type, double amount, String category) {
        Transaction transaction = new Transaction(type, amount, category);
        transactions.add(transaction);
        this.updatedAt = LocalDateTime.now(); // Update the timestamp

        if (type.equalsIgnoreCase("Income")) {
            balance += amount;
        } else if (type.equalsIgnoreCase("Expense")) {
            balance -= amount;
        }
        System.out.println("Transaction added: " + transaction);
    }

    public void listTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Listing all transactions:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
