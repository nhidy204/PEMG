package Services;

import Constants.FileConstants;
import Models.Budget;
import Services.Interfaces.IBudgetService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BudgetService implements IBudgetService {
    private static BudgetService instance;
    private final String FILE_PATH = FileConstants.ROOT_PATH + "/" + FileConstants.BUDGET_FILE_NAME;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private BudgetService() {}

    public static BudgetService getInstance() {
        if (instance == null) {
            instance = new BudgetService();
        }
        return instance;
    }

    @Override
    public void addBudget(Budget budget, ArrayList<Budget> budgets) {
        budgets.add(budget);
        saveBudgets(budgets);
        System.out.println("Budget added successfully.");
    }

    @Override
    public ArrayList<Budget> listBudgets(ArrayList<Budget> budgets) {
        return budgets;
    }

    @Override
    public void editBudget(String budgetId, Budget updatedBudget, ArrayList<Budget> budgets) {
        for (Budget budget : budgets) {
            if (budget.getId().equals(budgetId)) {
                budget.setMaximumAmount(updatedBudget.getMaximumAmount());
                budget.setBudgetName(updatedBudget.getBudgetName());
                budget.setUpdatedAt(updatedBudget.getUpdatedAt());
                saveBudgets(budgets);
                System.out.println("Budget updated successfully.");
                return;
            }
        }
        System.out.println("Budget not found.");
    }

    @Override
    public void deleteBudget(String budgetId, ArrayList<Budget> budgets) {
        budgets.removeIf(budget -> budget.getId().equals(budgetId));
        saveBudgets(budgets);
        System.out.println("Budget deleted successfully.");
    }

    @Override
    public void saveBudgets(ArrayList<Budget> budgets) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(budgets, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Budget> loadBudgets() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Budget[] budgetsArray = gson.fromJson(reader, Budget[].class);
            return new ArrayList<>(Arrays.asList(budgetsArray));
        } catch (FileNotFoundException e) {
            System.out.println("Budget file not found. Creating a new one.");
            return new ArrayList<>();
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Budget getBudgetByExpenseTargetId(String expenseTargetId) {
        ArrayList<Budget> budgets = loadBudgets();
        for (Budget budget : budgets) {
            if (budget.getExpenseTargetId().equals(expenseTargetId)) {
                return budget;
            }
        }
        return null;
    }
}
