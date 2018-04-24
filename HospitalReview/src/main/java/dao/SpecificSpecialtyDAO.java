package dao;

import dbconnection.DBConnection;
import model.SpecificSpecialty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpecificSpecialtyDAO {
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

    public static void addSpecificSpecialty(String specificSpecialty) throws SQLException {
        String sql = "INSERT INTO specific_specialty(specific_specialty) VALUES(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, specificSpecialty);
        preparedStatement.execute();
    }

    public static void deleteSpecificSpecialty(int specificSpecialtyID) throws SQLException {
        String sql = "DELETE FROM specific_specialty WHERE specific_specialty_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, specificSpecialtyID);
        preparedStatement.execute();
    }

    public static void updateSpecificSpecialty(int specificSpecialtyID, String specificSpecialty) throws SQLException {
        String sql = "UPDATE specific_specialty SET specific_specialty = ? WHERE specific_specialty_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, specificSpecialty);
        preparedStatement.setInt(2, specificSpecialtyID);
        preparedStatement.execute();
    }

    public static SpecificSpecialty getSpecificSpecialty(int specificSpecialtyID) throws SQLException {
        String sql = "SELECT specific_specialty, general_specialty_id FROM specific_specialty WHERE specific_specialty = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, specificSpecialtyID);
        SpecificSpecialty result = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result = new SpecificSpecialty(specificSpecialtyID, resultSet.getInt("general_specialty_id"),
                    resultSet.getString("specific_specialty"));
        }
        return result;
    }
}
