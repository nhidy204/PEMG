package Services;

import Constants.FileConstants;
import Models.FinancialGoal;
import Models.Wallet;
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
    public ArrayList<FinancialGoal> listFinancialGoals(ArrayList<FinancialGoal> financialGoals) {
        return financialGoals;
    }

    @Override
    public void editFinancialGoal(String financialGoalId, FinancialGoal updatedFinancialGoal, ArrayList<FinancialGoal> financialGoals) {
        for (FinancialGoal financialGoal : financialGoals) {
            if (financialGoal.getId().equals(financialGoalId)) {
                financialGoal.setTargetAmount(updatedFinancialGoal.getTargetAmount());
                financialGoal.setName(updatedFinancialGoal.getName());
                financialGoal.setUpdatedAt(updatedFinancialGoal.getUpdatedAt());
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
    public FinancialGoal getFinancialGoalById(String financialGoalId) {
        ArrayList<FinancialGoal> financialGoals = loadFinancialGoals();
        for (FinancialGoal financialGoal : financialGoals) {
            if (financialGoal.getId().equals(financialGoalId)) {
                return financialGoal;
            }
        }
        return null;
    }

    @Override
    public double getWalletBalanceByUserId(String userId) {
        WalletService walletService = WalletService.getInstance();
        ArrayList<Wallet> wallets = walletService.loadWallets();
        for (Wallet wallet : wallets) {
            if (wallet.getUserId().equals(userId)) {
                return wallet.getBalance();
            }
        }
        return 0;
    }
}
