package Models;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class User {
    public UUID id;
    public String password;
    public String name;
    public String createdAt;
    public String updatedAt;
    public String username;
    public Wallet wallet;
    public List<Budget> budgets;
    public List<Transaction> transactions;
    public List<FinancialGoal> financialGoals;

    public User() {
        this.budgets = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.financialGoals = new ArrayList<>();
    }

    public User(String name, String password) {
        this.id = UUID.randomUUID();
        this.password = password;
        this.name = name;
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
        this.wallet = new Wallet(0.0);
        this.budgets = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.financialGoals = new ArrayList<>();
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
        return "User ID: " + id + "\nUsername: " + username;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public List<FinancialGoal> getFinancialGoals() {
        return financialGoals;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    public void listBudgets() {
        if (budgets.isEmpty()) {
            System.out.println("No budgets available.");
        } else {
            System.out.println("Your Budgets:");
            for (int i = 0; i < budgets.size(); i++) {
                System.out.println((i + 1) + ". " + budgets.get(i));
            }
        }
    }
    public void addFinancialGoal(FinancialGoal goal) {
        if (goal != null) {
            goal.setUserId(this.id); // Liên kết userId
            financialGoals.add(goal); // Thêm vào danh sách
        }
    }


    public void listFinancialGoals() {
        if (financialGoals == null || financialGoals.isEmpty()) {
            System.out.println("No financial goals available.");
        } else {
            System.out.println("Your Financial Goals:");
            for (int i = 0; i < financialGoals.size(); i++) {
                System.out.println((i + 1) + ". " + financialGoals.get(i).toString());
            }
        }
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

}
