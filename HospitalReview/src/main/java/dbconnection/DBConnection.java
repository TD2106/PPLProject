package dbconnection;

import java.sql.*;

/**
 * @author Duy Le
 */
public class DBConnection {
    private static Connection dbConnection;
    private static String connectionURL = "jdbc:mysql://localhost:3306/hospitalreview";
    private static String user = "root";
    private static String password = "lutden";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        if (dbConnection == null) {
            dbConnection = DriverManager.getConnection(connectionURL, user, password);
        }
        return dbConnection;
    }

    public static void main(String[] args) {
        try {
            getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}