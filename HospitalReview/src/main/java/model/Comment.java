package model;

public class Comment {
    private int commentID;
    private int patientID;
    private int doctorID;
    private String content;
    private String date;
    private String time;

    public Comment(int commentID, int patientID, int doctorID, String content, String date, String time) {
        this.commentID = commentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
