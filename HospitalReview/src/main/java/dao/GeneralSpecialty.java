package dao;

import dbconnection.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class GeneralSpecialty {
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
}
