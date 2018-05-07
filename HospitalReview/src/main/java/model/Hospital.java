package model;

public class Hospital extends User {
    private String hospitalName;
    private String address;
    private String website;
    private String adminName;
    private String adminEmail;
    private int isActivated;

    public Hospital(int userID, String email, String password, String userType, String hospitalName, String address, String website, String adminName, String adminEmail, int isActivated) {
        super(userID, email, password, userType);
        this.hospitalName = hospitalName;
        this.address = address;
        this.website = website;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.isActivated = isActivated;
    }

    public int getHospitalID() {
        return this.getUserID();
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public int isActivated() {
        return isActivated;
    }

    public void setActivated(int activated) {
        isActivated = activated;
    }
}
