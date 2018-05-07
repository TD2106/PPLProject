package model;

public class Admin extends User {
    public Admin(int userID, String email, String password, String userType) {
        super(userID, email, password, userType);
    }

    public int getAdminID() {
        return this.getUserID();
    }
}
