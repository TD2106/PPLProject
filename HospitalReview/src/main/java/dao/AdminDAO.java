package dao;

import dbconnection.DBConnection;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
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

    public static Admin getAdmin(int adminID) throws SQLException {
        String sql = "SELECT email, password, user_type FROM user WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, adminID);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin result = null;
        while (resultSet.next()) {
            result = new Admin(adminID, resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        return result;
    }
    public static void addAdmin(String email, String password) throws SQLException {
        String sql = "INSERT INTO user(email,password,user_type) VALUES(?,?,'admin')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
    }

    public static void updateAdmin(int userID, String email, String password) throws SQLException {
        String sql = "UPDATE user SET email = ?, password = ? WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(3, userID);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
    }
}
