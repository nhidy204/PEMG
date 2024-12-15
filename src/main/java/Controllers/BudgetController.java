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

    public void showBudgetMenu() {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Add Budget");
            System.out.println("2. List Budgets");
            System.out.println("3. Edit Budget");
            System.out.println("4. Delete Budget");
            System.out.println("5. Back to main menu");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    addBudget();
                    break;
                case 2:
                    listBudgets();
                    break;
                case 3:
                    editBudget();
                    break;
                case 4:
                    deleteBudget();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addBudget() {
        String userId = validateService.inputString("Enter user ID: ", null);
        String expenseTargetId = validateService.inputString("Enter expense target ID: ", null);
        double maximumAmount = validateService.inputDouble("Enter maximum amount: ");
        String budgetName = validateService.inputString("Enter budget name: ", null);

        Budget budget = new Budget(UUID.randomUUID().toString(), userId, expenseTargetId, maximumAmount, budgetName, LocalDateTime.now().toString(), LocalDateTime.now().toString());
        budgetService.addBudget(budget, budgets);
        saveBudgets();
    }

    private void listBudgets() {
        ArrayList<Budget> budgetList = budgetService.listBudgets(budgets);
        for (Budget budget : budgetList) {
            System.out.println("Budget Name: " + budget.getBudgetName() + ", Maximum Amount: " + budget.getMaximumAmount() + ", ID: " + budget.getId());
        }
    }

    private void editBudget() {
        String id = validateService.inputString("Enter budget ID to edit: ", null);
        String budgetName = validateService.inputString("Enter new budget name: ", null);
        double maximumAmount = validateService.inputDouble("Enter new maximum amount: ");

        Budget updatedBudget = new Budget(id, null, null, maximumAmount, budgetName, LocalDateTime.now().toString(), LocalDateTime.now().toString());
        budgetService.editBudget(id, updatedBudget, budgets);
        saveBudgets();
    }

    private void deleteBudget() {
        String id = validateService.inputString("Enter budget ID to delete: ", null);
        budgetService.deleteBudget(id, budgets);
        saveBudgets();
    }

    private void saveBudgets() {
        budgetService.saveBudgets(budgets);
    }
}
