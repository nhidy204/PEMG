package Services;

import Constants.FileConstants;
import Models.ExpenseTarget;
import Services.Interfaces.IExpenseTargetService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpenseTargetService implements IExpenseTargetService {
    private static ExpenseTargetService instance;
    private final String FILE_PATH = FileConstants.ROOT_PATH + "/" + FileConstants.EXPENSE_TARGET_FILE_NAME;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private ExpenseTargetService() {}

    public static ExpenseTargetService getInstance() {
        if (instance == null) {
            instance = new ExpenseTargetService();
        }
        return instance;
    }

    @Override
    public void addExpenseTarget(ExpenseTarget expenseTarget, ArrayList<ExpenseTarget> expenseTargets) {
        expenseTargets.add(expenseTarget);
        saveExpenseTargets(expenseTargets);
        System.out.println("Expense target added successfully.");
    }


    @Override
    public ArrayList<ExpenseTarget> listExpenseTargetsByUserId(String userId, ArrayList<ExpenseTarget> expenseTargets) {
        ArrayList<ExpenseTarget> userExpenseTargets = new ArrayList<>();
        for (ExpenseTarget expenseTarget : expenseTargets) {
            if (expenseTarget.getUserId() != null && expenseTarget.getUserId().equals(userId)) {
                userExpenseTargets.add(expenseTarget);
            }
        }
        return userExpenseTargets;
    }


    @Override
    public void editExpenseTarget(String userId, String expenseTargetId, ExpenseTarget updatedExpenseTarget, ArrayList<ExpenseTarget> expenseTargets) {
        for (ExpenseTarget expenseTarget : expenseTargets) {
            if (expenseTarget.getId().equals(expenseTargetId) && expenseTarget.getUserId().equals(userId)) {
                expenseTarget.setName(updatedExpenseTarget.getName());
                saveExpenseTargets(expenseTargets);
                System.out.println("Expense target updated successfully.");
                return;
            }
        }
        System.out.println("Expense target not found or you don't have permission to edit this target.");
    }

    @Override
    public void deleteExpenseTarget(String userId, String expenseTargetId, ArrayList<ExpenseTarget> expenseTargets) {
        expenseTargets.removeIf(expenseTarget -> expenseTarget.getId().equals(expenseTargetId) && expenseTarget.getUserId().equals(userId));
        saveExpenseTargets(expenseTargets);
        System.out.println("Expense target deleted successfully or you don't have permission to delete this target.");
    }


    @Override
    public void saveExpenseTargets(ArrayList<ExpenseTarget> expenseTargets) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(expenseTargets, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ExpenseTarget> loadExpenseTargets() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            ExpenseTarget[] expenseTargetsArray = gson.fromJson(reader, ExpenseTarget[].class);
            if (expenseTargetsArray == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(expenseTargetsArray));
        } catch (FileNotFoundException e) {
            System.out.println("Expense target file not found. Creating a new one.");
            return new ArrayList<>();
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
