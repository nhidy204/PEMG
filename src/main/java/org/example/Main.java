package org.example;

import Models.User;
import Controllers.FileController;
import Controllers.UserController;
import Services.ValidateService;

public class Main {
    public static void main(String[] args) {
        FileController fileController = new FileController(); //tao 1 instance cho fileController de quan ly tep du lieu
        fileController.createUserFile(); //tao tep chua thong tin user
        ValidateService validateService = ValidateService.getInstance(); //khoi tao validateService de xac thuc va lay du lieu tu nguoi dung
        UserController userController = new UserController();  //khoi tao userController de quan ly chuc nang lien quan den nguoi dung
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
                    userController.register(); //goi phuong thuc register tu userController
                    break;
                case 2:
                    User user = userController.login(); //goi phuong thuc login tu userController va luu user hien tai neu login thanh cong
                    if (user != null) {
                        currentUser = user; //luu thong tin user hien tai
                    }
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    System.exit(0);
            }
        }
    }
}