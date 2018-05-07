package dbconnection;

import java.sql.*;

/**
 * @author Duy Le
 */
public class DBConnection {
    private static Connection dbConnection;
    private static String connectionURL = "jdbc:mysql://quan.cekgqzi9pswg.us-east-1.rds.amazonaws.com:3306/HospitalReview";
    private static String user = "quan";
    private static String password = "quan1609";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        if (dbConnection == null) {
            dbConnection = DriverManager.getConnection(connectionURL, user, password);
        }
        return dbConnection;
    }

}