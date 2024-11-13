package org.example;

import Controllers.UserController;
import Services.Interfaces.IUserService;
import Services.Interfaces.IValidateService;
import Services.UserService;
import Services.ValidateService;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();

        ValidateService validateService = ValidateService.getInstance();
        while (true) {

            System.out.println("1. Register");
            System.out.println("2. Display tasks");
            System.out.println("3. Delete task");
            System.out.println("4. Quit");
            int option = validateService.inputInt("Your choice: ", 1, 4);
                switch (option) {
                    case 1:
                        userController.register();
                        break;
                    case 2:

                        break;
                    case 3:
                        break;
                    case 4:
                        System.exit(0);
                }
            }
            //handing option

        }
        // Create a new user
//        User user = new User("JohnDoe", "password123");
//
//        // Add transactions
//        Transaction income = new Transaction("income", 2000, "salary");
//        Transaction expense = new Transaction("expense", 200, "food");
//        user.addTransaction(income);
//        user.addTransaction(expense);
//
//        // Set up budget
//        user.budget.setBudget("food", 500);
//
//        // Add financial goals
//        FinancialGoal goal = new FinancialGoal("Vacation", 3000);
//        user.goals.add(goal);
//        goal.addContribution(500);
//
//        // Save user data
//        user.saveUserData();
//
//        // Load user data
//        User loadedUser = User.loadUserData("JohnDoe");
//        if (loadedUser != null) {
//            System.out.println("User " + loadedUser.getUserName() + " loaded successfully.");
//        }
    }

