package dao;

import dbconnection.DBConnection;
import model.Hospital;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class HospitalDAO {
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

    public static void addHospital(String email, String password, String hospitalName, String address,
                                   String website, String hospitalAdminName, String hospitalAdminEmail) throws SQLException {
        String sql = "{CALL add_hospital(?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setString(1, email);
        callableStatement.setString(2, password);
        callableStatement.setString(3, hospitalName);
        callableStatement.setString(4, address);
        callableStatement.setString(5, website);
        callableStatement.setString(6, hospitalAdminName);
        callableStatement.setString(7, hospitalAdminEmail);
        callableStatement.execute();
    }

    public static void deleteHospital(int hospitalID) throws SQLException {
        String sql = "DELETE FROM user WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, hospitalID);
        preparedStatement.execute();
    }

    public static Hospital getHospital(int hospitalID) throws SQLException {
        User user = UserDAO.getUser(hospitalID);
        String sql = "SELECT hospital_name, address, website, hospital_admin_name, hospital_admin_email, is_activated " +
                "FROM hospital WHERE hospital_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, hospitalID);
        ResultSet resultSet = preparedStatement.executeQuery();
        Hospital result = null;
        while (resultSet.next()) {
            result = new Hospital(user.getUserID(), user.getEmail(), user.getPassword(), user.getUserType(),
                    resultSet.getString("hospital_name"), resultSet.getString("address"), resultSet.getString("website"),
                    resultSet.getString("hospital_admin_name"), resultSet.getString("hospital_admin_email"),
                    resultSet.getInt("is_activated"));
        }
        return result;
    }

    public static ArrayList<Hospital> getAllHospital() throws SQLException {
        String sql = "SELECT hospital_id FROM hospital";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Hospital> hospitals = new ArrayList<Hospital>();
        while (resultSet.next()) {
            hospitals.add(getHospital(resultSet.getInt("hospital_id")));
        }
        return hospitals;
    }

    public static ArrayList<Hospital> getAllActivatedHospital() throws SQLException {
        String sql = "SELECT hospital_id FROM hospital WHERE is_activated = 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Hospital> hospitals = new ArrayList<Hospital>();
        while (resultSet.next()) {
            hospitals.add(getHospital(resultSet.getInt("hospital_id")));
        }
        return hospitals;
    }

    public static ArrayList<Hospital> getHospitalByName(String hospitalName) throws SQLException {
        hospitalName = hospitalName.toUpperCase();
        String sql = "SELECT hospital_id FROM hospital WHERE UPPER(hospital_name) like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + hospitalName + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Hospital> hospitals = new ArrayList<>();
        while (resultSet.next()) {
            hospitals.add(getHospital(resultSet.getInt("hospital_id")));
        }
        return hospitals;
    }

    public static ArrayList<Hospital> getHospitalByNameAndAddress(String hospitalName, String address) throws SQLException {
        hospitalName = hospitalName.toUpperCase();
        address = address.toUpperCase();
        String sql = "SELECT hospital_id FROM hospital WHERE UPPER(hospital_name) like ? AND UPPER(address) like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + hospitalName + "%");
        preparedStatement.setString(2, "%" + address + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Hospital> hospitals = new ArrayList<>();
        while (resultSet.next()) {
            hospitals.add(getHospital(resultSet.getInt("hospital_id")));
        }
        return hospitals;
    }

    public static void updateHospital(int hospitalID, String email, String password, String hospitalName, String address,
                                      String website, String hospitalAdminName, String hospitalAdminEmail) throws SQLException {
        String sql = "{CALL update_hospital(?,?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, hospitalID);
        callableStatement.setString(2, email);
        callableStatement.setString(3, password);
        callableStatement.setString(4, hospitalName);
        callableStatement.setString(5, address);
        callableStatement.setString(6, website);
        callableStatement.setString(7, hospitalAdminName);
        callableStatement.setString(8, hospitalAdminEmail);
        callableStatement.execute();
    }

//    public static void main(String[] args){
//        try {
//            addHospital("CR@gmail.com","123","Cho Ray","Nguyen Chi Thanh","cr.com","test","test@gmail.com");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
