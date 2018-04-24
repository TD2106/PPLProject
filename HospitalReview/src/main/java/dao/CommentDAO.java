package dao;

import dbconnection.DBConnection;
import model.Comment;

import java.sql.*;
import java.util.ArrayList;

public class CommentDAO {
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

    public static void addComment(int patientID, int doctorID, String commentContent) throws SQLException {
        String sql = "{CALL add_comment(?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, patientID);
        callableStatement.setInt(2, doctorID);
        callableStatement.setString(3, commentContent);
        callableStatement.execute();
    }

    public static void deleteComment(int commentID) throws SQLException {
        String sql = "DELETE FROM comment WHERE comment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, commentID);
        preparedStatement.execute();
    }

    public static void enableComment(int commentID) throws SQLException {
        String sql = "UPDATE comment SET is_enable = 1 WHERE comment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, commentID);
        preparedStatement.execute();
    }

    public static void disableComment(int commentID) throws SQLException {
        String sql = "UPDATE comment SET is_enable = 0 WHERE comment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, commentID);
        preparedStatement.execute();
    }

    public static ArrayList<Comment> getAllCommentOfADoctor(int doctorID) throws SQLException {
        String sql = "SELECT comment_id, patient_id, comment_content, comment_date, comment_time" +
                ", is_enable FROM comment WHERE doctor_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, doctorID);
        ArrayList<Comment> comments = new ArrayList<Comment>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            comments.add(new Comment(resultSet.getInt("comment_id"), resultSet.getInt("patient_id"),
                    doctorID, resultSet.getString("comment_content"), resultSet.getString("comment_date"),
                    resultSet.getString("comment_time"), resultSet.getInt("is_enable")));
        }
        return comments;
    }

    public static ArrayList<Comment> getAllEnableCommentOfADoctor(int doctorID) throws SQLException {
        String sql = "SELECT comment_id, patient_id, comment_content, comment_date, comment_time" +
                " FROM comment WHERE doctor_id = ? AND is_enable = 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, doctorID);
        ArrayList<Comment> comments = new ArrayList<Comment>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            comments.add(new Comment(resultSet.getInt("comment_id"), resultSet.getInt("patient_id"),
                    doctorID, resultSet.getString("comment_content"), resultSet.getString("comment_date"),
                    resultSet.getString("comment_time"), 1));
        }
        return comments;
    }

    public static ArrayList<Comment> getAllCommentOfAPatient(int patientID) throws SQLException {
        String sql = "SELECT comment_id, doctor_id, comment_content, comment_date, comment_time" +
                ", is_enable FROM comment WHERE patient_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patientID);
        ArrayList<Comment> comments = new ArrayList<Comment>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            comments.add(new Comment(resultSet.getInt("comment_id"), patientID,
                    resultSet.getInt("doctor_id"), resultSet.getString("comment_content"), resultSet.getString("comment_date"),
                    resultSet.getString("comment_time"), resultSet.getInt("is_enable")));
        }
        return comments;
    }
}
