package dao;

import dbconnection.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        String sql = "{CALL add_patient(?,?,?,?,?,?,0)}";
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

    public static void addFavoriteHospital(int patientID, int hospitalID) throws SQLException {
        String sql = "INSERT INTO favorite_hospital(patient_id,hospital_id) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setInt(2, hospitalID);
        preparedStatement.execute();
    }

    public static void addFavoriteDoctor(int patientID, int doctorID) throws SQLException {
        String sql = "INSERT INTO favorite_doctor(patient_id,doctor_id) VALUES(?,?)";
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


}
