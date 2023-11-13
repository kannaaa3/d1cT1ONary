package model.database;

import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {
    /**
     * Function to create users database.
     *
     * @param statement a statement object that what created by a database connection
     * @throws SQLException throws when the statement can not be executed
     */
    public static void createUserTable(Statement statement) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS users(
                    user_id VARCHAR[64],
                    username VARCHAR[32],
                    password VARCHAR[32],
                    PRIMARY KEY (user_id)
                );
                """;
        statement.execute(sql);
    }

    /**
     * Function to create user's word revision database.
     *
     * @param statement a statement object that what created by a database connection
     * @throws SQLException throws when the statement can not be executed
     */
    public static void createUserWordReviewDataTable(Statement statement) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS user_word_review_data(
                    user_id VARCHAR[64],
                    word VARCHAR[32],
                    timestamp INTEGER,
                    PRIMARY KEY (user_id, word),
                    FOREIGN KEY (user_id)
                        REFERENCES users (user_id)
                            ON DELETE CASCADE
                            ON UPDATE NO ACTION
                );
                """;
        statement.execute(sql);
    }

    /**
     * Function to create user's wordlist database.
     *
     * @param statement a statement object that what created by a database connection
     * @throws SQLException throws when the statement can not be executed
     */
    public static void createUserWordListDataTable(Statement statement) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS user_wordlist_data(
                    user_id VARCHAR[64],
                    word_list_id INTEGER,
                    word_list_name VARCHAR[32],
                    word VARCHAR[32],
                    PRIMARY KEY (user_id, word_list_id),
                    FOREIGN KEY (user_id)
                        REFERENCES users (user_id)
                            ON DELETE CASCADE
                            ON UPDATE NO ACTION
                );
                """;
        statement.execute(sql);
    }

    /**
     * Function to create user's search history database.
     *
     * @param statement a statement object that what created by a database connection
     * @throws SQLException throws when the statement can not be executed
     */
    public static void createUserSearchHistoryDataTable(Statement statement) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS user_search_history_data(
                    user_id VARCHAR[64],
                    word VARCHAR[32],
                    timestamp INTEGER,
                    PRIMARY KEY (user_id, word),
                    FOREIGN KEY (user_id)
                        REFERENCES users (user_id)
                            ON DELETE CASCADE
                            ON UPDATE NO ACTION
                );
                """;
        statement.execute(sql);
    }
}
