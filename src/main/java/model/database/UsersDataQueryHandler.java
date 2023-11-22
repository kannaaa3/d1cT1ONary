package model.database;

import javafx.util.Pair;
import model.word.UserWord;
import model.word.Word;
import model.word.WordList;
import model.dictionary.Dictionary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDataQueryHandler {
    /**
     * Function to get the user's search history with userID.
     *
     * @param connection the database connection
     * @param userID     the user's userID
     * @return a list of string which is user's search history
     * @throws SQLException a sql exception if the queries failed
     */
    public static List<String> getUserSearchHistory(Connection connection, String userID)
            throws SQLException {
        String sql = """
                SELECT synset_id, w_num
                FROM user_search_history_data
                WHERE user_id = ?
                ORDER BY timestamp DESC;
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        ResultSet resultSet = statement.executeQuery();

        List<String> answer = new ArrayList<>();
        while (resultSet.next()) {
            answer.add(Database.getWordFromSynsetIDAndWordNum(
                    resultSet.getLong("synset_id"),
                    resultSet.getLong("w_num")));
        }
        return answer;
    }

    /**
     * Function to get user's wordlist data.
     *
     * @param connection the database connection
     * @param userID     the user's userID
     * @return a list of wordlist which is
     * @throws SQLException a SQLException if the query can not be performed
     */
    public static List<WordList> getUserWordLists(Connection connection, String userID)
            throws SQLException {
        String sql = """
                SELECT word_list_name
                FROM user_word_list_name_data
                WHERE user_id = ?
                ORDER BY word_list_id
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        ResultSet resultSet = statement.executeQuery();
        List<WordList> wordLists = new ArrayList<>();
        while (resultSet.next()) {
            wordLists.add(new WordList(resultSet.getString("word_list_name")));
        }

        sql = """
                        SELECT word_list_id, synset_id, w_num
                        FROM user_word_list_data
                        WHERE user_id = ?
                """;
        statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        resultSet = statement.executeQuery();
        try {
            while (resultSet.next()) {
                wordLists.get(resultSet.getInt("word_list_id"))
                        .addWord(Database.
                                getWordData(Database.getWordFromSynsetIDAndWordNum(
                                        resultSet.getLong("synset_id"),
                                        resultSet.getLong("w_num"))));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Something is wrong with the database construction!");
            e.printStackTrace();
            System.exit(-1);
        }

        return wordLists;
    }

    /**
     * Function to get user's word review data.
     *
     * @param connection the database connection
     * @param userID     the user's id
     * @return a list of user's word
     * @throws SQLException if the query can not be performed
     */
    public static List<UserWord> getUserWordReview(Connection connection, String userID)
            throws SQLException {
        String sql = """
                SELECT synset_id, w_num, timestamp, fluency_level, review_times
                FROM user_word_review_data
                WHERE user_id = ?
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        return null;
    }

    /**
     * Function to add word to user's search history.
     *
     * @param connection the database connection
     * @param userID     the user's id
     * @param word       the word
     */
    public static void addWordToUserSearchHistory(Connection connection,
                                                  String userID, String word) {
        Pair<Long, Long> wordKey = Database.getWordInformation(word);
        assert wordKey != null;
        String sql = """
                INSERT INTO user_search_history_data
                VALUES (?, ?, ?, ?);
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID);
            preparedStatement.setLong(2, wordKey.getKey());
            preparedStatement.setLong(3, wordKey.getValue());
            preparedStatement.setLong(4, System.currentTimeMillis() / 1000);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can add word to user search history");
            e.printStackTrace();
        }
    }

    /**
     * Function to delete word from user's search history
     *
     * @param connection the database connection
     * @param userID     user's id
     * @param word       the word
     */
    public static void deleteWordFromUserHistory(Connection connection,
                                                 String userID, String word) {
        Pair<Long, Long> wordKey = Database.getWordInformation(word);
        assert wordKey != null;
        String sql = """
                DELETE FROM user_search_history_data
                WHERE user_id = ? AND synset_id = ? AND w_num = ?;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID);
            preparedStatement.setLong(2, wordKey.getKey());
            preparedStatement.setLong(3, wordKey.getValue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can remove word to user search history");
            e.printStackTrace();
        }
    }

    public static void createNewWordList(Connection connection,
                                         String userID, int wordListID, String wordListName) {
        String sql = """
                INSERT INTO user_word_list_name_data
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID);
            preparedStatement.setInt(2, wordListID);
            preparedStatement.setString(3, wordListName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can not create new wordlist!");
            e.printStackTrace();
        }
    }

    /**
     * Function to remove word list.
     *
     * @param connection the connection to database
     * @param userID     the user's ID
     * @param wordListID the id of the wordlist
     */
    public static void removeWordList(Connection connection, String userID,
                                      int wordListID) {
        String sql = """
                DELETE FROM user_word_list_name_data
                WHERE user_id = ? AND word_list_id = ?;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID);
            preparedStatement.setInt(2, wordListID);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something is wrong when removing wordlist!");
            e.printStackTrace();
        }
    }

    /**
     * Function to add word to wordlist.
     *
     * @param connection the connection to the database
     * @param userID     the user's id
     * @param wordListID the id of the word list
     * @param word       the word
     */
    public static void addWordToWordList(Connection connection, String userID,
                                         int wordListID, Word word) {

        String sql = """
                INSERT INTO user_word_list_data
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID);
            preparedStatement.setInt(2, wordListID);
            preparedStatement.setLong(3, word.getWordID());
            preparedStatement.setLong(4, word.getWordNum());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something is wrong when adding word to wordlist!");
            e.printStackTrace();
        }
    }

    /**
     * Function to remove word from wordlist.
     *
     * @param connection the connection to the database
     * @param userID     the user's id
     * @param wordListID the wordlist id
     * @param word       the word we want to delete
     */
    public static void removeWordFromWordList(Connection connection, String userID,
                                              int wordListID, Word word) {
        String sql = """
                DELETE FROM user_word_list_data
                WHERE user_id = ? AND word_list_id = ? AND synset_id = ? AND w_num = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID);
            preparedStatement.setInt(2, wordListID);
            preparedStatement.setLong(3, word.getWordID());
            preparedStatement.setLong(4, word.getWordNum());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something is wrong when remove word from wordlist!");
            e.printStackTrace();
        }
    }
}