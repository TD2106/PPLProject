package model;

public class Rating {
    private int patientID;
    private int doctorID;
    private int rating;
    private int isEnable;

    public Rating(int patientID, int doctorID, int rating, int isEnable) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.rating = rating;
        this.isEnable = isEnable;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
