package model.database;

import model.word.Meaning;
import model.word.Phonetic;
import model.word.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDataQueryHandler {
    /**
     * Function to get the word's data from the database.
     *
     * @param connection the database connection
     * @param wordID the word's wordID we want to get data
     * @return a Word object which describe the word information, null if the word does not exist
     * @throws SQLException throw if the query can not be executed
     */
    public static Word getWordData(Connection connection, String wordID) throws SQLException {
        List<String> synonyms = new ArrayList<>();
        List<String> antonyms = new ArrayList<>();
        ResultSet resultSet;
        String sql;
        PreparedStatement statement;

        sql = """
        SELECT synonym
        FROM word_synonym
        WHERE word_id = ?;
        """;
        statement = connection.prepareStatement(sql);
        statement.setString(1, wordID);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            synonyms.add(resultSet.getString("synonym"));
        }

        sql = """
        SELECT antonym
        FROM word_antonym
        WHERE word_id = ?;
        """;
        statement = connection.prepareStatement(sql);
        statement.setString(1, wordID);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            antonyms.add(resultSet.getString("antonym"));
        }

        sql = """
        SELECT word, text, audio, part_of_speech, definition, example
        FROM words
        WHERE word_id = ?;
        """;
        statement = connection.prepareStatement(sql);
        statement.setString(1, wordID);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Phonetic phonetic = new Phonetic(
                    resultSet.getString("text"),
                    resultSet.getString("audio"));
            Meaning meaning = new Meaning(
                    resultSet.getString("part_of_speech"),
                    resultSet.getString("definition"),
                    resultSet.getString("example"),
                    (String[]) synonyms.toArray(),
                    (String[]) antonyms.toArray());

            return new Word(resultSet.getString("word"), phonetic, meaning);
        }
        return null;
    }
}