package model.database;

import javafx.util.Pair;
import model.util.Algorithm;
import model.util.ComparablePair;
import model.util.Converter;
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
        return null;
    }

    /**
     * Function to get all the word from the database.
     *
     * @param connection the database connection
     * @return an array of string which is the word from database
     */
    public static String[] getWordListFromDatabase(Connection connection) {
        String sql = """
                SELECT DISTINCT word
                FROM wn_synset
                """;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            List<String> ans = new ArrayList<>();
            while (resultSet.next()) {
                ans.add(resultSet.getString("word"));
            }
            return ans.toArray(new String[0]);
        } catch (SQLException e) {
            System.out.println("Can not get word data from the database!");
            e.printStackTrace();
            System.exit(-1);
        }
        return new String[0];
    }

    /**
     * Function to get wordlist from the synset list.
     *
     * @param connection the database connection
     * @param synsetID the list of synset we want to retrieve
     * @return an array of string where their synsetID in the array
     * @throws SQLException an SQLException if the query can be performed
     */
    public static String[] getWordListFromSynsetID(Connection connection,
                                            Long[] synsetID) throws SQLException {
        String sql = """
                SELECT synset_id, word
                FROM wn_synset;
                """;
        Algorithm.sort(synsetID);
        List<String> ans = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            if (Algorithm.contains(synsetID, resultSet.getLong("synset_id"))) {
                ans.add(resultSet.getString("word"));
            }
        }
        return ans.toArray(new String[0]);
    }

    /**
     * Function to get word list from synset ID and word num
     *
     * @param connection the connection to database
     * @param target the target we want to retrieve
     * @return an array of word which match the synset id and word num
     * @throws SQLException an SQLException if the query can be performed
     */
    public static String[] getWordListFromSynsetIDAndWordNum(
            Connection connection, ComparablePair<Long, Long>[] target) throws SQLException {
        String sql = """
                SELECT synset_id, w_num, word
                FROM wn_synset;
                """;
        Algorithm.sort(target);
        List<String> ans = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            if (Algorithm.contains(target, new ComparablePair<Long, Long>(
                    resultSet.getLong("synset_id"),
                    resultSet.getLong("w_num")))) {
                ans.add(resultSet.getString("word"));
            }
        }
        return ans.toArray(new String[0]);
    }

    /**
     * Function to query synonym from database.
     *
     * @param connection the database connection
     * @param synsetID the id of the synset
     * @return an array of synonym
     * @throws SQLException an exception if the query can not be performed
     */
    public static String[] getWordSynonym(Connection connection,
                                          long synsetID) throws SQLException {
        String sql;
        sql = """
        SELECT synset_id_1
        FROM wn_similar
        WHERE synset_id_2 = ?
        UNION
        SELECT synset_id_2
        FROM wn_similar
        WHERE synset_id_1 = ?;
        """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, synsetID);
        preparedStatement.setLong(2, synsetID);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Long> resultSynset = new ArrayList<>();
        while (resultSet.next()) {
            resultSynset.add(resultSet.getLong(1));
        }
        resultSynset.add(synsetID);
        return getWordListFromSynsetID(connection,
                resultSynset.toArray(new Long[0]));
    }

    /**
     * Function to query antonym from database.
     *
     * @param connection the connection to database
     * @param synsetID the word's synsetID
     * @param wordNum the word's num
     * @return an array of antonym
     * @throws SQLException an exception if the query can not be performed
     */
    public static String[] getWordAntonym(Connection connection,
                                          long synsetID, long wordNum) throws SQLException {
        String sql;
        sql = """
        SELECT synset_id_1, wnum_1
        FROM wn_antonym
        WHERE synset_id_2 = ? AND wnum_2 = ?
        UNION
        SELECT synset_id_2, wnum_2
        FROM wn_antonym
        WHERE synset_id_1 = ? AND wnum_1 = ?
        """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, synsetID);
        preparedStatement.setLong(2, wordNum);
        preparedStatement.setLong(3, synsetID);
        preparedStatement.setLong(4, wordNum);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<ComparablePair<Long, Long>> resultSynset = new ArrayList<>();
        while (resultSet.next()) {
            resultSynset.add(new ComparablePair<>(
                    resultSet.getLong(1), resultSet.getLong(2)));
        }
        return getWordListFromSynsetIDAndWordNum(connection,
                (Converter.convertFromListToArray(resultSynset)));
    }


    /**
     * Function to get word's meaning from the database.
     *
     * @param connection the database connection
     * @param word the word we want to get meaning
     * @return an array of string which is the meaning
     * @throws SQLException an exception if the query can not be performed
     */
    public static String[] getWordMeaning(Connection connection, String word) throws SQLException {
        List<Pair<Long, Long> > synset = getWordKey(connection, word);
        Long[] synsetID = new Long[synset.size()];
        for (int i = 0; i < synset.size(); i++) {
            synsetID[i] = synset.get(i).getKey();
        }
        String sql;
        sql = """
        SELECT synset_id, gloss
        FROM wn_gloss;
        """;
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        List<String> answer = new ArrayList<>();
        while (resultSet.next()) {
            if (Algorithm.contains(synsetID, resultSet.getLong("synset_id"))) {
                answer.add(resultSet.getString("gloss"));
            }
        }
        return answer.toArray(new String[0]);
    }

    /**
     * Function to get key for word.
     *
     * @param connection the connection to the database
     * @param word the word we want to find
     * @return a list which is the pair (synset_id, w_num)
     * @throws SQLException an exception if the query can not be performed
     */
    public static List<Pair<Long, Long> > getWordKey(Connection connection, String word)
            throws SQLException {
        String sql;
        sql = """
        SELECT synset_id, w_num
        FROM wn_synset
        WHERE word = ?;
        """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, word);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Pair<Long, Long>> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Pair<>(resultSet.getLong("synset_id"),
                    resultSet.getLong("w_num")));
        }
        return result;
    }
}