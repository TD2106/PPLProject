package model;

public class Doctor {
    private int doctorID;
    private String firstName;
    private String lastName;
    private String degree;
    private boolean isAcceptInsurance;
    private String officeHour;
    private int hospitalID;
    private int generalSpecialtyID;
    private double averageRating;

    public Doctor(int doctorID, String firstName, String lastName, String degree, boolean isAcceptInsurance,
                  String officeHour, int hospitalID, int generalSpecialtyID, double averageRating) {
        this.doctorID = doctorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
        this.isAcceptInsurance = isAcceptInsurance;
        this.officeHour = officeHour;
        this.hospitalID = hospitalID;
        this.generalSpecialtyID = generalSpecialtyID;
        this.averageRating = averageRating;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public boolean isAcceptInsurance() {
        return isAcceptInsurance;
    }

    public void setAcceptInsurance(boolean acceptInsurance) {
        isAcceptInsurance = acceptInsurance;
    }

    public String getOfficeHour() {
        return officeHour;
    }

    public void setOfficeHour(String officeHour) {
        this.officeHour = officeHour;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public int getGeneralSpecialtyID() {
        return generalSpecialtyID;
    }

    public void setGeneralSpecialtyID(int generalSpecialtyID) {
        this.generalSpecialtyID = generalSpecialtyID;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
