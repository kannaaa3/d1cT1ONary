package model.database;

import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class TableCreator {
    public static final String[] tables = new String[]{
            "wn_gloss",
            "wn_synset",
            "wn_antonym",
            "wn_similar"
    };
    public static final String[] statements = new String[]{
            "INSERT INTO `wn_gloss` VALUES (?,?);",
            "INSERT INTO `wn_synset` VALUES (?,?,?,?,?,?,?);",
            "INSERT INTO `wn_antonym` VALUES (?,?,?,?);",
            "INSERT INTO `wn_similar` VALUES (?,?);"
    };
    public static final Integer[] numberOfArguments = new Integer[]{
            2, 7, 4, 2
    };

    /**
     * Function to get sql query from file.
     *
     * @param filePath the path to the file
     * @return an array of string which are the sql queries
     */
    public static String[] getQueryData(String filePath, String seperate) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                InputStream inputStream = new FileInputStream(file);
                String[] ans = new String(inputStream.readAllBytes()).split(seperate);
                inputStream.close();
                return ans;
            }
        } catch (IOException e) {
            System.out.println("Error while reading file: " + filePath);
            e.printStackTrace();
            System.exit(-1);
        }
        return new String[0];
    }

    /**
     * Function to load table's data from file.
     *
     * @param connection the database connection
     * @param filePath the table's queries filepath
     * @throws SQLException throw new SQLException if the query can not be performed
     */
    public static void createTable(Connection connection, String filePath) throws SQLException {
        String[] sql = getQueryData(filePath, ";");
        Statement statement = connection.createStatement();
        for (String query : sql) {
            statement.execute(query);
        }
    }

    /**
     * Function to load dictionary data
     *
     * @param connection the database connection
     * @param filePath the filepath of the dictionary data
     * @throws SQLException if the query can not be done
     */
    public static void loadDictionaryData(Connection connection,
                                          String filePath) throws SQLException {
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT * FROM wn_synset");
        if (resultSet.next()) {
            return;
        }

        String[] sql = getQueryData(filePath, "\n");
        int currentPointer = 0;
        while (currentPointer < sql.length) {
            for (int i = 0; i < statements.length; i++) {
                if (sql[currentPointer].trim().equals(tables[i])) {
                    PreparedStatement preparedStatement
                            = connection.prepareStatement(statements[i]);
                    for (int j = 1; j <= numberOfArguments[i]; j++) {
                        preparedStatement.setString(j, sql[currentPointer + j].trim());
                    }
                    preparedStatement.executeUpdate();
                    currentPointer += numberOfArguments[i];
                }
            }
            currentPointer++;
        }
    }
}