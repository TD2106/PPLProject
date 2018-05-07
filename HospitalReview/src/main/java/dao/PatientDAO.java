package dao;

import dbconnection.DBConnection;
import model.Patient;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class PatientDAO {
    private static Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addPatient(String email, String password, String firstName, String lastName, String gender, String address) throws SQLException {
        String sql = "{CALL add_patient(?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setString(1, email);
        callableStatement.setString(2, password);
        callableStatement.setString(3, firstName);
        callableStatement.setString(4, lastName);
        callableStatement.setString(5, gender);
        callableStatement.setString(6, address);
        callableStatement.execute();
    }

    public static void activatePatient(int patientID) throws SQLException {
        String sql = "UPDATE patient SET is_activated = 1 WHERE patient_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.execute();
    }

    public static void deactivatePatient(int patientID) throws SQLException {
        String sql = "UPDATE patient SET is_activated = 0 WHERE patient_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.execute();
    }

    public static boolean isInFavoriteDoctor(int patientID, int doctorID) throws SQLException{
        String sql = "SELECT * FROM favorite_doctor WHERE patient_id = ? AND doctor_id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(sql);
        sqlStatement.setString(1,Integer.toString(patientID));
        sqlStatement.setString(2,Integer.toString(doctorID));
        ResultSet resultSet = sqlStatement.executeQuery();
        return resultSet.next();
    }

    public static boolean isInFavoriteHospital(int patientID, int hospitalID) throws SQLException{
        String sql = "SELECT * FROM favorite_hospital WHERE patient_id = ? AND hospital_id = ?";
        PreparedStatement sqlStatement = connection.prepareStatement(sql);
        sqlStatement.setString(1,Integer.toString(patientID));
        sqlStatement.setString(2,Integer.toString(hospitalID));
        ResultSet resultSet = sqlStatement.executeQuery();
        return resultSet.next();
    }

    public static void addFavoriteHospital(int patientID, int hospitalID) throws SQLException {
        String sql = "INSERT INTO favorite_hospital(patient_id,hospital_id) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setInt(2, hospitalID);
        preparedStatement.execute();
    }

    public static void addFavoriteDoctor(int patientID, int doctorID) throws SQLException {
        String sql = "INSERT INTO favorite_doctor(patient_id,doctor_id) VALUES(?,?)";
        System.out.println("patientID = " + patientID + " doctorID = " + doctorID);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setInt(2, doctorID);
        preparedStatement.execute();
    }

    public static void removeFavoriteHospital(int patientID, int hospitalID) throws SQLException {
        String sql = "DELETE FROM favorite_hospital WHERE patient_id = ? and hospital_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setInt(2, hospitalID);
        preparedStatement.execute();
    }

    public static void removeFavoriteDoctor(int patientID, int doctorID) throws SQLException {
        String sql = "DELETE FROM favorite_doctor WHERE patient_id = ? and doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setInt(2, doctorID);
        preparedStatement.execute();
    }

    public static void deletePatient(int patientID) throws SQLException {
        String sql = "DELETE FROM user WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.execute();
    }

    public static void updatePatientProfile(int patientID, String email, String password, String firstName, String lastName, String gender, String address) throws SQLException {
        String sql = "{CALL update_patient(?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, patientID);
        callableStatement.setString(2, email);
        callableStatement.setString(3, password);
        callableStatement.setString(4, firstName);
        callableStatement.setString(5, lastName);
        callableStatement.setString(6, gender);
        callableStatement.setString(7, address);
        callableStatement.execute();
    }

    public static Patient getPatient(int patientID) throws SQLException {
        User user = UserDAO.getUser(patientID);
        String sql = "SELECT first_name, last_name, gender, address, is_activated FROM patient WHERE patient_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        ResultSet resultSet = preparedStatement.executeQuery();
        Patient result = null;
        while (resultSet.next()) {
            result = new Patient(user.getUserID(), user.getEmail(), user.getPassword(), user.getUserType(),
                    resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("gender"),
                    resultSet.getString("address"), resultSet.getInt("is_activated"));
        }
        return result;
    }

    public static ArrayList<Patient> getAllPatient() throws SQLException {
        String sql = "SELECT patient_id FROM patient";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ArrayList<Patient> patients = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            patients.add(getPatient(resultSet.getInt("patient_id")));
        }
        return patients;
    }

    public static void addPatientLanguage(int patientID, String language) throws SQLException {
        String sql = "INSERT INTO patient_language(patient_id,language) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setString(2, language);
        preparedStatement.execute();
    }

    /*public static void main(String[] args) throws SQLException,ClassNotFoundException{
        PatientDAO.addFavoriteDoctor(3, 1);
    }*/
}
