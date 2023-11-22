package model.database;
import javafx.util.Pair;
import model.user.User;
import model.word.Meaning;
import model.word.Phonetic;
import model.word.Word;
import model.word.WordList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database {
    private static Connection connection = null;
    private static Statement statement = null;

    /**
     * Constructor for a new database object.
     */
    public static void init() {
        if (connection != null) {
            return;
        }
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
    private static void createTables() {
        try {
            TableCreator.createTable(connection, "src/main/resources/table.sql");
        } catch (SQLException e) {
            System.out.println("Something is wrong when creating tables!");
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            TableCreator.loadDictionaryData(connection, "src/main/resources/data.sql");
        } catch (SQLException e) {
            System.out.println("Something is wrong when loading tables!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Function to close connection, use when the application is closed.
     */
    public static void closeConnection() {
        init();
        try {
            statement.close();
            connection.close();
            statement = null;
            connection = null;
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
    public static String register(String username, String password) {
        init();
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
    public static String login(String username, String password) {
        init();
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
    public static boolean containsUsername(String username) {
        init();
        try {
            return UsersQueryHandler.containsUsername(connection, username);
        } catch (SQLException e) {
            System.out.println("Error while checking if username: " + username + "exist!");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Function to get all the word from the database.
     *
     * @return an array which are the word in the list
     */
    public static String[] getAllWordFromDatabase() {
        init();
        return WordDataQueryHandler.getWordListFromDatabase(connection);
    }

    /**
     * Function to remove all user's record from the database.
     */
    public static void removeAllUserRecord() {
        init();
        try {
            statement.execute("DELETE FROM user_word_list_name_data");
            statement.execute("DELETE FROM user_search_history_data");
            statement.execute("DELETE FROM user_word_list_data");
            statement.execute("DELETE FROM user_word_review_data");
            statement.execute("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[] getSynonym(String word) {
        init();
        Pair<Long, Long> wordKey = getWordInformation(word);
        if (wordKey == null) {
            return new String[0];
        }
        try {
            return WordDataQueryHandler.getWordSynonym(connection, wordKey.getKey());
        } catch (SQLException e) {
            System.out.println("Something went wrong while querying!");
            e.printStackTrace();
        }
        return new String[0];
    }

    public static String[] getAntonym(String word) {
        init();
        Pair<Long, Long> wordKey = getWordInformation(word);
        if (wordKey == null) {
            return new String[0];
        }
        try {
            return WordDataQueryHandler.getWordAntonym(connection,
                    wordKey.getKey(), wordKey.getValue());
        } catch (SQLException e) {
            System.out.println("Something went wrong while querying!");
            e.printStackTrace();
        }
        return new String[0];
    }

    public static String getWordMeaning(String word) {
        init();
        try {
            String[] meaning = WordDataQueryHandler.getWordMeaning(connection, word);
            if (meaning.length > 0) {
                return meaning[0];
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong while getting word's data!");
            e.printStackTrace();
        }
        return null;
    }

    public static Pair<Long, Long> getWordInformation(String word) {
        init();
        try {
            List<Pair<Long, Long>> result = WordDataQueryHandler.getWordKey(connection, word);
            if (result.isEmpty()) {
                return null;
            }
            return result.get(0);
        } catch (SQLException e) {
            System.out.println("Something is wrong when fetching word data!");
            e.printStackTrace();
        }

        return null;
    }

    public static Word getWordData(String word) {
        init();
        Pair<Long, Long> wordKey = getWordInformation(word);
        if (wordKey == null) {
            return null;
        }
        String sql = """
                SELECT *
                FROM wn_synset
                WHERE synset_id = ? AND w_num = ?;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, wordKey.getKey());
            preparedStatement.setLong(2, wordKey.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Word(
                        wordKey.getKey(),
                        wordKey.getValue(),
                        word,
                        new Phonetic(
                                resultSet.getString("phonetic"),
                                ""
                        ),
                        new Meaning(
                                resultSet.getString("ss_type"),
                                getWordMeaning(word),
                                "",
                                getSynonym(word),
                                getAntonym(word)
                        ));
            }
        } catch (SQLException e) {
            System.out.println("Can not perform SQL query to get word data!");
            e.printStackTrace();
        }

        return new Word(
                wordKey.getKey(),
                wordKey.getValue(),
                word,
                new Phonetic(
                        "",
                        ""
                ),
                new Meaning(
                        "",
                        "",
                        "",
                        getSynonym(word),
                        getAntonym(word)
                )
        );
    }

    public static String getWordFromSynsetIDAndWordNum(Long synsetID, Long wordNum) {
        init();
        String sql = """
                SELECT word
                FROM wn_synset
                WHERE synset_id = ? AND w_num = ?;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, synsetID);
            preparedStatement.setLong(2, wordNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getString("word");
        } catch (SQLException e) {
            System.out.println("Something went wrong when extracting word!");
            e.printStackTrace();
        }
        return "";
    }

    public static void addWordToUserSearchHistory(String userID, String word) {
        init();
        UsersDataQueryHandler.addWordToUserSearchHistory(connection, userID, word);
    }

    public static void deleteWordFromUserHistory(String userID, String word) {
        init();
        UsersDataQueryHandler.deleteWordFromUserHistory(connection, userID, word);
    }

    public static void createNewWordList(String userID, int wordListID, String wordListName) {
        init();
        UsersDataQueryHandler.createNewWordList(connection, userID, wordListID, wordListName);
    }

    public static void removeWordList(String userID, int wordListID) {
        init();
        UsersDataQueryHandler.removeWordList(connection, userID, wordListID);
    }

    public static void addWordToWordList(String userID, int wordListID, Word word) {
        init();
        UsersDataQueryHandler.addWordToWordList(connection, userID, wordListID, word);
    }

    public static void removeWordFromWordList(String userID,
                                              int wordListID, Word word) {
        UsersDataQueryHandler.removeWordFromWordList(connection, userID, wordListID, word);
    }

    public static List<WordList> getUserWordLists(String userID) {
        try {
            return UsersDataQueryHandler.getUserWordLists(connection, userID);
        } catch (SQLException e) {
            System.out.println("Can not get user's word list?");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<String> getUserSearchHistory(String userID) {
        try {
            return UsersDataQueryHandler.getUserSearchHistory(connection, userID);
        } catch (SQLException e) {
            System.out.println("Can not get user's search history?");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}