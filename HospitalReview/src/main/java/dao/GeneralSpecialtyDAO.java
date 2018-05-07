package dao;

import dbconnection.DBConnection;
import model.GeneralSpecialty;

import java.sql.*;
import java.util.ArrayList;

public class GeneralSpecialtyDAO {
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

    public static void addGeneralSpecialty(String generalSpecialty) throws SQLException {
        String sql = "INSERT INTO general_specialty(general_specialty) VALUES(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, generalSpecialty);
        preparedStatement.execute();
    }

    public static void updateGeneralSpecialty(int generalSpecialtyID, String generalSpecialty) throws SQLException {
        String sql = "UPDATE general_specialty SET general_specialty = ? WHERE general_specialty_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, generalSpecialty);
        preparedStatement.setInt(2, generalSpecialtyID);
        preparedStatement.execute();
    }

    public static void deleteGeneralSpecialty(int generalSpecialtyID) throws SQLException {
        String sql = "DELETE FROM general_specialty WHERE general_specialty_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, generalSpecialtyID);
        preparedStatement.execute();
    }

    public static GeneralSpecialty getGeneralSpecialty(int generalSpecialtyID) throws SQLException {
        GeneralSpecialty result = null;
        String sql = "SELECT general_specialty FROM general_specialty WHERE general_specialty_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, generalSpecialtyID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result = new GeneralSpecialty(generalSpecialtyID, resultSet.getString("general_specialty"));
        }
        return result;
    }

    public static ArrayList<GeneralSpecialty> getAllGeneralSpecialty() throws SQLException {
        String sql = "SELECT general_specialty_id FROM general_specialty";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<GeneralSpecialty> generalSpecialties = new ArrayList<>();
        while (resultSet.next()) {
            generalSpecialties.add(getGeneralSpecialty(resultSet.getInt("general_specialty_id")));
        }
        return generalSpecialties;
    }


}
