package model.database;

import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {
    public static final int MAX_WORD_LENGTH = 64;
    public static final int MAX_USERNAME_LENGTH = 64;
    public static final int MAX_URL_LENGTH = 256;
    
    /**
     * Function to create users database.
     *
     * @param statement a statement object that what created by a database connection
     * @throws SQLException throws when the statement can not be executed
     */
    public static void createUserTable(Statement statement) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS users(
                    user_id VARCHAR[MAX_WORD_LENGTH],
                    username VARCHAR[MAX_USERNAME_LENGTH],
                    password VARCHAR[MAX_USERNAME_LENGTH],
                    CONSTRAINT users_pk PRIMARY KEY (user_id)
                );
                """;
        statement.execute(sql);
    }

    /**
     * Function to create word database.
     *
     * @param statement a statement object that what created by a database connection
     * @throws SQLException throws when the statement can not be executed
     */
    public static void createWordDatabase(Statement statement) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS words(
                    word_id VARCHAR[MAX_WORD_LENGTH],
                    word VARCHAR[MAX_WORD_LENGTH],
                    text VARCHAR[MAX_WORD_LENGTH],
                    audio VARCHAR[MAX_URL_LENGTH],
                    part_of_speech VARCHAR[MAX_WORD_LENGTH],
                    definition VARCHAR[MAX_WORD_LENGTH],
                    example VARCHAR[MAX_WORD_LENGTH],
                    CONSTRAINT words_pk PRIMARY KEY (word_id)
                    
                );
                """;
        statement.execute(sql);
    }

    /**
     * Function to create synonym database.
     *
     * @param statement a statement object that what created by a database connection
     * @throws SQLException throws when the statement can not be executed
     */
    public static void createSynonymDatabase(Statement statement) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS word_synonym(
                    word_id VARCHAR[MAX_WORD_LENGTH],
                    synonym VARCHAR[MAX_WORD_LENGTH],
                    CONSTRAINT word_synonym_pk PRIMARY KEY (word_id, synonym),
                    CONSTRAINT word_synonym_word_id_fk
                    FOREIGN KEY (word_id)
                        REFERENCES words (word_id)
                            ON DELETE CASCADE
                            ON UPDATE NO ACTION
                );
                """;
        statement.execute(sql);
    }

    /**
     * Function to create antonym database.
     *
     * @param statement a statement object that what created by a database connection
     * @throws SQLException throws when the statement can not be executed
     */
    public static void createAntonymDatabase(Statement statement) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS word_antonym(
                    word_id VARCHAR[MAX_WORD_LENGTH],
                    antonym VARCHAR[MAX_WORD_LENGTH],
                    CONSTRAINT word_antonym_pk PRIMARY KEY (word_id, antonym),
                    CONSTRAINT word_antonym_word_id
                    FOREIGN KEY (word_id)
                        REFERENCES words (word_id)
                            ON DELETE CASCADE
                            ON UPDATE NO ACTION
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
                    user_id VARCHAR[MAX_WORD_LENGTH],
                    word_id VARCHAR[MAX_WORD_LENGTH],
                    timestamp INTEGER,
                    fluency_level INTEGER,
                    CONSTRAINT user_word_review_data_pk PRIMARY KEY (user_id, word_id),
                    CONSTRAINT user_word_review_data_user_id_fk
                    FOREIGN KEY (user_id)
                        REFERENCES users (user_id)
                            ON DELETE CASCADE
                            ON UPDATE NO ACTION,
                    CONSTRAINT user_word_review_data_word_id_fk
                    FOREIGN KEY (word_id)
                        REFERENCES words (word_id)
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
                CREATE TABLE IF NOT EXISTS user_word_list_data(
                    user_id VARCHAR[MAX_WORD_LENGTH],
                    word_list_id INTEGER,
                    word_id VARCHAR[MAX_WORD_LENGTH],
                    CONSTRAINT user_word_list_data_pk PRIMARY KEY (user_id, word_list_id, word_id),
                    CONSTRAINT user_word_list_data_user_id
                    FOREIGN KEY (user_id)
                        REFERENCES users (user_id)
                            ON DELETE CASCADE
                            ON UPDATE NO ACTION,
                    CONSTRAINT user_word_list_data_word_id
                    FOREIGN KEY (word_id)
                        REFERENCES words (word_id)
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
    public static void createUserWordListNameDataTable(Statement statement) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS user_word_list_name_data(
                    user_id VARCHAR[MAX_WORD_LENGTH],
                    word_list_id INTEGER,
                    word_list_name VARCHAR[MAX_WORD_LENGTH],
                    CONSTRAINT user_word_list_name_data_pk PRIMARY KEY (user_id, word_list_id),
                    CONSTRAINT user_word_list_name_data_user_id_fk
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
                    user_id VARCHAR[MAX_WORD_LENGTH],
                    word_id VARCHAR[MAX_WORD_LENGTH],
                    timestamp INTEGER,
                    CONSTRAINT user_search_history_data_pk
                        PRIMARY KEY (user_id, word_id),
                    CONSTRAINT user_search_history_data_user_id_fk
                        FOREIGN KEY (user_id)
                            REFERENCES users (user_id)
                                ON DELETE CASCADE
                                ON UPDATE NO ACTION,
                    CONSTRAINT user_search_history_data_word_id_fk
                        FOREIGN KEY (word_id)
                            REFERENCES words (word_id)
                                ON DELETE CASCADE
                                ON UPDATE NO ACTION
                );
                """;
        statement.execute(sql);
    }
}