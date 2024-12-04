package org.example;

import Controllers.FileController;
import Controllers.UserController;
import Services.ValidateService;

public class Main {
    public static void main(String[] args) {
        FileController fileController = new FileController();
        fileController.createUserFile();
        ValidateService validateService = ValidateService.getInstance();
        UserController userController = new UserController();

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


