package model.database;

import model.word.UserWord;
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
     * @param userID the user's userID
     * @return a list of string which is user's search history
     * @throws SQLException a sql exception if the queries failed
     */
    public static List<String> getUserSearchHistory(Connection connection, String userID)
            throws SQLException {
        String sql = """
                SELECT word_id
                FROM user_search_history_data
                WHERE user_id = ?
                ORDER BY timestamp DESC;
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        ResultSet resultSet = statement.executeQuery();

        List<String> answer = new ArrayList<>();
        while (resultSet.next()) {
            answer.add(resultSet.getString("word_id"));
        }
        return answer;
    }

    /**
     * Function to get user's wordlist data.
     *
     * @param connection the database connection
     * @param userID the user's userID
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
                SELECT word_list_id, word_id
                FROM user_word_list_data
                WHERE user_id = ?
        """;
        statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        resultSet = statement.executeQuery();
        try {
            while (resultSet.next()) {
                wordLists.get(resultSet.getInt("word_list_id"))
                        .addWord(WordDataQueryHandler
                                .getWordData(connection, resultSet.getString("word_id")));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Something is wrong with the database construction!");
            e.printStackTrace();
            System.exit(-1);
        }

        return wordLists;
    }

    public static List<UserWord> getUserWordReview(Connection connection, String userID)
            throws SQLException {
        String sql = """
                SELECT word_id, timestamp, fluency_level, review_times
                FROM user_word_review_data
                WHERE user_id = ?
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        return null;
    }
}