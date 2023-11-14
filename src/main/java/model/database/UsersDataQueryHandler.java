package model.database;

import model.word.WordList;
import model.dictionary.Dictionary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDataQueryHandler {
    public static List<String> getUserSearchHistory(Connection connection, String userID)
            throws SQLException {
        String sql = """
                SELECT word
                FROM user_search_history_data
                WHERE user_id = ?
                ORDER BY timestamp DESC;
                """;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        ResultSet resultSet = statement.executeQuery();

        List<String> answer = new ArrayList<>();
        while (resultSet.next()) {
            answer.add(resultSet.getString("word"));
        }
        return answer;
    }

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
                SELECT word_list_id, word
                FROM user_word_list_data
                WHERE user_id = ?
        """;
        statement = connection.prepareStatement(sql);
        statement.setString(1, userID);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            wordLists.get(resultSet.getInt("word_list_id"))
                    .addWord(Dictionary.getWordData(resultSet.getString("word")));
        }

        return wordLists;
    }

    public static List<String> getUserWordReview(Connection connection, String userID)
            throws SQLException {
        return null;
    }
}