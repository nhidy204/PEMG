package org.example;
import Controllers.TransactionController;
import Models.User;
import Services.BudgetService;
import Services.FinancialGoalService;
import Services.Interfaces.IBudgetService;
import Services.Interfaces.IFinancialGoalService;
import Utils.UserDataManager;
import Controllers.FileController;
import Controllers.UserController;
import Services.ValidateService;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileController fileController = new FileController();
        fileController.createUserFile();
        ValidateService validateService = ValidateService.getInstance();
        UserController userController = new UserController();
        UserDataManager userDataManager;
        userDataManager = new UserDataManager();

        User user = userDataManager.loadUser("user.json");
        if (user == null) {
            user = new User();
            userDataManager.saveUser(user);
        }

        // Khởi tạo các service
        IBudgetService budgetService = new BudgetService();
        IFinancialGoalService goalService = new FinancialGoalService();
        //services.WalletService walletService = new services.WalletService();
        // Kiểm tra mục tiêu tài chính
        //goalService.checkAndNotifyGoals(user);
        // Lưu lại người dùng
        userDataManager.saveUser(user);



        while (true) {
            System.out.println("Welcome to the Personal Expense Management System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");


            int option = validateService.inputInt("Your choice: ", 1, 3);


            switch (option) {
                case 1:
                    userController.register();
                    break;
                case 2:
                    if (userController.login()) {  // Adjusted to directly call the login method
                        System.out.println("You are now logged in!");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    System.exit(0);
            }
        }
    }
}


