package Models;
// import com.google.gson.Gson;


public class User {
    private final String username;
    private final String password;
    private final double balance;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0; // Default balance
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void loginUser(String username, String password) {
        // Additional logic for logged-in user can be added here
    }
}

/*
    // Save user data to JSON
    public void saveUserData() {
        try (FileWriter writer = new FileWriter(userName + ".json")) {
            Gson gson = new Gson();
            gson.toJson(this, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Load user data from JSON
    public static User loadUserData(String userName) {
        try (FileReader reader = new FileReader(userName + ".json")) {
           Gson gson = new Gson();
           return gson.fromJson(reader, User.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
*/
