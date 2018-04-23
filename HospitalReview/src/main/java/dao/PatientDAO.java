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
}
