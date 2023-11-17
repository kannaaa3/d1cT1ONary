package model.database;

import model.user.User;

import java.sql.*;
import java.util.Random;

public class Database {
    private Connection connection = null;
    private Statement statement = null;

    /**
     * Constructor for a new database object.
     */
    public Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"
                    + "src/main/resources/SqliteJavaDB.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Can not find class org.sqlite.JDBC, maybe missing dependencies");
            e.printStackTrace();
            System.exit(-1);
        } catch (SQLException e) {
            System.out.println("Connection failed unexpectedly!");
            e.printStackTrace();
            System.exit(-1);
        }
        createTables();
    }

    /**
     * Function to create all essential tables.
     */
    private void createTables() {
        try {
            TableCreator.createUserTable(statement);
            TableCreator.createWordDatabase(statement);
            TableCreator.createSynonymDatabase(statement);
            TableCreator.createAntonymDatabase(statement);
            TableCreator.createUserWordListDataTable(statement);
            TableCreator.createUserWordListNameDataTable(statement);
            TableCreator.createUserWordReviewDataTable(statement);
            TableCreator.createUserSearchHistoryDataTable(statement);
        } catch (SQLException e) {
            System.out.println("Something is wrong when creating tables!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Function to close connection, use when the application is closed.
     */
    public void closeConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can not close SQLite connection!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Function to register new users.
     *
     * @param username the new user's username
     * @param password the new user's password
     * @return a string which is the user's id, adding user to database if needed
     */
    public String register(String username, String password) {
        Random ran = new Random();
        while (true) {
            StringBuilder userID = new StringBuilder();
            for (int i = 0; i < 64; i++) {
                int randomInteger = ran.nextInt(16);
                if (randomInteger < 10) {
                    userID.append((char)(48 + randomInteger));
                } else {
                    userID.append((char)(97 + randomInteger - 10));
                }
            }
            try {
                if (UsersQueryHandler.containsUserID(connection, userID.toString())) {
                    continue;
                }
                UsersQueryHandler.addUser(connection, userID.toString(), username, password);
                return userID.toString();
            } catch (SQLException e) {
                System.out.println("Unexpected error while registering!");
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    /**
     * Function login with username and password, if the pair username and password led to a match,
     * the function will return a string which is the user's id, else return null.
     *
     * @param username the user's username
     * @param password the user's password
     * @return a string which is the user's id if the pair (username, password) matches a user or
     * null if it's not
     */
    public String login(String username, String password) {
        try {
            return UsersQueryHandler.getUserID(connection, username, password);
        } catch (SQLException e) {
            System.out.println("Unexpected error while getting user id!");
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    /**
     * Function to check if a username is already existed.
     *
     * @param username the username we want to check
     * @return true if the username is already existed, false if otherwise
     */
    public boolean containsUsername(String username) {
        try {
            return UsersQueryHandler.containsUsername(connection, username);
        } catch (SQLException e) {
            System.out.println("Error while checking if username: " + username + "exist!");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Please note that this function is only used when testing.
     */
    public void removeAllTables() {
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS users;");
            statement.executeUpdate("DROP TABLE IF EXISTS words;");
            statement.executeUpdate("DROP TABLE IF EXISTS word_synonym");
            statement.executeUpdate("DROP TABLE IF EXISTS word_antonym");
            statement.executeUpdate("DROP TABLE IF EXISTS user_word_review_data;");
            statement.executeUpdate("DROP TABLE IF EXISTS user_word_list_data;");
            statement.executeUpdate("DROP TABLE IF EXISTS user_search_history_data;");
            statement.executeUpdate("DROP TABLE IF EXISTS user_word_list_name_data");
        } catch (SQLException e) {
            System.out.println("Error while removing all tables!");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}