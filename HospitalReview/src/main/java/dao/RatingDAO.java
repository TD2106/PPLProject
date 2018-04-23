package dao;

import dbconnection.DBConnection;
import model.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RatingDAO {
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

    public static void deleteRating(int patientID, int doctorID) throws SQLException {
        String sql = "DELETE FROM rating WHERE patient_id = ? AND doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setInt(2, doctorID);
        preparedStatement.execute();
    }

    public static boolean isRatingExists(int patientID, int doctorID) throws SQLException {
        String sql = "SELECT rating FROM rating WHERE patient_id = ? AND doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setInt(2, doctorID);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public static void addRating(int patientID, int doctorID, int rating) throws SQLException {
        String sql = "INSERT INTO rating(patient_id,doctor_id,rating,is_enable) VALUES(?,?,?,1)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setInt(2, doctorID);
        preparedStatement.setInt(3, rating);
        preparedStatement.execute();
    }

    public static void updateRating(int patientID, int doctorID, int rating) throws SQLException {
        String sql = "UPDATE rating SET rating = ? WHERE patient_id = ? and doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, rating);
        preparedStatement.setInt(2, patientID);
        preparedStatement.setInt(3, doctorID);
        preparedStatement.execute();
    }

    public static void enableRating(int patientID, int doctorID) throws SQLException {
        String sql = "UPDATE rating SET is_enable = 1 WHERE patient_id = ? and doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setInt(2, doctorID);
        preparedStatement.execute();
    }

    public static void disableRating(int patientID, int doctorID) throws SQLException {
        String sql = "UPDATE rating SET is_enable = 0 WHERE patient_id = ? and doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        preparedStatement.setInt(2, doctorID);
        preparedStatement.execute();
    }

    public static ArrayList<Rating> getAllRatingOfADoctor(int doctorID) throws SQLException {
        String sql = "SELECT patient_id,rating,is_enable FROM rating WHERE doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, doctorID);
        ArrayList<Rating> ratings = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ratings.add(new Rating(resultSet.getInt("patient_id"), doctorID, resultSet.getInt("rating"), resultSet.getInt("is_enable")));
        }
        return ratings;
    }

    public static ArrayList<Rating> getAllEnableRatingOfADoctor(int doctorID) throws SQLException {
        String sql = "SELECT patient_id,rating FROM rating WHERE doctor_id = ? and is_enable = 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, doctorID);
        ArrayList<Rating> ratings = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ratings.add(new Rating(resultSet.getInt("patient_id"), doctorID, resultSet.getInt("rating"), 1));
        }
        return ratings;
    }

    public static ArrayList<Rating> getAllRatingOfAPatient(int patientID) throws SQLException {
        String sql = "SELECT doctor_id,rating,is_enable FROM rating WHERE patient_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        ArrayList<Rating> ratings = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ratings.add(new Rating(patientID, resultSet.getInt("doctor_id"), resultSet.getInt("rating"), resultSet.getInt("is_enable")));
        }
        return ratings;
    }

    public static double getAverageRatingOfADoctor(int doctorID) throws SQLException {
        ArrayList<Rating> ratings = getAllEnableRatingOfADoctor(doctorID);
        double result = 0;
        for (Rating rating : ratings) {
            result += rating.getRating();
        }
        if (ratings.size() == 0) return 0;
        else return result /= ratings.size();
    }
}
