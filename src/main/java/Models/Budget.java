package Models;

import java.util.HashMap;
import java.util.Map;

public class Budget {
    public Map<String, Double> categoryBudgets = new HashMap<>();
    public Map<String, Double> spending = new HashMap<>();

    public void setBudget(String category, double amount) {
        categoryBudgets.put(category, amount);
    }

    public void addSpending(String category, double amount) {
        spending.put(category, spending.getOrDefault(category, 0.0) + amount);
    }

    public boolean isOverBudget(String category) {
        return spending.getOrDefault(category, 0.0) > categoryBudgets.getOrDefault(category, Double.MAX_VALUE);
    }
}


