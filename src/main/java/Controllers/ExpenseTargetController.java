package Controllers;

import Models.ExpenseTarget;
import Services.Interfaces.IExpenseTargetService;
import Services.Interfaces.IValidateService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class ExpenseTargetController {
    private final IExpenseTargetService expenseTargetService;
    private final IValidateService validateService;
    private final ArrayList<ExpenseTarget> expenseTargets;
    private final Scanner scanner = new Scanner(System.in);

    public ExpenseTargetController(IExpenseTargetService expenseTargetService, IValidateService validateService) {
        this.expenseTargetService = expenseTargetService;
        this.validateService = validateService;
        this.expenseTargets = expenseTargetService.loadExpenseTargets();
    }

    public void showExpenseTargetMenu(String userId) { // Truyền userId vào đây
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Add Expense Target");
            System.out.println("2. List Expense Targets");
            System.out.println("3. Edit Expense Target");
            System.out.println("4. Delete Expense Target");
            System.out.println("5. Back to main menu");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    addExpenseTarget(userId); // Truyền userId vào đây
                    break;
                case 2:
                    listExpenseTargets(userId); // Truyền userId vào đây
                    break;
                case 3:
                    editExpenseTarget(userId);
                    break;
                case 4:
                    deleteExpenseTarget(userId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addExpenseTarget(String userId) {
        String name = validateService.inputString("Enter expense target name: ", null);
        ExpenseTarget expenseTarget = new ExpenseTarget(UUID.randomUUID().toString(), name, userId);
        expenseTargetService.addExpenseTarget(expenseTarget, expenseTargets);
        saveExpenseTargets();
    }


    private void listExpenseTargets(String userId) {
        ArrayList<ExpenseTarget> expenseTargetList = expenseTargetService.listExpenseTargetsByUserId(userId, expenseTargets);
        if (expenseTargetList.isEmpty()) {
            System.out.println("No expense targets found for this user.");
        } else {
            for (ExpenseTarget expenseTarget : expenseTargetList) {
                System.out.println("Expense Target Name: " + expenseTarget.getName() + ", ID: " + expenseTarget.getId());
            }
        }
    }

    private void editExpenseTarget(String userId) {
        String id = validateService.inputString("Enter expense target ID to edit: ", null);
        String name = validateService.inputString("Enter new expense target name: ", null);
        ExpenseTarget updatedExpenseTarget = new ExpenseTarget(id, name, userId); // Truyền userId vào đây
        expenseTargetService.editExpenseTarget(userId, id, updatedExpenseTarget, expenseTargets);
        saveExpenseTargets();
    }

    private void deleteExpenseTarget(String userId) {
        String id = validateService.inputString("Enter expense target ID to delete: ", null);
        expenseTargetService.deleteExpenseTarget(userId, id, expenseTargets);
        saveExpenseTargets();
    }

    private void saveExpenseTargets() {
        expenseTargetService.saveExpenseTargets(expenseTargets);
    }

}
