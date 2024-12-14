package Controllers;

import Models.*;
import Services.*;
import Services.Interfaces.IUserService;
import Services.Interfaces.IValidateService;

import java.util.Scanner;
//controller cho function lien quan den nguoi dung
public class UserController {
    private final IUserService userService; //interface cho dich vu lq toi nguoi dung
    private final IValidateService validateService; //interface cho chuc nang input
    private final Scanner scanner = new Scanner(System.in);

    //constructor khoi tao controller, lay instance cuủa userService và validateService (theo single pattern)
    public UserController() {
        this.userService = UserService.getInstance();
        this.validateService = ValidateService.getInstance();

    }

    public void register() {
        //yeu cau nhap username va password
        String username = validateService.inputString("Enter username: ", null);
        String password = validateService.inputString("Enter password: ", null);

        //kiem tra xem username da ton tai hay chua
        if (userService.isUsernameTaken(username)) {
            System.out.println("Username is already taken. Please try a different one.");
        } else {
            //register newUser va thong bao thanh cong
            User newUser = userService.registerUser(username, password);
            System.out.println(newUser.username + " registered successfully.");
        }
    }

    public User login() {
        //nhap username va password login
        String loginUsername = validateService.inputString("Login with your username: ", null);
        String loginPassword = validateService.inputString("Enter your password: ", null);

        //goi user de xac thuc thong tin dang nhap
        User user = userService.loginUser(loginUsername, loginPassword);
        //vong lap, neu user login fail thi hien thi lua chon Y/N cho nguoi dung neu muon login tiep
        while (user == null) {
            Boolean option = validateService.inputYesNo("Login again?");
            if (option){
                loginUsername = validateService.inputString("Login with your username: ", null);
                loginPassword = validateService.inputString("Enter your password: ", null);

                user = userService.loginUser(loginUsername, loginPassword);
            }else {
                return null; //neu chon N -> tra ve null
            }
        }
        //neu login thanh cong thi hien thi menu sau khi dang nhap
        showPostLoginMenu();
        return user;
    }

    //hien thi menu sau khi dang nhap
    private void showPostLoginMenu() {
        while (true) {
            System.out.println("Menu for you:");
            System.out.println("1. Transaction");
            System.out.println("2. Budget");
            System.out.println("3. Financial Goals");
            System.out.println("4. Wallet");
            System.out.println("5. Logout");

            int choice = validateService.inputInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4: // Wallet

                    break;


                case 5:

                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}