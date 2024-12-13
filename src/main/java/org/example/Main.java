package org.example;

import Models.User;
import Controllers.FileController;
import Controllers.UserController;
import Services.ValidateService;

public class Main {
    public static void main(String[] args) {
        FileController fileController = new FileController();
        fileController.createUserFile();
        ValidateService validateService = ValidateService.getInstance();
        UserController userController = new UserController();
        User currentUser = new User();
        while (true) {
            System.out.println("--------------------------------------------------");
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
                    User user = userController.login();
                    if (user != null) {
                        currentUser = user;
                    }
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    System.exit(0);
            }
        }
    }
}


