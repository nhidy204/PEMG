package Models;

import java.time.LocalDateTime;

public class Budget {
    public final String[] categories;
    public final double[] categoryBudgets;
    public final double[] categorySpending;
    public final LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public Budget(String[] categories) {
        this.categories = categories;
        this.categoryBudgets = new double[categories.length];
        this.categorySpending = new double[categories.length];
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setBudget(String category, double amount) {
        int index = getCategoryIndex(category);
        if (index != -1) {
            categoryBudgets[index] = amount;
            this.updatedAt = LocalDateTime.now(); // Update the timestamp
            System.out.println("Budget set for " + category + ": $" + amount);
        } else {
            System.out.println("Category not found.");
        }
    }

    public void addSpending(String category, double amount) {
        int index = getCategoryIndex(category);
        if (index != -1) {
            categorySpending[index] += amount;
            this.updatedAt = LocalDateTime.now(); // Update the timestamp
            System.out.println("Added $" + amount + " spending in category " + category);
        } else {
            System.out.println("Category not found.");
        }
    }

    public boolean isOverBudget(String category) {
        int index = getCategoryIndex(category);
        if (index != -1) {
            return categorySpending[index] > categoryBudgets[index];
        } else {
            System.out.println("Category not found.");
            return false;
        }
    }

    public double getRemainingBudget(String category) {
        int index = getCategoryIndex(category);
        if (index != -1) {
            return categoryBudgets[index] - categorySpending[index];
        } else {
            System.out.println("Category not found.");
            return 0;
        }
    }

    private int getCategoryIndex(String category) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equalsIgnoreCase(category)) {
                return i;
            }
        }
        return -1;
    }

    public void printBudgets() {
        for (int i = 0; i < categories.length; i++) {
            System.out.println(categories[i] + " Budget: $" + categoryBudgets[i] + ", Spending: $" + categorySpending[i]);
        }
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
