package model.database;

import java.sql.*;

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
            System.out.println("Database Opened...\n");
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
    }

    /**
     * Function to create all essential tables.
     */
    private void createTables() {
        try {
            TableCreator.createUserTable(statement);
            TableCreator.createUserWordListDataTable(statement);
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


}