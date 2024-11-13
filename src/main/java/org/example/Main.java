package org.example;

import Controllers.UserController;
import Services.ValidateService;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        ValidateService validateService = ValidateService.getInstance();

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
                    if (userController.login()) {  // Adjust login method if necessary
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


