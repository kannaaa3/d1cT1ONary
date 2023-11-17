package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersQueryHandler {
    /**
     * Function to add a new user to the database.
     *
     * @param connection the connection to the database
     * @param userID the new user's ID
     * @param username the new user's username
     * @param password the new user's password
     * @throws SQLException an exception will be throw if adding user unsuccessfully
     */
    public static void addUser(Connection connection, String userID,
                               String username, String password) throws SQLException {
        String sql = """
                INSERT INTO users
                VALUES (?, ?, ?)
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        statement.setString(2, username);
        statement.setString(3, password);
        statement.executeUpdate();
    }

    /**
     * Function to get user id with username and password.
     *
     * @param connection the connection to the database
     * @param username the username we want to get user ID
     * @param password the password of the user
     * @return a String which is the username of the user, or null if no userid founded
     * @throws SQLException a SQLException will be thrown if no user found
     */
    public static String getUserID(Connection connection, String username,
                                   String password) throws SQLException {
        String sql = """
                SELECT user_id
                FROM users
                WHERE username = ? AND password = ?;
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("user_ID");
        } else {
            return null;
        }
    }

    /**
     * Function to check if a username is already existed.
     *
     * @param connection the connection to the database
     * @param username the username we want to check
     * @return true if the username is already presented
     * @throws SQLException throws when the query is unsuccessfully executed
     */
    public static boolean containsUsername(Connection connection, String username)
            throws SQLException {
        String sql = """
                SELECT username
                FROM users
                WHERE username = ?
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        return statement.executeQuery().next();
    }

    /**
     * Function to check if user id is already existed.
     *
     * @param connection the connection to the database
     * @param userID the userID we want to check
     * @return true if the user id is already presented
     * @throws SQLException throws when the query is unsuccessfully executed
     */
    public static boolean containsUserID(Connection connection, String userID) throws SQLException {
        String sql = """
                SELECT user_id
                FROM users
                WHERE user_id = ?
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        return statement.executeQuery().next();
    }
}