package model;

public class Patient extends User {
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private int isActivated;

    public Patient(int userID, String email, String password, String userType, String firstName, String lastName, String gender, String address, int isActivated) {
        super(userID, email, password, userType);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.isActivated = isActivated;
    }

    public int getPatientID() {
        return this.getUserID();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int isActivated() {
        return isActivated;
    }

    public void setActivated(int activated) {
        isActivated = activated;
    }
}
