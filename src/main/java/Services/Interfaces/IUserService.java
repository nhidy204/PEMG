package Services.Interfaces;

import Models.User;

public interface IUserService {
    void registerUser(String username, String password);
    User loginUser(String username, String password);
    double getUserBalance();
    void viewReports();
    boolean isUsernameTaken(String username);
}