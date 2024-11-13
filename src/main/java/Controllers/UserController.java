package Controllers;

import Services.Interfaces.IUserService;
import Services.Interfaces.IValidateService;
import Services.UserService;
import Services.ValidateService;

public class UserController {
    private final IUserService userService;
    private final IValidateService validateService;
    public UserController() {
        this.userService = UserService.getInstance();
        this.validateService = ValidateService.getInstance();
    }


    public void register() {
        String username = validateService.inputString("Enter username: ", null);
        String password = validateService.inputString("Enter password: ", null);
        userService.registerUser(username, password);
    }
}
