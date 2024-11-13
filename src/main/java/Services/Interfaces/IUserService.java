package Services.Interfaces;

public interface IUserService {
    void registerUser(String username, String password);
    void loginUser(String username, String password);
    double getUserBalance();
    void viewReports();
}