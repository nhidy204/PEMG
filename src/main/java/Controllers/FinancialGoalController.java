package Controllers;

import Models.FinancialGoal;
import Services.Interfaces.IFinancialGoalService;
import Services.Interfaces.IValidateService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class FinancialGoalController {
    private final IFinancialGoalService financialGoalService;
    private final IValidateService validateService;
    private final ArrayList<FinancialGoal> financialGoals;
    private final Scanner scanner = new Scanner(System.in);

    public FinancialGoalController(IFinancialGoalService financialGoalService, IValidateService validateService) {
        this.financialGoalService = financialGoalService;
        this.validateService = validateService;
        this.financialGoals = financialGoalService.loadFinancialGoals();
    }

    public void showFinancialGoalMenu() {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Add Financial Goal");
            System.out.println("2. List Financial Goals");
            System.out.println("3. Edit Financial Goal");
            System.out.println("4. Delete Financial Goal");
            System.out.println("5. Check Goal Achievement");
            System.out.println("6. Track Goal Progress");
            System.out.println("7. Back to main menu");

            int choice = validateService.inputInt("Choose an option: ", 1, 7);

            switch (choice) {
                case 1:
                    addFinancialGoal();
                    break;
                case 2:
                    listFinancialGoals();
                    break;
                case 3:
                    editFinancialGoal();
                    break;
                case 4:
                    deleteFinancialGoal();
                    break;
                case 5:
                    checkGoalAchievement();
                    break;
                case 6:
                    trackGoalProgress();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addFinancialGoal() {
        String userId = validateService.inputString("Enter user ID: ", null);
        String name = validateService.inputString("Enter financial goal name: ", null);
        double targetAmount = validateService.inputDouble("Enter target amount: ");

        FinancialGoal financialGoal = new FinancialGoal(UUID.randomUUID().toString(), userId, name, targetAmount, LocalDateTime.now().toString(), LocalDateTime.now().toString());
        financialGoalService.addFinancialGoal(financialGoal, financialGoals);
        saveFinancialGoals();
    }

    private void listFinancialGoals() {
        ArrayList<FinancialGoal> financialGoalList = financialGoalService.listFinancialGoals(financialGoals);
        for (FinancialGoal financialGoal : financialGoalList) {
            System.out.println("Financial Goal Name: " + financialGoal.getName() + ", Target Amount: " + financialGoal.getTargetAmount() + ", ID: " + financialGoal.getId());
        }
    }

    private void editFinancialGoal() {
        String id = validateService.inputString("Enter financial goal ID to edit: ", null);
        String name = validateService.inputString("Enter new financial goal name: ", null);
        double targetAmount = validateService.inputDouble("Enter new target amount: ");

        FinancialGoal updatedFinancialGoal = new FinancialGoal(id, null, name, targetAmount, LocalDateTime.now().toString(), LocalDateTime.now().toString());
        financialGoalService.editFinancialGoal(id, updatedFinancialGoal, financialGoals);
        saveFinancialGoals();
    }

    private void deleteFinancialGoal() {
        String id = validateService.inputString("Enter financial goal ID to delete: ", null);
        financialGoalService.deleteFinancialGoal(id, financialGoals);
        saveFinancialGoals();
    }

    private void checkGoalAchievement() {
        String id = validateService.inputString("Enter financial goal ID to check achievement: ", null);
        FinancialGoal financialGoal = financialGoalService.getFinancialGoalById(id);
        if (financialGoal != null) {
            double walletBalance = financialGoalService.getWalletBalanceByUserId(financialGoal.getUserId());
            if (walletBalance >= financialGoal.getTargetAmount()) {
                System.out.println("Congratulations! You have achieved your financial goal: " + financialGoal.getName());
            } else {
                System.out.println("You have not yet achieved your financial goal: " + financialGoal.getName());
            }
        } else {
            System.out.println("Financial goal not found.");
        }
    }

    private void trackGoalProgress() {
        String id = validateService.inputString("Enter financial goal ID to track progress: ", null);
        FinancialGoal financialGoal = financialGoalService.getFinancialGoalById(id);
        if (financialGoal != null) {
            double walletBalance = financialGoalService.getWalletBalanceByUserId(financialGoal.getUserId());
            double progress = (walletBalance / financialGoal.getTargetAmount()) * 100;
            System.out.println("Progress towards financial goal '" + financialGoal.getName() + "': " + progress + "%");
        } else {
            System.out.println("Financial goal not found.");
        }
    }

    private void saveFinancialGoals() {
        financialGoalService.saveFinancialGoals(financialGoals);
    }
}

