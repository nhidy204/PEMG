package Services;

import Constants.FileConstants;
import Models.Budget;
import Models.Wallet;
import Services.Interfaces.IBudgetService;
import Services.Interfaces.IWalletService;
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
    private final IWalletService walletService;

    private BudgetService(IWalletService walletService) {
        this.walletService = walletService;
    }

    public static BudgetService getInstance(IWalletService walletService) {
        if (instance == null) {
            instance = new BudgetService(walletService);
        }
        return instance;
    }

    @Override
    public void addBudget(Budget budget, ArrayList<Budget> budgets) {
        Wallet wallet = walletService.getWalletByUserId(budget.getUserId());
        if (wallet == null) {
            System.out.println("No wallet found for this user. Cannot create budget.");
            return;
        }

        double remainingBalance = wallet.getBalance();
        for (Budget b : budgets) {
            if (b.getUserId().equals(budget.getUserId())) {
                remainingBalance -= b.getMaximumAmount();
            }
        }

        if (budget.getMaximumAmount() > remainingBalance) {
            System.out.println("Cannot create budget. Amount exceeds remaining wallet balance.");
            return;
        }

        budgets.add(budget);
        saveBudgets(budgets);
        System.out.println("Budget added successfully.");
    }

    @Override
    public ArrayList<Budget> listBudgets(String userId, ArrayList<Budget> budgets) {
        ArrayList<Budget> userBudgets = new ArrayList<>();
        for (Budget budget : budgets) {
            if (budget.getUserId() != null && budget.getUserId().equals(userId)) {
                userBudgets.add(budget);
            }
        }
        return userBudgets;
    }

    @Override
    public void editBudget(String budgetId, Budget updatedBudget, ArrayList<Budget> budgets) {
        for (Budget budget : budgets) {
            if (budget.getId() != null && budget.getId().equals(budgetId)) {
                budget.setMaximumAmount(updatedBudget.getMaximumAmount());
                budget.setBudgetName(updatedBudget.getBudgetName());
                budget.setUpdatedAt(updatedBudget.getUpdatedAt());
                budget.setUserId(updatedBudget.getUserId());
                saveBudgets(budgets);
                System.out.println("Budget updated successfully.");
                return;
            }
        }
        System.out.println("Budget not found.");
    }

    @Override
    public void deleteBudget(String budgetId, ArrayList<Budget> budgets) {
        budgets.removeIf(budget -> budget.getId() != null && budget.getId().equals(budgetId));
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
            if (budgetsArray == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(budgetsArray));
        } catch (FileNotFoundException e) {
            System.out.println("Budget file not found. Creating a new one.");
            return new ArrayList<>();
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
