package Services.Interfaces;
import Models.User;

import java.util.ArrayList;

public interface IUserService {
    User registerUser(String username, String password, ArrayList<User> users);
    User loginUser(String username, String password, ArrayList<User> users);
    double getUserBalance();
    void viewReports();
    boolean isUsernameTaken(String username, ArrayList<User> users);
    ArrayList<User> loadUsers();
}
