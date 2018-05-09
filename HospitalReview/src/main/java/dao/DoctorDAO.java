package dao;

import dbconnection.DBConnection;
import model.Doctor;

import java.sql.*;
import java.util.ArrayList;

public class DoctorDAO {
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

    public static void addDoctor(String firstName, String lastName, String degree, int acceptInsurance,
                                 String officeHour, int hospitalID, int generalSpecialtyID) throws SQLException {
        String sql = "{CALL add_doctor (?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setString(1, firstName);
        callableStatement.setString(2, lastName);
        callableStatement.setString(3, degree);
        callableStatement.setInt(4, acceptInsurance);
        callableStatement.setString(5, officeHour);
        callableStatement.setInt(6, hospitalID);
        callableStatement.setInt(7, generalSpecialtyID);
        callableStatement.execute();
    }

    public static void updateDoctor(int doctorID, String firstName, String lastName, String degree, int acceptInsurance,
                                    String officeHour, int hospitalID, int generalSpecialtyID) throws SQLException {
        String sql = "{CALL update_doctor(?,?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, doctorID);
        callableStatement.setString(2, firstName);
        callableStatement.setString(3, lastName);
        callableStatement.setString(4, degree);
        callableStatement.setInt(5, acceptInsurance);
        callableStatement.setString(6, officeHour);
        callableStatement.setInt(7, hospitalID);
        callableStatement.setInt(8, generalSpecialtyID);
    }

    public static void deleteDoctor(int doctorID) throws SQLException {
        String sql = "DELETE FROM doctor WHERE doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, doctorID);
        preparedStatement.execute();
    }

    public static Doctor getDoctor(int doctorID) throws SQLException {
        String sql = "SELECT first_name,last_name,degree,accept_insurance,office_hour,hospital_id,general_specialty_id" +
                " FROM doctor WHERE doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, doctorID);
        ResultSet resultSet = preparedStatement.executeQuery();
        Doctor result = null;
        while (resultSet.next()) {
            result = new Doctor(doctorID, resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7), RatingDAO.getAverageRatingOfADoctor(doctorID));
        }
        return result;
    }

    public static ArrayList<String> getAllDoctorLanguages(int doctorID) throws SQLException{
        String sql = "SELECT * FROM doctor_language WHERE doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, doctorID);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> doctorLanguages = new ArrayList<>();
        while (resultSet.next()){
            doctorLanguages.add(resultSet.getString(2));
        }
        return doctorLanguages;
    }

    public static ArrayList<Doctor> getAllDoctor() throws SQLException{
        String sql = "SELECT doctor_id FROM doctor";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Doctor> doctors = new ArrayList<>();
        while (resultSet.next()) {
            doctors.add(getDoctor(resultSet.getInt(1)));
        }
        return doctors;
    }

    public static ArrayList<Doctor> getAllDoctorFromHospital(int hospitalID) throws SQLException {
        String sql = "SELECT doctor_id FROM doctor WHERE hospital_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, hospitalID);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Doctor> doctors = new ArrayList<>();
        while (resultSet.next()) {
            doctors.add(getDoctor(resultSet.getInt(1)));
        }
        return doctors;
    }

    public static ArrayList<Doctor> getAllDoctorByName(String firstName, String lastName) throws SQLException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        firstName = firstName.toUpperCase();
        lastName = lastName.toLowerCase();
        String sql = "SELECT doctor_id FROM doctor WHERE UPPER(first_name) LIKE ? AND UPPER(last_name) LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + firstName + "%");
        preparedStatement.setString(2, "%" + lastName + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            doctors.add(getDoctor(resultSet.getInt(1)));
        }
        return doctors;
    }

    public static ArrayList<Doctor> getAllDoctorByNameAndGeneralSpecialty
            (String firstName, String lastName, int generalSpecialtyID) throws SQLException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        firstName = firstName.toUpperCase();
        lastName = lastName.toUpperCase();
        String sql = "SELECT doctor_id FROM doctor WHERE UPPER(first_name) LIKE ? AND UPPER(last_name) LIKE ? " +
                "AND general_specialty_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + firstName + "%");
        preparedStatement.setString(2, "%" + lastName + "%");
        preparedStatement.setInt(3, generalSpecialtyID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            doctors.add(getDoctor(resultSet.getInt(1)));
        }
        return doctors;
    }

    public static ArrayList<Doctor> getAllDoctorByNameAndGeneralSpecialtyAndSpecificSpecialty
            (String firstName, String lastName, int generalSpecialtyID, int specificSpecialtyID) throws SQLException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        firstName = firstName.toUpperCase();
        lastName = lastName.toUpperCase();
        String sql = "SELECT DISTINCT doctor_id FROM doctor INNER JOIN doctor_specific_specialty ON " +
                "doctor.doctor_id = doctor_specific_specialty.doctor_id" +
                " WHERE upper(first_name) = ? AND upper(last_name) = ? AND " +
                "general_specialty_id = ? AND specific_specialty_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + firstName + "%");
        preparedStatement.setString(2, "%" + lastName + "%");
        preparedStatement.setInt(3, generalSpecialtyID);
        preparedStatement.setInt(4, specificSpecialtyID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            doctors.add(getDoctor(resultSet.getInt(1)));
        }
        return doctors;
    }

    public static void addDoctorLanguage(int doctorID, String language) throws SQLException{
        String sql = "INSERT INTO doctor_language(doctor_id,language) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, doctorID);
        preparedStatement.setString(2, language);
        preparedStatement.execute();
    }

    /*public static void main(String[] args) throws SQLException,ClassNotFoundException{
        ArrayList<String> doctors = DoctorDAO.getAllDoctorLanguages(1);
        for(String doctor: doctors){
            System.out.println(doctor);
        }
    }*/
}
