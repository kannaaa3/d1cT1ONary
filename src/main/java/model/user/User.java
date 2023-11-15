package model.user;

import model.word.Word;
import model.word.WordList;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userID;
    private final List<String> searchHistory;
    private final List<WordList> wordLists;

    /**
     * Create a new user with the userID.
     *
     * @param userID the userID
     */
    public User(String userID) {
        this.userID = userID;
        this.searchHistory = new ArrayList<>();
        this.wordLists = new ArrayList<>();
    }

    /**
     * Function to add a new word to the search history.
     *
     * @param word a string which is the word we want to add to the history
     */
    public void addWordToSearchHistory(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Please do not add a null word "
                    + "into the search history!");
        }
        removeWordFromSearchHistory(word);
        searchHistory.add(word);
    }

    /**
     * Function to remove a word from the search history.
     *
     * @param word a string which is the word we want to add to the history
     */
    public void removeWordFromSearchHistory(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Please do not remove a null word "
                    + "from the search history!");
        }
        for (int i = 0; i < searchHistory.size(); i++) {
            if (searchHistory.get(i).equals(word)) {
                searchHistory.remove(i);
                break;
            }
        }
    }

    /**
     * Function to add a new word to a user's word list.
     *
     * @param word       the word user want to add
     * @param wordListID the wordListID, please note that enumeration begins at 0
     */
    public void addWordToWordList(Word word, int wordListID) {
        if (wordListID < 0 || wordListID >= wordLists.size()) {
            throw new IndexOutOfBoundsException("Word list ID out of bound "
                    + "when adding word to word list!");
        }
        wordLists.get(wordListID).addWord(word);
    }

    /**
     * Function is used when user want to remove a wordlist.
     *
     * @param wordListID id of the wordlist we want to remove
     */
    public void removeWordList(int wordListID) {
        if (wordListID < 0 || wordListID >= wordLists.size()) {
            throw new IndexOutOfBoundsException("Word list ID out of bound "
                    + "when removing wordlist!");
        }
        wordLists.remove(wordListID);
    }

    /**
     * Function to return all user's wordlist.
     *
     * @return a List object which is all the wordlist
     */
    public List<WordList> getAllWordLists() {
        return this.wordLists;
    }

    public String getUserID() {
        return this.userID;
    }
}