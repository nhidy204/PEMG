package Services;

import Constants.FileConstants;
import Models.FinancialGoal;
import Services.Interfaces.IFinancialGoalService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FinancialGoalService implements IFinancialGoalService {
    private static FinancialGoalService instance;
    private final String FILE_PATH = FileConstants.ROOT_PATH + "/" + FileConstants.FINANCIAL_GOAL_FILE_NAME;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private FinancialGoalService() {}

    public static FinancialGoalService getInstance() {
        if (instance == null) {
            instance = new FinancialGoalService();
        }
        return instance;
    }

    @Override
    public void addFinancialGoal(FinancialGoal financialGoal, ArrayList<FinancialGoal> financialGoals) {
        financialGoals.add(financialGoal);
        saveFinancialGoals(financialGoals);
        System.out.println("Financial goal added successfully.");
    }

    @Override
    public ArrayList<FinancialGoal> listFinancialGoals(String userId, ArrayList<FinancialGoal> financialGoals) {
        ArrayList<FinancialGoal> userFinancialGoals = new ArrayList<>();
        for (FinancialGoal financialGoal : financialGoals) {
            if (financialGoal.getUserId() != null && financialGoal.getUserId().equals(userId)) {
                userFinancialGoals.add(financialGoal);
            }
        }
        return userFinancialGoals;
    }

    @Override
    public void editFinancialGoal(String financialGoalId, FinancialGoal updatedFinancialGoal, ArrayList<FinancialGoal> financialGoals) {
        for (FinancialGoal financialGoal : financialGoals) {
            String id = financialGoal.getId();
            if (id.equals(financialGoalId)) {
                financialGoal.setGoalName(updatedFinancialGoal.getGoalName());
                financialGoal.setGoalTarget(updatedFinancialGoal.getGoalTarget());
                financialGoal.setUpdatedAt(updatedFinancialGoal.getUpdatedAt());
                financialGoal.setUserId(updatedFinancialGoal.getUserId());
                saveFinancialGoals(financialGoals);
                System.out.println("Financial goal updated successfully.");
                return;
            }
        }
        System.out.println("Financial goal not found.");
    }

    @Override
    public void deleteFinancialGoal(String financialGoalId, ArrayList<FinancialGoal> financialGoals) {
        financialGoals.removeIf(financialGoal -> financialGoal.getId().equals(financialGoalId));
        saveFinancialGoals(financialGoals);
        System.out.println("Financial goal deleted successfully.");
    }

    @Override
    public void saveFinancialGoals(ArrayList<FinancialGoal> financialGoals) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(financialGoals, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<FinancialGoal> loadFinancialGoals() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            FinancialGoal[] financialGoalsArray = gson.fromJson(reader, FinancialGoal[].class);
            return new ArrayList<>(Arrays.asList(financialGoalsArray));
        } catch (FileNotFoundException e) {
            System.out.println("Financial goal file not found. Creating a new one.");
            return new ArrayList<>();
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void checkGoalProgress(String userId, double walletBalance, ArrayList<FinancialGoal> financialGoals) {
        for (FinancialGoal financialGoal : financialGoals) {
            if (financialGoal.getUserId().equals(userId)) {
                double progress = (walletBalance / financialGoal.getGoalTarget()) * 100;
                if (progress > 100) {
                    progress = 100;
                }

                int progressBarLength = 20; // Length of the progress bar
                int progressHashes = (int) (progress / 100 * progressBarLength);
                StringBuilder progressBar = new StringBuilder();
                for (int i = 0; i < progressHashes; i++) {
                    progressBar.append("#");
                }
                for (int i = progressHashes; i < progressBarLength; i++) {
                    progressBar.append("-");
                }

                System.out.println("Progress for goal '" + financialGoal.getGoalName() + "': " + String.format("%.2f", progress) + "% [" + progressBar + "]");
                if (progress == 100) {
                    System.out.println("Congratulations! You have achieved your goal: " + financialGoal.getGoalName());
                }
            }
        }
    }

}
