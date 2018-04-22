package model;

public abstract class User {
    private int userID;
    private String email;
    private String password;
    private String userType;

    public User(int userID, String email, String password, String userType) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }
}
