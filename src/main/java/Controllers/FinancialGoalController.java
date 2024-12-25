package Controllers;

import Models.FinancialGoal;
import Services.Interfaces.IFinancialGoalService;
import Services.Interfaces.IValidateService;
import Services.Interfaces.IWalletService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class FinancialGoalController {
    private final IFinancialGoalService financialGoalService;
    private final IValidateService validateService;
    private final IWalletService walletService;
    private final ArrayList<FinancialGoal> financialGoals;
    private final Scanner scanner = new Scanner(System.in);

    public FinancialGoalController(IFinancialGoalService financialGoalService, IValidateService validateService, IWalletService walletService) {
        this.financialGoalService = financialGoalService;
        this.validateService = validateService;
        this.walletService = walletService;
        this.financialGoals = financialGoalService.loadFinancialGoals();
    }

    public void showFinancialGoalMenu(String userId) {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Add Financial Goal");
            System.out.println("2. List Financial Goals");
            System.out.println("3. Edit Financial Goal");
            System.out.println("4. Delete Financial Goal");
            System.out.println("5. Check Goal Progress");
            System.out.println("6. Back to main menu");

            int choice = validateService.inputInt("Choose an option: ", 1, 6);

            switch (choice) {
                case 1:
                    addFinancialGoal(userId);
                    break;
                case 2:
                    listFinancialGoals(userId);
                    break;
                case 3:
                    editFinancialGoal(userId);
                    break;
                case 4:
                    deleteFinancialGoal(userId);
                    break;
                case 5:
                    checkGoalProgress(userId);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addFinancialGoal(String userId) {
        String goalName = validateService.inputString("Enter financial goal name: ", null);
        double goalTarget = validateService.inputDouble("Enter financial goal target: ");

        FinancialGoal financialGoal = new FinancialGoal(UUID.randomUUID().toString(), goalName, goalTarget, LocalDateTime.now().toString(), LocalDateTime.now().toString(), userId);
        financialGoalService.addFinancialGoal(financialGoal, financialGoals);
        saveFinancialGoals();
    }

    private void listFinancialGoals(String userId) {
        ArrayList<FinancialGoal> financialGoalList = financialGoalService.listFinancialGoals(userId, financialGoals);
        int id = 1;
        if (financialGoalList.isEmpty()) {
            System.out.println("No financial goals found for this user.");
        } else {
            for (FinancialGoal financialGoal : financialGoalList) {
                System.out.printf("%-10s %-20s %-20s %-12s\n", "No.", "Name", "Goal Target", "Financial Goal ID");
                System.out.printf("%-10s %-20s %-20s %-12s\n", id, financialGoal.getGoalName(), financialGoal.getGoalTarget(), financialGoal.getId());
                id++;
            }
        }
    }

    private void editFinancialGoal(String userId) {
        String id = validateService.inputString("Enter financial goal ID to edit: ", null);
        String goalName = validateService.inputString("Enter new financial goal name: ", null);
        double goalTarget = validateService.inputDouble("Enter new financial goal target: ");

        FinancialGoal updatedFinancialGoal = new FinancialGoal(id, goalName, goalTarget, LocalDateTime.now().toString(), LocalDateTime.now().toString(), userId);
        financialGoalService.editFinancialGoal(id, updatedFinancialGoal, financialGoals);
        saveFinancialGoals();
    }

    private void deleteFinancialGoal(String userId) {
        String id = validateService.inputString("Enter financial goal ID to delete: ", null);
        financialGoalService.deleteFinancialGoal(id, financialGoals);
        saveFinancialGoals();
    }

    private void checkGoalProgress(String userId) {
        double walletBalance = walletService.getWalletByUserId(userId).getBalance();
        financialGoalService.checkGoalProgress(userId, walletBalance, financialGoals);
    }

    private void saveFinancialGoals() {
        financialGoalService.saveFinancialGoals(financialGoals);
    }
}
