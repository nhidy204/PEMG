package Controllers;

import Models.*;
import Services.*;
import Services.Interfaces.IUserService;
import Services.Interfaces.IValidateService;

import java.util.ArrayList;
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

    public void register(ArrayList<User> users) {
        //yeu cau nhap username va password
        String username = validateService.inputString("Enter username: ", null);
        String password = validateService.inputString("Enter password: ", null);

        //kiem tra xem username da ton tai hay chua
        if (userService.isUsernameTaken(username, users)) {
            System.out.println("Username is already taken. Please try a different one.");
        } else {
            //register newUser va thong bao thanh cong
            User newUser = userService.registerUser(username, password, users);
            System.out.println(newUser.username + " registered successfully.");
        }
    }

    public User login(ArrayList<User> users) {
        //nhap username va password login
        String loginUsername = validateService.inputString("Login with your username: ", null);
        String loginPassword = validateService.inputString("Enter your password: ", null);

        //goi user de xac thuc thong tin dang nhap
        User user = userService.loginUser(loginUsername, loginPassword, users);
        //vong lap, neu user login fail thi hien thi lua chon Y/N cho nguoi dung neu muon login tiep
        while (user == null) {
            Boolean option = validateService.inputYesNo("Login again?");
            if (option) {
                loginUsername = validateService.inputString("Login with your username: ", null);
                loginPassword = validateService.inputString("Enter your password: ", null);

                user = userService.loginUser(loginUsername, loginPassword, users);
            } else {
               return null;
            }
        }
        //neu login thanh cong thi hien thi menu sau khi dang nhap
       return user;
    }

    public ArrayList<User> loadUser() {
        return userService.loadUsers();
    }

    //hien thi menu sau khi dang nhap

}