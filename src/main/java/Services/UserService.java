package Services;


import Services.Interfaces.IUserService;

public class UserService implements IUserService {
    private static UserService instance;
    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void registerUser(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        System.out.println("registerUser Success");
    }

    @Override
    public void loginUser(String username, String password) {

    }

    @Override
    public double getUserBalance() {
        return 0;
    }

    @Override
    public void viewReports() {

    }
}
