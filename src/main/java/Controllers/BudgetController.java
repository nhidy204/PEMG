package Controllers;

import Models.Budget;
import Services.Interfaces.IBudgetService;
import Services.Interfaces.IValidateService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BudgetController {
    private final IBudgetService budgetService;
    private final IValidateService validateService;
    private final ArrayList<Budget> budgets;
    private final Scanner scanner = new Scanner(System.in);

    public BudgetController(IBudgetService budgetService, IValidateService validateService) {
        this.budgetService = budgetService;
        this.validateService = validateService;
        this.budgets = budgetService.loadBudgets();
    }

    public void showBudgetMenu(String userId) {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Add Budget");
            System.out.println("2. List Budgets");
            System.out.println("3. Edit Budget");
            System.out.println("4. Delete Budget");
            System.out.println("5. Back to main menu");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            // Luôn tải lại dữ liệu từ file
            budgets.clear();
            budgets.addAll(budgetService.loadBudgets());

            switch (choice) {
                case 1:
                    addBudget(userId);
                    break;
                case 2:
                    listBudgets(userId);
                    break;
                case 3:
                    editBudget(userId);
                    break;
                case 4:
                    deleteBudget(userId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void addBudget(String userId) {
        String budgetName = validateService.inputString("Enter budget name: ", null);
        double maximumAmount = validateService.inputDouble("Enter maximum amount: ");
        String expenseTargetId = validateService.inputString("Enter expense target ID: ", null);

        Budget budget = new Budget(UUID.randomUUID().toString(), budgetName, maximumAmount, expenseTargetId, LocalDateTime.now().toString(), LocalDateTime.now().toString(), userId);
        budgetService.addBudget(budget, budgets);
        saveBudgets();
    }

    private void listBudgets(String userId) {
        ArrayList<Budget> budgetList = budgetService.listBudgets(userId, budgets);
        if (budgetList.isEmpty()) {
            System.out.println("No budgets found for this user.");
        } else {
            for (Budget budget : budgetList) {
                System.out.println("Budget Name: " + budget.getBudgetName() + ", Maximum Amount: " + budget.getMaximumAmount() + ", Expense Target ID: " + budget.getExpenseTargetId() + ", ID: " + budget.getId());
            }
        }
    }

    private void editBudget(String userId) {
        String id = validateService.inputString("Enter budget ID to edit: ", null);
        String budgetName = validateService.inputString("Enter new budget name: ", null);
        double maximumAmount = validateService.inputDouble("Enter new maximum amount: ");
        String expenseTargetId = validateService.inputString("Enter new expense target ID: ", null);

        Budget updatedBudget = new Budget(id, budgetName, maximumAmount, expenseTargetId, LocalDateTime.now().toString(), LocalDateTime.now().toString(), userId);
        budgetService.editBudget(id, updatedBudget, budgets);
        saveBudgets();
    }

    private void deleteBudget(String userId) {
        String id = validateService.inputString("Enter budget ID to delete: ", null);
        budgetService.deleteBudget(id, budgets);
        saveBudgets();
    }

    private void saveBudgets() {
        budgetService.saveBudgets(budgets);
    }
}
